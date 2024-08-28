package service;

import domain.Product;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    ///CRUD Operations
    public Product create(){
        Product product;

        product = new Product();

        return product;
    }

    public Product findById(Long productId){
        if(productId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Product.class));
        }

        Optional<Product> optionalProduct = this.productRepository.findById(productId);

        if(optionalProduct.isEmpty()){
            throw new NotFoundException(Product.class);
        }

        return optionalProduct.get();
    }

    public List<Product> findAll(){
        List<Product> productList;

        productList = this.productRepository.findAll();

        return  productList;
    }

    public Product save(Product product){
        if(product==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Product.class));

        return this.productRepository.save(product);
    }

    public void delete(Product product){
        if(product==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Product.class));
        }
        if(product.getId()==null)
            throw new NotFoundException(Product.class);

        this.productRepository.delete(product);
    }

    public void deleteById(Long id){
        if(id==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Product.class));

        Optional<Product> product = this.productRepository.findById(id);

        if(product.isEmpty()){
            throw new NotFoundException(Product.class);
        }

    }


    ///Extra queries
    public List<Product> findByName(String name){
        List<Product> productList;
        if(name==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Product.class));

        if(name.isBlank())
            productList = this.findAll();
        else
            productList = this.productRepository.findByName(name);

        return productList;
    }
}
