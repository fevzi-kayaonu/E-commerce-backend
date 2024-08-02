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
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 45, name = "name")
    private String name;

    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "price")
    private Double price;

    @Column(nullable = false, name = "rating")
    private Double rating;

    @Column(nullable = false, name = "stock_quantity")
    private Integer stockQuantity;

    @Column(nullable = false, length = 20, name = "gender")
    @Enumerated(EnumType.STRING)
    private Enum_Gender gender;

    @Column(nullable = false, updatable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt = Instant.now();

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Review> reviews = new LinkedHashSet<>();

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product other = (Product) o;
        if (!other.canEqual(this)) {
            return false;
        }

        Object this$id = this.getId();
        Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
            return false;
        }

        Object this$price = this.getPrice();
        Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) {
            return false;
        }

        Object this$rating = this.getRating();
        Object other$rating = other.getRating();
        if (this$rating == null ? other$rating != null : !this$rating.equals(other$rating)) {
            return false;
        }

        Object this$stockQuantity = this.getStockQuantity();
        Object other$stockQuantity = other.getStockQuantity();
        if (this$stockQuantity == null ? other$stockQuantity != null : !this$stockQuantity.equals(other$stockQuantity)) {
            return false;
        }

        Object this$name = this.getName();
        Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }

        Object this$description = this.getDescription();
        Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }

        Object this$gender = this.getGender();
        Object other$gender = other.getGender();
        if (this$gender == null ? other$gender != null : !this$gender.equals(other$gender)) {
            return false;
        }

        Object this$createdAt = this.getCreatedAt();
        Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) {
            return false;
        }

        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }


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
        return "Product(id=" + var10000 + ", name=" + this.getName() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", rating=" + this.getRating() + ", stockQuantity=" + this.getStockQuantity() + ", gender=" + this.getGender() + ", createdAt=" + this.getCreatedAt() + ")";
    }

}
