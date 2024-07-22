package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
