package controller.customer;

import domain.CreditCard;
import domain.actor.Customer;
import domain.actor.Person;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CreditCardService;
import service.PersonService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer/credit")
public class CreditCardController {

    private final PersonService personService;
    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(PersonService personService, CreditCardService creditCardService) {
        this.personService = personService;
        this.creditCardService = creditCardService;
    }


    @GetMapping("/all/{id}")
    public List<CreditCard> all(@PathVariable Long id){
        try {
            ///Verify if exist the Customer id exists
            Person person = this.personService.findById(id);
            if(person instanceof Customer customer){
                return customer.getCreditCards();
            }else{
                throw new NotFoundException("The customer id not find");
            }

        }catch (Exception ex){
            return new ArrayList<>();
        }
    }

    @GetMapping("/{id}")
    public CreditCard one(@PathVariable Long id){
        return this.creditCardService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCard){
        try {
            CreditCard replacedcreditCard = this.creditCardService.findById(id);

            replacedcreditCard.updateFrom(creditCard);

            CreditCard returnedCreditCard = this.creditCardService.save(replacedcreditCard);

            return ResponseEntity.ok(returnedCreditCard);
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<CreditCard> newCreditCard(@RequestBody CreditCard creditCard){
        try {
            CreditCard newCreditCard = this.creditCardService.save(creditCard);
            return ResponseEntity.ok(newCreditCard);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CreditCard> deleteCreditCard(@PathVariable Long id){
        try {
            this.creditCardService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return  ResponseEntity.notFound().build();
        }
    }

}