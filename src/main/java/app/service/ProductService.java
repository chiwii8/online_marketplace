package app.service;

import app.domain.Product;
import app.exceptions.NotFoundException;
import app.repository.ProductRepository;
import app.service.interfaces.ICommonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements ICommonService<Product> {

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
    public Product findById(Long id) throws NotFoundException {
        Optional<Product> optionalProduct;

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
    public void delete(Product entity) {

        if(!this.productRepository.existsById(entity.getId())){
            throw new NotFoundException(Product.class);
        }

        this.productRepository.delete(entity);
    }

    @Override
    public Product save(Product entity) {
        return this.productRepository.save(entity);
    }
}