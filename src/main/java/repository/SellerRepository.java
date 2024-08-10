package repository;

import domain.actor.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    @Query("select s from Seller s where s.comercialName= :comercialName")
    public Seller findByComercialName(@Param("comercialName") String comercialName);
}