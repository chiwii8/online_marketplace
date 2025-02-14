package app.controller;

import app.domain.Direction;
import app.exceptions.NotFoundException;
import app.modelAsembler.DirectionModelAssembler;
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
public class DirectionController {

    private final ICommonService<Direction,Long> directionService;
    private final DirectionModelAssembler assembler;

    @Autowired
    public DirectionController(ICommonService<Direction,Long> directionService, DirectionModelAssembler assembler) {
        this.directionService = directionService;
        this.assembler = assembler;
    }

    @GetMapping("/direction/{id}")
    public EntityModel<Direction> direction(@PathVariable Long id){
        Direction direction;

        direction = this.directionService.findById(id);
        return assembler.toModel(direction);
    }


    @GetMapping("/directions")
    public CollectionModel<EntityModel<Direction>> allDirection(){
        List<EntityModel<Direction>> directions = this.directionService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return  CollectionModel.of(directions, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DirectionController.class).allDirection()).withSelfRel());
    }

    @PutMapping("/direction/{id}")
    public ResponseEntity<Direction> updateDirection(@PathVariable Long id,@RequestBody Direction direction){

        try {
            Direction result = this.directionService.save(direction);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(direction);
        }

    }

    @PostMapping("/direction/")
    public ResponseEntity<Direction> newDirection(@RequestBody Direction direction){
        try {
            Direction result = this.directionService.save(direction);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(direction);
        }
    }

    @DeleteMapping("/direction/{id}")
    public ResponseEntity<Direction> deleteDirection(@PathVariable Long id){
        try{
            this.directionService.deleteById(id);

            return ResponseEntity.noContent().build();
        }catch (NotFoundException | IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().build();
        }
    }


}