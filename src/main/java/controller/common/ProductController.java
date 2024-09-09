package controller.common;

import domain.Product;
import domain.actor.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;

@RestController
@RequestMapping( path = "/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> all(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id){
        return this.productService.findById(id);
    }

    @PostMapping
    public List<Product> listByName(@RequestParam(value = "name") String name){
        return productService.findByName(name);
    }

    @PostMapping("/new")
    public ResponseEntity<Product> newProduct(@RequestBody Product product){
        try{
            Product newProduct = this.productService.save(product);
            return ResponseEntity.ok(newProduct);
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){

        try {
            Product replacedProduct = this.productService.findById(id);
            replacedProduct.updateFrom(product);

            Product returnedProduct = this.productService.save(replacedProduct);

            return ResponseEntity.ok(returnedProduct);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
    }
}