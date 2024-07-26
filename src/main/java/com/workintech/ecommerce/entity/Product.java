package com.workintech.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;

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

    // Product oluşturulurken category yi savelememe rağmen neden category oluşturup save edip category id sine ulaşıyor.
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // ManyToMany de proje run edildiğinde component direk oluşturuluyor, oneTomany de neden oluşturulmuyor Lazy olarak bırakıyoruz ?

    // product oluşturulurken içerisine imagesleri yolladığım zaman images tablosundaki product_id null olamaz hatası aldım
    // 45. satırda böyle bit hata almıyorum
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<Image> images = new ArrayList<>(); // bunları hep List olarak tutuyorum Set olarak mı tutmalıyım , ayrımı nedir. JpaRepositorydeki metodlarda List döndürüyuor

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Review> reviews = new  LinkedHashSet<>();

}
