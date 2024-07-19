package com.workintech.ecommerce.service;


import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    //Bir servise birden fazla reposiroty inject edersem yan eksisi olur mu , (daha fazla repository eklersem daha basit jpql lerle işleri çözebiliyorum)
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


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
        return product;
    }

    @Override
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
    public List<Product> getByCategory(String name) {
        return productRepository.getByCategory(name);
    }

    @Override
    public List<Product> getByCategoryAndGender(String name, String gender, int offset, int count) {
        Pageable pageable = PageRequest.of(offset,count);
       return productRepository.getByCategoryAndGender(name,gender,pageable).getContent();
    }


    @Override
    public List<Product> getProducts(int offset, int count) {
        Pageable pageable = PageRequest.of(offset, count);
        return productRepository.findAll(pageable).getContent();
    }
}
