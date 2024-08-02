package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.ProductRequestDto;
import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Product;

public class ProductMapper {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.name());
        product.setDescription(productRequestDto.description());
        product.setPrice(productRequestDto.price());
        product.setStockQuantity(productRequestDto.stockQuantity());
        product.setGender(productRequestDto.gender());
        return product;
    }

    public static ProductResponseDto productToProductResponseDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getRating(), product.getStockQuantity(),
                product.getGender(), product.getCreatedAt(), CategoryMapper.categoryToCategoryResponseDto(product.getCategory()),
                product.getImages().stream().map(ImageMapper::imageToImageResponseDto).toList());
    }
}
