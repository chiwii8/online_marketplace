package app.service;

import app.domain.Review;
import app.exceptions.NotFoundException;
import app.repository.ReviewRepository;
import app.service.interfaces.ICommonService;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements ICommonService<Review,Long> {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review create() {
        return new Review();
    }

    @Override
    public Review findById(Long id)throws IllegalArgumentException,NotFoundException{
        Optional<Review> optionalReview;
        if(id == null){
            throw new IllegalArgumentException("The id must not be null");
        }

        optionalReview = reviewRepository.findById(id);
        if(optionalReview.isEmpty()){
            throw new NotFoundException(Review.class);
        }

        return optionalReview.get();
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews;

        reviews = reviewRepository.findAll();

        return reviews;
    }

    @Override
    public void delete(Review entity) throws IllegalArgumentException, OptimisticLockingFailureException {
        this.reviewRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id)throws NotFoundException,IllegalArgumentException,OptimisticLockingFailureException{
            if(id==null){
                throw new IllegalArgumentException("The id must not be null");
            }

            if(!this.reviewRepository.existsById(id)){
                throw new NotFoundException(Review.class);
            }

            this.reviewRepository.deleteById(id);
    }

    @Override
    public Review save(Review entity) throws IllegalArgumentException,OptimisticLockingFailureException{
        return this.reviewRepository.save(entity);
    }
}