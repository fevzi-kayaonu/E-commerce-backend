package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CreditCardRequestDto;
import com.workintech.ecommerce.entity.CreditCard;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.mapper.CreditCardMapper;
import com.workintech.ecommerce.repository.CreditCardRepository;
import com.workintech.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService{


    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, UserRepository userRepository) {
        this.creditCardRepository = creditCardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard findById(Long id) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(id);
        return optionalCreditCard.orElse(null);
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard delete(Long id) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(id);
        if (optionalCreditCard.isPresent()) {
            creditCardRepository.deleteById(id);
            return optionalCreditCard.get();
        }
        return null;
    }

    @Transactional
    @Override
    public CreditCard addCreditCard(CreditCardRequestDto creditCardRequestDto, String user_mail) {
        Optional<User> user = userRepository.findByEmail(user_mail);
        if (user.isPresent()) {

            CreditCard creditCard = CreditCardMapper.creditCardRequestDtoCreditCard(creditCardRequestDto);
            creditCard.addUser(user.get());
            // user.get().addCreditCard(creditCard);
            // userRepository.save(user.get());
            return save(creditCard);
        }
        throw new RuntimeException("Credit Card not found");
    }

}
