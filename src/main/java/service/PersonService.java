package service;

import domain.actor.Person;
import exceptions.IllegalBlankException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PersonService {

    private final PersonRepository personRepository;
    
   @Autowired
   public  PersonService(PersonRepository personRepository){
       this.personRepository = personRepository;
   }

    ///CRUD Operations
    public Person create(){
        throw new UnsupportedOperationException("This is a abstract class and cannot be created");
    }

    public Person findById(Long personId){
        if(personId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Person.class));
        }

        Optional<Person> optionalPerson = this.personRepository.findById(personId);

        if(optionalPerson.isEmpty()){
            throw new NotFoundException(Person.class);
        }

        return optionalPerson.get();
    }

    public List<Person> findAll(){
        List<Person> personList;

        personList = this.personRepository.findAll();

        return  personList;
    }

    public Person save(Person person){
        if(person==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Person.class));

        return this.personRepository.save(person);
    }

    public void delete(Person person){
        if(person==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Person.class));
        }
        if(person.getId()==null)
            throw new NotFoundException(Person.class);

        this.personRepository.delete(person);
    }

    ///Extra Operations
    public List<Person> findByName(String name){
       if(name==null)
           throw new NullPointerException(ExceptionUtils.getNullMessage(Person.class));
       if(name.isBlank())
           throw new IllegalBlankException(Person.class);

       return this.personRepository.findByName(name);

    }

    public List<Person> findBySurnames(String surnames){
       if(surnames==null)
           throw new NullPointerException(ExceptionUtils.getNullMessage(Person.class));
        if(surnames.isBlank())
            throw new IllegalBlankException(Person.class);

        return this.personRepository.findBySurnames(surnames);
    }

    public Person findByEmail(String email){
        if(email==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Person.class));
        if(email.isBlank())
            throw new IllegalBlankException(Person.class);

        Person person = this.personRepository.findByEmail(email);

        if(person==null)
            throw new NotFoundException(Person.class);

        return person;
    }

    public Person findByPhoneNumber(String phoneNumber){
        if(phoneNumber==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Person.class));
        if(phoneNumber.isBlank())
            throw new IllegalBlankException(Person.class);

        Person person = this.personRepository.findByPhoneNumber(phoneNumber);

        if(person==null)
            throw new NotFoundException(Person.class);

        return person;
    }
}