package app.service;

import app.domain.Product;
import app.exceptions.NotFoundException;
import app.repository.ProductRepository;
import app.service.interfaces.ICommonService;
import jakarta.transaction.Transactional;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ICommonService<Product,Long> {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create() {
        return new Product();
    }

    @Override
    public Product findById(Long id) throws IllegalArgumentException,NotFoundException {
        Optional<Product> optionalProduct;

        if(id==null){
            throw new IllegalArgumentException("The id Element receive is null");
        }

        optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new NotFoundException(Product.class);
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products;

        products = productRepository.findAll();


        return products;
    }

    @Override
    public void delete(Product entity) throws IllegalArgumentException, OptimisticLockingFailureException {
        this.productRepository.delete(entity);
    }

    public void deleteById(Long id) throws NotFoundException, IllegalArgumentException{
        if(id==null || !this.productRepository.existsById(id)){
            throw new NotFoundException(Product.class);
        }

        this.productRepository.deleteById(id);
    }

    @Override
    public Product save(Product entity)  throws IllegalArgumentException, OptimisticLockingFailureException {
        return this.productRepository.save(entity);
    }
}