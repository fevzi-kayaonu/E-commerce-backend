package com.workintech.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "public")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 45,name="name")
    private String name;

    @Column(nullable = false,name="description")
    private String description;

    @Column(nullable = false,name="price")
    private Double price;

    @Column(nullable = false,name="rating")
    private Double rating;

    @Column(nullable = false,name="stock_quantity")
    private Integer stockQuantity;

    @Column(nullable = false, length = 20,name="gender")
    @Enumerated(EnumType.STRING)
    private Enum_Gender gender ;

    @Column(nullable = false, updatable = false,name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
