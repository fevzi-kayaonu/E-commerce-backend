package com.workintech.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "adresses", schema = "public")
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

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch=FetchType.LAZY)
    @JoinTable(name="user_address",schema = "public",joinColumns = @JoinColumn(name="address_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> users;

}
