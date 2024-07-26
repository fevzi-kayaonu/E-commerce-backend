package com.workintech.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "addresses", schema = "public")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(nullable = false,name= "description")
    private String description;

    @Column(nullable = false, length = 45,name= "street")
    private String street;

    @Column(nullable = false, length = 45,name= "neighborhood")
    private String neighborhood;

    @Column(nullable = false, length = 45,name= "district")
    private String district;

    @Column(nullable = false, length = 45,name= "city")
    private String city;

    @Column(nullable = false,name= "postal_code")
    private String postalCode;

    @ManyToMany(mappedBy = "addresses", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<User> users = new ArrayList<>();

}
