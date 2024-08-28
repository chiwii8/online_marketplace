package repository;

import domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.name=:name")
    public List<Product> findByName(@Param("name") String name);

    public List<Product> findByFeatures(@Param("features") Map<String,String> features);

}