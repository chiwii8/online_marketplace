package controller.common;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public boolean newProduct(@RequestBody Product product){
        return this.productService.save(product)!=null;
    }

    @PutMapping("/{id}")
    public  Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        Product replacedProduct = this.productService.findById(id);

        replacedProduct.updateFrom(product);

        this.productService.save(replacedProduct);
        return replacedProduct;
    }

    public void deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
    }
}