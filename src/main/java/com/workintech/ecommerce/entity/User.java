package com.workintech.ecommerce.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 45,name="first_name")
    private String firstName;

    @Column(nullable = false, length = 45,name="last_name")
    private String lastName;

    @Column(nullable = false, length = 45, unique = true,name="email")
    private String email;

    @Column(nullable = false, length = 45,name="password")
    private String password;

    @Column(name = "account_expiration_date")
    private LocalDateTime accountExpirationDate;

    @Column(name = "account_locked")
    private Boolean accountLocked;

    @Column(name = "credentials_expiration_date")
    private LocalDateTime credentialsExpirationDate;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH} )
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="user_credit_card",schema = "public",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="credit_card_id"))
    private List<CreditCard> creditCards;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="user_address",schema = "public",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="address_id"))
    private List<Address> addresses; // hard dependency yaparasak 74. saıra gerek kalmıyor bize ne dezavantajı var burasda direk hardepencdency yapmanın

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Review> reviews ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Order> orders ;


    public void addAddress(Address address){
        if(addresses==null){
            addresses = new ArrayList<>();
        }
        addresses.add(address);
    }

    public void addCreditCard(CreditCard creditCard){
        if(creditCards==null){
            creditCards = new ArrayList<>();
        }
        creditCards.add(creditCard);
    }

    public void addOrder(Order order){
        if(orders==null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role != null ? List.of(role) : List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpirationDate == null || LocalDateTime.now().isBefore(accountExpirationDate);
    }

    @Override
    public boolean isAccountNonLocked() {

        return !Boolean.TRUE.equals(accountLocked);
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return credentialsExpirationDate == null || LocalDateTime.now().isBefore(credentialsExpirationDate);
    }

    @Override
    public boolean isEnabled() {

        return Boolean.TRUE.equals(enabled);
    }
}

