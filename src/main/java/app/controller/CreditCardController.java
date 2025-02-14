package app.controller;


import app.domain.CreditCard;
import app.exceptions.NotFoundException;
import app.modelAsembler.CreditCardAssembler;
import app.service.interfaces.ICommonService;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@RestController
public class CreditCardController{

    private final ICommonService<CreditCard,Long> creditCardService;
    private final CreditCardAssembler assembler;

    @Autowired
    public CreditCardController(ICommonService<CreditCard, Long> creditCardService, CreditCardAssembler assembler) {
        this.creditCardService = creditCardService;
        this.assembler = assembler;
    }

    @GetMapping("/creditcard/{id}")
    public EntityModel<CreditCard> creditCard(@PathVariable Long id){
        CreditCard creditCard;
        try {
            creditCard = this.creditCardService.findById(id);
            return assembler.toModel(creditCard);
        }catch (NotFoundException ex){
            //Debería devolver un Error
        }
        return null;
    }

    @GetMapping("/creditcards")
    public CollectionModel<EntityModel<CreditCard>> allCreditCards(){
        List<EntityModel<CreditCard>> creditcards = this.creditCardService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(creditcards,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CreditCardController.class).allCreditCards()).withSelfRel());
    }

    @PutMapping("/creditcard/{id}")
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCard){
        try {
            CreditCard result = this.creditCardService.save(creditCard);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(creditCard);
        }
    }

    @PostMapping("/creditcard/")
    public ResponseEntity<CreditCard> newCreditCard(@RequestBody CreditCard creditCard){
        try{
            CreditCard result = this.creditCardService.save(creditCard);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(creditCard);
        }
    }

    @DeleteMapping("/creditcard/{id}")
    public ResponseEntity<CreditCard> deleteCreditCard(@PathVariable Long id){
        try {
            this.creditCardService.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (NotFoundException | IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().build();
        }
    }

}