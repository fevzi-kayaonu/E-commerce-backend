package com.workintech.ecommerce.repository;


import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Enum_Gender;
import com.workintech.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE p.name= :name")
    Optional<List<Product>> getByName(String name);

    @Query(value = "SELECT p FROM Product p WHERE p.category.name= :name")
    Optional<List<Product>> getByCategory(Enum_Category name);

    @Query(value = "SELECT p FROM Product p WHERE p.category.name= :name and p.gender= :gender")
    Optional<Page<Product>> getByCategoryAndGender(@Param("name") Enum_Category name, @Param("gender") Enum_Gender gender, Pageable pageable);//Stringleri enum olarak alabilirmiyiz daha sonra bakarsın

}
