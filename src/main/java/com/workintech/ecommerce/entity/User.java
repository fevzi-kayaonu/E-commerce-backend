package com.workintech.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 45, name = "first_name")
    private String firstName;

    @Column(nullable = false, length = 45, name = "last_name")
    private String lastName;

    @Column(nullable = false, length = 45, unique = true, name = "email")
    private String email;

    @Column(nullable = false, length = 45, name = "password")
    private String password;

    @Column(name = "account_expiration_date")
    private LocalDateTime accountExpirationDate;

    @Column(name = "account_locked")
    private Boolean accountLocked;

    @Column(name = "credentials_expiration_date")
    private LocalDateTime credentialsExpirationDate;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_credit_card", schema = "public", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "credit_card_id"))
    private Set<CreditCard> creditCards = new HashSet<>();


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_address", schema = "public", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Review> reviews = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addReviews(Review review) {
        reviews.add(review);
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
        creditCard.addUser(this);
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

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User) o;
        if (!other.canEqual(this)) {
            return false;
        }

        Object this$id = this.getId();
        Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
            return false;
        }

        Object this$accountLocked = this.getAccountLocked();
        Object other$accountLocked = other.getAccountLocked();
        if (this$accountLocked == null ? other$accountLocked != null : !this$accountLocked.equals(other$accountLocked)) {
            return false;
        }

        Object this$enabled = this.getEnabled();
        Object other$enabled = other.getEnabled();
        if (this$enabled == null ? other$enabled != null : !this$enabled.equals(other$enabled)) {
            return false;
        }

        Object this$firstName = this.getFirstName();
        Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) {
            return false;
        }

        Object this$lastName = this.getLastName();
        Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) {
            return false;
        }

        Object this$email = this.getEmail();
        Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }

        Object this$password = this.getPassword();
        Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }

        Object this$accountExpirationDate = this.getAccountExpirationDate();
        Object other$accountExpirationDate = other.getAccountExpirationDate();
        if (this$accountExpirationDate == null ? other$accountExpirationDate != null : !this$accountExpirationDate.equals(other$accountExpirationDate)) {
            return false;
        }

        Object this$credentialsExpirationDate = this.getCredentialsExpirationDate();
        Object other$credentialsExpirationDate = other.getCredentialsExpirationDate();
        if (this$credentialsExpirationDate == null ? other$credentialsExpirationDate != null : !this$credentialsExpirationDate.equals(other$credentialsExpirationDate)) {
            return false;
        }

        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $accountLocked = this.getAccountLocked();
        result = result * 59 + ($accountLocked == null ? 43 : $accountLocked.hashCode());
        Object $enabled = this.getEnabled();
        result = result * 59 + ($enabled == null ? 43 : $enabled.hashCode());
        Object $firstName = this.getFirstName();
        result = result * 59 + ($firstName == null ? 43 : $firstName.hashCode());
        Object $lastName = this.getLastName();
        result = result * 59 + ($lastName == null ? 43 : $lastName.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $accountExpirationDate = this.getAccountExpirationDate();
        result = result * 59 + ($accountExpirationDate == null ? 43 : $accountExpirationDate.hashCode());
        Object $credentialsExpirationDate = this.getCredentialsExpirationDate();
        result = result * 59 + ($credentialsExpirationDate == null ? 43 : $credentialsExpirationDate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        Long var10000 = this.getId();
        return "User(id=" + var10000 + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", accountExpirationDate=" + this.getAccountExpirationDate() + ", accountLocked=" + this.getAccountLocked() + ", credentialsExpirationDate=" + this.getCredentialsExpirationDate() + ", enabled=" + this.getEnabled() + ", role=" + this.getRole() + ", creditCards=" + this.getCreditCards() + ", addresses=" + this.getAddresses() + ", reviews=" + this.getReviews() + ", orders=" + this.getOrders() + ")";
    }
}

