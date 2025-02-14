package app.controller;

import app.domain.Order;
import app.exceptions.NotFoundException;
import app.modelAsembler.OrderModelAssembler;
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
public class OrderController {

    private final ICommonService<Order,Long> orderService;
    private final OrderModelAssembler assembler;

    @Autowired
    public OrderController(ICommonService<Order,Long> orderService, OrderModelAssembler assembler) {
        this.orderService = orderService;
        this.assembler = assembler;
    }

    @GetMapping("/order/{id}")
    public EntityModel<Order> order(@PathVariable Long id){
        Order order;

        order = this.orderService.findById(id);
        return assembler.toModel(order);
    }


    @GetMapping("/orders")
    public CollectionModel<EntityModel<Order>> allOrder(){
        List<EntityModel<Order>> orders = this.orderService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return  CollectionModel.of(orders, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).allOrder()).withSelfRel());
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id,@RequestBody Order order){

        try {
            Order result = this.orderService.save(order);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(order);
        }

    }

    @PostMapping("/order/")
    public ResponseEntity<Order> newOrder(@RequestBody Order order){
        try {
            Order result = this.orderService.save(order);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(order);
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id){
        try{
            this.orderService.deleteById(id);

            return ResponseEntity.noContent().build();
        }catch (NotFoundException | IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().build();
        }
    }


}