package service;

import domain.Review;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ReviewRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    ///CRUD Operations
    public Review create(){
        Review review;

        review = new Review();

        return review;
    }

    public Review findById(Long reviewId){
        if(reviewId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Review.class));
        }

        Optional<Review> optionalReview = this.reviewRepository.findById(reviewId);

        if(optionalReview.isEmpty()){
            throw new NotFoundException(Review.class);
        }

        return optionalReview.get();
    }

    public List<Review> findAll(){
        List<Review> reviewList;

        reviewList = this.reviewRepository.findAll();

        return  reviewList;
    }

    public Review save(Review review){
        if(review==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Review.class));

        return this.reviewRepository.save(review);
    }

    public void delete(Review review){
        if(review==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Review.class));
        }
        if(review.getIdReview() == null)
            throw new NotFoundException(Review.class);

        this.reviewRepository.delete(review);
    }
}
