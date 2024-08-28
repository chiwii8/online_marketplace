package repository;

import domain.actor.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    @Query("select p from Person p where p.name=:name")
    public List<Person> findByName(@Param("name") String name);

    @Query("select p from Person p where p.surnames like '%:surnames%'")
    public List<Person> findBySurnames(@Param("surnames") String surnames);

    @Query("select p from Person p where p.email=:email")
    public Person findByEmail(@Param("email") String email);

    @Query("select p from Person p where p.phoneNumber=:phoneNumber")
    public Person findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}