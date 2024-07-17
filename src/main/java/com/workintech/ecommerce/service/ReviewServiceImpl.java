package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Review;
import com.workintech.ecommerce.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {

        return reviewRepository.findById(id).orElseThrow(null) ;
    }

    @Transactional
    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional
    @Override
    public Review delete(Long id) {
        Review review = findById(id);
        reviewRepository.delete(review);
        return review;
    }
}
