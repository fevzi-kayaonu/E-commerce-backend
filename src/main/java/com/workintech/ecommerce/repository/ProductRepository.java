package com.workintech.ecommerce.repository;


import com.workintech.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="SELECT p FROM Product p WHERE p.name= :name")
    List<Product> getByName(String name);
    @Query(value="SELECT p FROM Product p WHERE p.category.name= :name")
    List<Product> getByCategory(String name);
    @Query(value="SELECT p FROM Product p WHERE p.category.name= :name and p.gender= :gender")
    Page<Product> getByCategoryAndGender(@Param("name") String name, @Param("gender") String gender, Pageable pageable);//Stringleri enum olarak alabilirmiyiz daha sonra bakarsın

}
