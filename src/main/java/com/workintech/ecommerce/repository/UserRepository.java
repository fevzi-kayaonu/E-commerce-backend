package com.workintech.ecommerce.repository;


import com.workintech.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value="SELECT u FROM User u WHERE u.email= :email")
    User findByEmail(String email);

}
