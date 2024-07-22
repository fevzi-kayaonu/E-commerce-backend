package com.workintech.ecommerce.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

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

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch=FetchType.LAZY)
    @JoinTable(name="user_credit_card",schema = "public",joinColumns = @JoinColumn(name="credit_card_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> users;


    public User setAddUser(User user){
        List<User> userList = getUsers();
        userList.add(user);
        setUsers(userList);
        return user;
    }

}