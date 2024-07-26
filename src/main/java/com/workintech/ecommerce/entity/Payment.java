package com.workintech.ecommerce.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payments", schema = "public")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 20,name="method")
    @Enumerated(EnumType.STRING)
    private Enum_PaymentMethod method;

    @Column(nullable = false, length = 20,name="status")
    @Enumerated(EnumType.STRING)
    private Enum_PaymentStatus status;

    @Column(nullable = false,name="date")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant date = Instant.now();

    @Column(nullable = false,name="amount")
    private Double amount;
  //bidirectional
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    //unidirectional
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;
   /*
    @Override
    public String toString() {
        Long var10000 = this.getId();
        return "Payment(id=" + var10000 + ", method=" + this.getMethod() + ", status=" + this.getStatus() + ", date=" + this.getDate() + ", amount=" + this.getAmount() + ", creditCard=" + this.getCreditCard() + ")";
    }
   */
}