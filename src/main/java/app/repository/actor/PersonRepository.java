package app.repository.actor;

import app.domain.actor.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByEmail(String email);

    Optional<Person> findByPhoneNumber(String phoneNumber);
}