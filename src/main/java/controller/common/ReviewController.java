package controller.common;

import domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;
import service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ProductService productService;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }



    @PostMapping("/new")
    public ResponseEntity<Review> newReview(@RequestBody Review review){
        try {
            Review newReview = this.reviewService.save(review);
            return ResponseEntity.ok(newReview);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review){
        try {
            Review replacedReview = this.reviewService.findById(id);

            replacedReview.updateFrom(review);

            Review newReview = this.reviewService.save(replacedReview);

            return ResponseEntity.ok(newReview);
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long id){
        try {
            this.reviewService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}