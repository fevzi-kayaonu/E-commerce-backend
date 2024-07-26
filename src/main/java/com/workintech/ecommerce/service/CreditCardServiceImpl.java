package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CreditCardRequestDto;
import com.workintech.ecommerce.entity.CreditCard;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.mapper.CreditCardMapper;
import com.workintech.ecommerce.repository.CreditCardRepository;
import com.workintech.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService{


    private final CreditCardRepository creditCardRepository;
    private final UserService userService;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, UserService userService) {
        this.creditCardRepository = creditCardRepository;
        this.userService = userService;
    }

    @Override
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard findById(Long id) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(id);
        return optionalCreditCard
                .orElseThrow(() -> new ErrorException("Credit Card not found", HttpStatus.NOT_FOUND));

    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard delete(Long id) {
        CreditCard creditCard = findById(id);
        creditCardRepository.delete(creditCard);
        return creditCard;
    }

    @Transactional
    @Override
    public CreditCard addCreditCard(CreditCardRequestDto creditCardRequestDto, String user_mail) {
        User user = userService.findByEmail(user_mail);

            CreditCard creditCard = CreditCardMapper.creditCardRequestDtoCreditCard(creditCardRequestDto);
           // creditCard.addUser(user.get());
            user.addCreditCard(creditCard);
            // userRepository.save(user.get());
            return creditCard;

        //throw new RuntimeException("Credit Card not found");
    }

}
