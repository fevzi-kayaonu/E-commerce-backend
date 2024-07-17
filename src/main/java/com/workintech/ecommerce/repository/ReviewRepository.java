package com.workintech.ecommerce.repository;


import com.workintech.ecommerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
