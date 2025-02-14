package app.controller;

import app.domain.actor.Person;
import app.exceptions.NotFoundException;
import app.modelAsembler.PersonModelAssembler;
import app.service.interfaces.ICommonService;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@RestController
public class PersonController {

    private final ICommonService<Person,Long> personService;
    private final PersonModelAssembler assembler;

    @Autowired
    public PersonController(ICommonService<Person,Long> personService, PersonModelAssembler assembler) {
        this.personService = personService;
        this.assembler = assembler;
    }

    @GetMapping("/person/{id}")
    public EntityModel<Person> person(@PathVariable Long id){
        Person person;

        person = this.personService.findById(id);
        return assembler.toModel(person);
    }


    @GetMapping("/persons")
    public CollectionModel<EntityModel<Person>> allPerson(){
        List<EntityModel<Person>> persons = this.personService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return  CollectionModel.of(persons, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).allPerson()).withSelfRel());
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id,@RequestBody Person person){

        try {
            Person result = this.personService.save(person);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(person);
        }

    }

    @PostMapping("/person/")
    public ResponseEntity<Person> newPerson(@RequestBody Person person){
        try {
            Person result = this.personService.save(person);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(person);
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long id){
        try{
            this.personService.deleteById(id);

            return ResponseEntity.noContent().build();
        }catch (NotFoundException | IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().build();
        }
    }


}