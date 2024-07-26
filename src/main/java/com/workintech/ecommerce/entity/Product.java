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

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $price = this.getPrice();
        result = result * 59 + ($price == null ? 43 : $price.hashCode());
        Object $rating = this.getRating();
        result = result * 59 + ($rating == null ? 43 : $rating.hashCode());
        Object $stockQuantity = this.getStockQuantity();
        result = result * 59 + ($stockQuantity == null ? 43 : $stockQuantity.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        Object $gender = this.getGender();
        result = result * 59 + ($gender == null ? 43 : $gender.hashCode());
        Object $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        Long var10000 = this.getId();
        return "Product(id=" + var10000 + ", name=" + this.getName() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", rating=" + this.getRating() + ", stockQuantity=" + this.getStockQuantity() + ", gender=" + this.getGender() + ", createdAt=" + this.getCreatedAt() +")";
    }



}
