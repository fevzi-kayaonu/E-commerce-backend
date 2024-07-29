package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CreditCardRequestDto;
import com.workintech.ecommerce.entity.CreditCard;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.repository.CreditCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    private CreditCard creditCard;
    private User user;
    private CreditCardRequestDto creditCardRequestDto;

    @BeforeEach
    void setUp() {
        creditCard = new CreditCard();
        creditCard.setId(1L);
        creditCard.setNo("1234567812345678");
        creditCard.setName("John Doe");
        creditCard.setExpireMonth(12);
        creditCard.setExpireYear(2025);
        creditCard.setCcv(123);

        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");

        creditCardRequestDto = new CreditCardRequestDto(
                "1234567812345678",
                "John Doe",
                12,
                2025,
                123
        );
    }

    @Test
    void findAll() {
        when(creditCardRepository.findAll()).thenReturn(new ArrayList<>());

        assertTrue(creditCardService.findAll().isEmpty());
    }

    @Test
    void findById() {
        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(creditCard));

        CreditCard foundCreditCard = creditCardService.findById(1L);

        assertNotNull(foundCreditCard);
        assertEquals("1234567812345678", foundCreditCard.getNo());
    }

    @Test
    void findById_NotFound() {
        when(creditCardRepository.findById(1L)).thenReturn(Optional.empty());

        ErrorException exception = assertThrows(ErrorException.class, () -> creditCardService.findById(1L));

        assertEquals("Credit Card not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void save() {
        when(creditCardRepository.save(creditCard)).thenReturn(creditCard);

        CreditCard savedCreditCard = creditCardService.save(creditCard);

        assertNotNull(savedCreditCard);
        assertEquals("1234567812345678", savedCreditCard.getNo());
    }

    @Test
    void delete() {
        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(creditCard));

        CreditCard deletedCreditCard = creditCardService.delete(1L);

        assertNotNull(deletedCreditCard);
        assertEquals("1234567812345678", deletedCreditCard.getNo());

        verify(creditCardRepository, times(1)).delete(creditCard);
    }

    @Test
    void addCreditCard() {
        when(userService.findByEmail("user@example.com")).thenReturn(user);

        CreditCard addedCreditCard = creditCardService.addCreditCard(creditCardRequestDto, "user@example.com");

        assertNotNull(addedCreditCard);
        assertEquals("1234567812345678", addedCreditCard.getNo());
        assertTrue(addedCreditCard.getUsers().contains(user));
    }
}
