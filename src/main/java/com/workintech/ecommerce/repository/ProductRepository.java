package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.TreeSet;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="SELECT p FROM Product p WHERE p.name= :name")
    List<Product> getByName(String name);
    @Query(value="SELECT p FROM Product p WHERE p.category.name= :name")
    List<Product> getByCategory(String name);

}
