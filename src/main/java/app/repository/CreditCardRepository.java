package app.repository;

import app.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    Optional<CreditCard> findByOwner(@Param("owner") String owner);
}