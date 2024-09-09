package controller.common;

import domain.actor.Admin;
import domain.actor.Customer;
import domain.actor.Person;
import domain.actor.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import service.CustomerService;
import service.PersonService;
import service.SellerService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final PersonService personService;

    @Autowired
    public ProfileController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person one(@PathVariable Long id){
        return this.personService.findById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<Person> newProfile(@RequestBody Person person){
        try{
            Person returnedPerson = this.personService.save(person);
            return ResponseEntity.ok(returnedPerson);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateProfile(@PathVariable Long id, @RequestBody Person updatedPerson){
        try {
            Person person = this.personService.findById(id);
            if(updatedPerson instanceof Customer updatedCustomer && person instanceof Customer customer){
                customer.updateFrom(updatedCustomer);
            }else if(updatedPerson instanceof Seller updatedSeller && person instanceof Seller seller){
                seller.updateFrom(updatedSeller);
            }else if(updatedPerson instanceof Admin && person instanceof Admin admin){
                admin.updateFrom(updatedPerson);
            }else{
                return ResponseEntity.badRequest().build();
            }

            this.personService.save(person);

            return ResponseEntity.ok(person);

        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }

    }

}