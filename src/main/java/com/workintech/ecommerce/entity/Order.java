package com.workintech.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;

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
    private String status;

    @ManyToOne
    @JoinColumn(name = "adress_id", nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false,name="amount")
    private Double amount;
}
