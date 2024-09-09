package controller.customer;

import domain.Order;
import domain.actor.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;
import service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customer/order")
public class OrderController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public OrderController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Order> all(@PathVariable Long id){
        try {
            ///Verify if the user is a customer
            Customer customer = customerService.findById(id);
            return customer.getOrders();

        }catch (Exception ex){
            return new ArrayList<>();
        }

    }

    @GetMapping("/{id}")
    public Order one(@PathVariable Long id){
        return this.orderService.findById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<Order> newOrder(@RequestBody Order order){
        try {
            Order newOrder = this.orderService.save(order);
            return ResponseEntity.ok(newOrder);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order){
        try {
            Order replacedOrder = this.orderService.findById(id);

            replacedOrder.updateFrom(order);

            Order updatedOrder = this.orderService.save(replacedOrder);

            return ResponseEntity.ok(updatedOrder);
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id){
        try {
            this.orderService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}