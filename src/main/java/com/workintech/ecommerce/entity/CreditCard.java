package com.workintech.ecommerce.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "credit_cards", schema = "public")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false,name="no")
    private String no;

    @Column(nullable = false,name="name")
    private String name;

    @Column(nullable = false,name="expire_month")
    private Integer expireMonth;

    @Column(nullable = false,name="expire_year")
    private Integer expireYear;

    @Column(nullable = false,name="ccv")
    private Integer ccv;
}