package app.repository.actor;

import app.domain.Product;
import app.domain.actor.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    List<Product> findAllProductsBySeller(Seller seller);

    Optional<Seller> findByCommercialName(String name);
}