package repository;

import domain.actor.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    @Query("select s from Seller s where s.commercialName like '%:commercialName%")
    public List<Seller> findByCommercialName(@Param("commercialName") String commercialName);

}