package controller.customer;

import domain.Direction;
import domain.actor.Customer;
import domain.actor.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;
import service.DirectionService;
import service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/customer/direction")
public class DirectionController {
    private final DirectionService directionService;
    private final CustomerService customerService;

    @Autowired
    public DirectionController(DirectionService directionService,CustomerService customerService) {
        this.directionService = directionService;
        this.customerService = customerService;
    }

    /**
     * Example method of how is going to do, before we can use the login register
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public List<Direction> all(@PathVariable Long id){
        ///Verify if exists the customer with that id
        Customer customer = this.customerService.findById(id);

        return customer.getDirections();
    }

    @GetMapping("/{id}")
    public Direction one(@PathVariable Long id){
        return this.directionService.findById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<Direction> newDirection(@RequestBody Direction direction){
        try {
            Direction newDirection = this.directionService.save(direction);
            return ResponseEntity.ok(newDirection);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direction> updateDirection(@PathVariable Long id, @RequestBody Direction direction){
        try {
            Direction replacedDirection = this.directionService.findById(id);

            replacedDirection.updateFrom(direction);

            Direction updatedDirection = this.directionService.save(replacedDirection);

            return ResponseEntity.ok(updatedDirection);
        }catch (Exception ex){
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Direction> deleteDirection(@PathVariable Long id){
        try {
            this.directionService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}