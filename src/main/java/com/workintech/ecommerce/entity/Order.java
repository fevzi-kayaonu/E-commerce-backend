package com.workintech.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false,name="date")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant date = Instant.now();

    @Column(nullable = false, length = 20,name="status")
    @Enumerated(EnumType.STRING)
    private Enum_OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "adress_id", nullable = false)
    private Address address;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false,name="amount")
    private Double amount;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch=FetchType.LAZY)
    @JoinTable(name="product_order",schema = "public",joinColumns = @JoinColumn(name="order_id"),inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> products;

    @OneToOne(cascade=CascadeType.ALL,mappedBy = "order")
    private Payment payment;

}
