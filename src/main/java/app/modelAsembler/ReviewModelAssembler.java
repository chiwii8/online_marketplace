package app.modelAsembler;

import app.controller.ReviewController;
import app.domain.Review;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {

    @Override
    public EntityModel<Review> toModel(Review review) {
        return EntityModel.of(review, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReviewController.class).review(review.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReviewController.class).allReview()).withRel("reviews"));
    }

}