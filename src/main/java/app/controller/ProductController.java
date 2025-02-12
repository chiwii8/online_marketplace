package app.controller;

import app.domain.Product;
import app.exceptions.NotFoundException;
import app.modelAsembler.ProductModelAssembler;
import app.service.interfaces.ICommonService;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ICommonService<Product,Long> productService;
    private final ProductModelAssembler assembler;

    @Autowired
    public ProductController(ICommonService<Product,Long> productService, ProductModelAssembler assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }

    @GetMapping("/product/{id}")
    public EntityModel<Product> product(@PathVariable Long id){
        Product product;

             product = this.productService.findById(id);
             return assembler.toModel(product);
    }


    @GetMapping("/products")
    public CollectionModel<EntityModel<Product>> allProduct(){
        List<EntityModel<Product>> products = this.productService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return  CollectionModel.of(products, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).allProduct()).withSelfRel());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){

        try {
            Product result = this.productService.save(product);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(product);
        }

    }

    @PostMapping("/product/")
    public ResponseEntity<Product> newProduct(@RequestBody Product product){
        try {
            Product result = this.productService.save(product);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(product);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        try{
            this.productService.deleteById(id);

            return ResponseEntity.noContent().build();
        }catch (NotFoundException | IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().build();
        }
    }


}