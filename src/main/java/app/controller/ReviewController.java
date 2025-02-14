package app.controller;

import app.domain.Review;
import app.exceptions.NotFoundException;
import app.modelAsembler.ReviewModelAssembler;
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
public class ReviewController {

    private final ICommonService<Review,Long> reviewService;
    private final ReviewModelAssembler assembler;

    @Autowired
    public ReviewController(ICommonService<Review,Long> reviewService, ReviewModelAssembler assembler) {
        this.reviewService = reviewService;
        this.assembler = assembler;
    }

    @GetMapping("/review/{id}")
    public EntityModel<Review> review(@PathVariable Long id){
        Review review;

        review = this.reviewService.findById(id);
        return assembler.toModel(review);
    }


    @GetMapping("/reviews")
    public CollectionModel<EntityModel<Review>> allReview(){
        List<EntityModel<Review>> reviews = this.reviewService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return  CollectionModel.of(reviews, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReviewController.class).allReview()).withSelfRel());
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id,@RequestBody Review review){

        try {
            Review result = this.reviewService.save(review);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(review);
        }

    }

    @PostMapping("/review/")
    public ResponseEntity<Review> newReview(@RequestBody Review review){
        try {
            Review result = this.reviewService.save(review);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(review);
        }
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long id){
        try{
            this.reviewService.deleteById(id);

            return ResponseEntity.noContent().build();
        }catch (NotFoundException | IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().build();
        }
    }


}