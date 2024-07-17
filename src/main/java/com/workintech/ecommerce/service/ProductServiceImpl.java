package com.workintech.ecommerce.service;

import org.springframework.data.domain.Sort;
import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAll() { // Burada databaseden id ye göre mi sıralayıp getirdi, yani id ye göre mi endeksli
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(null) ;
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Transactional
    @Override
    public Product delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
        return product;
    }



    public List<Product> getPriceDesc() {
        /* Bu algoritmaları jpql ile mi yapmak doğru olur ya da bu şekilde mi daha doğru olur
        return findAll().stream()
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());
         */
        Sort sort = Sort.by(Sort.Order.desc("price"));
        return productRepository.findAll(sort);

    }

    @Override
    public List<Product> getPriceAsc() {
        Sort sort = Sort.by(Sort.Order.asc("price"));
        return productRepository.findAll(sort);
    }

    @Override
    public List<Product> getByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> getByCategory(Enum_Category category) {
        Long categoryId = category.getCategoryId();
        return productRepository.getByCategory(categoryId);
    }
}
