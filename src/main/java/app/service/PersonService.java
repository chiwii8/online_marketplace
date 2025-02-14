package app.service;

import app.domain.actor.Person;
import app.exceptions.NotFoundException;
import app.repository.actor.PersonRepository;
import app.service.interfaces.ICommonService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements ICommonService<Person,Long> {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person create() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("The Class Person can not be created");
    }

    @Override
    public Person findById(Long id) throws NotFoundException,IllegalArgumentException {
        Optional<Person> optionalPerson;
        if(id==null)
            throw new IllegalArgumentException("The id must not be null");

        optionalPerson = personRepository.findById(id);

        if(optionalPerson.isEmpty())
            throw new NotFoundException(Person.class);

        return optionalPerson.get();
    }

    @Override
    public List<Person> findAll() {
        List<Person> people;

        people = personRepository.findAll();

        return people;
    }

    @Override
    public void delete(Person entity)throws IllegalArgumentException, OptimisticLockingFailureException {
        this.delete(entity);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException,IllegalArgumentException{
        if(id==null){
            throw new IllegalArgumentException("the id must not be null");
        }

        if(!this.personRepository.existsById(id))
            throw new NotFoundException(Person.class);

        this.personRepository.deleteById(id);

    }

    @Override
    public Person save(Person entity) throws IllegalArgumentException,OptimisticLockingFailureException{
        return this.personRepository.save(entity);
    }
}