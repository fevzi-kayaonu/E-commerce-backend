package com.workintech.ecommerce.service;


import com.workintech.ecommerce.dto.ProductRequestDto;
import com.workintech.ecommerce.entity.*;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.mapper.ImageMapper;
import com.workintech.ecommerce.repository.CategoryRepository;
import com.workintech.ecommerce.repository.ImageRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.mapper.ProductMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final CategoryService categoryService;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ImageService imageService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
        this.categoryService = categoryService;
    }


    @Override
    public List<Product> findAll() { // Burada databaseden id ye göre mi sıralayıp getirdi, yani id ye göre mi endeksli
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ErrorException("Product not found", HttpStatus.NOT_FOUND));
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
    public List<Product> getByCategory(Enum_Category name) {
        return productRepository.getByCategory(name).orElseThrow(() -> new ErrorException("Category not found :" + name, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Product> getByCategoryAndGender(Enum_Category name, Enum_Gender gender, int offset, int count) {
        Pageable pageable = PageRequest.of(offset, count);
        return productRepository.getByCategoryAndGender(name, gender, pageable).orElseThrow(() -> new ErrorException("No products found for category: " + name + " and gender: " + gender, HttpStatus.NOT_FOUND)).getContent();
    }


    @Override
    public List<Product> getProducts(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return productRepository.findAll(pageable).getContent();
    }

    @Transactional
    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {

        Product product = ProductMapper.productRequestDtoToProduct(productRequestDto);

        Category category = categoryService.getByName(productRequestDto.category());

        product.setCategory(category);

        category.addProduct(product);

        product.setImages(productRequestDto.imageRequestDto().stream().map(item -> {
            Image image = new Image();
            image.setProduct(product);
            image.setUrl(item.url());
            return imageService.save(image);
        }).toList());

        return product;
    }
}
