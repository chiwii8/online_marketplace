package repository;

import domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    @Query("select c from CreditCard c where c.owner=:owner")
    public CreditCard findByOwner(@Param("owner") String owner);

}
