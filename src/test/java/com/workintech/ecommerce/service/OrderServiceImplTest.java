package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.dto.PaymentRequestDto;
import com.workintech.ecommerce.entity.*;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private AddressService addressService;

    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderRequestDto orderRequestDto;
    private Order order;
    private User user;
    private Address address;
    private CreditCard creditCard;
    private Product product;
    private Payment payment;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1L);
        order.setDate(Instant.now());
        order.setStatus(Enum_OrderStatus.HAZIRLANIYOR);
        order.setAmount(100.0);
        order.setProducts(new ArrayList<>());

        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");

        address = new Address();
        address.setId(1L);

        creditCard = new CreditCard();
        creditCard.setId(1L);

        product = new Product();
        product.setId(1L);
        product.setPrice(100.0);

        payment = new Payment();
        payment.setId(1L);
        payment.setAmount(100.0);

        orderRequestDto = new OrderRequestDto(
                1L,
                Enum_OrderStatus.HAZIRLANIYOR, // addressId
                new PaymentRequestDto(
                        Enum_PaymentMethod.KART,
                        100.0,
                        1L
                ),
                List.of(1L)
        );
    }

    @Test
    void findAll() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        assertTrue(orderService.findAll().isEmpty());
    }

    @Test
    void findById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order foundOrder = orderService.findById(1L);

        assertNotNull(foundOrder);
        assertEquals(1L, foundOrder.getId());
    }

    @Test
    void findById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        ErrorException exception = assertThrows(ErrorException.class, () -> orderService.findById(1L));

        assertEquals("Order not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void save() {
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.save(order);

        assertNotNull(savedOrder);
        assertEquals(1L, savedOrder.getId());
    }

    @Test
    void delete() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order deletedOrder = orderService.delete(1L);

        assertNotNull(deletedOrder);
        assertEquals(1L, deletedOrder.getId());

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void addOrder() {
        when(creditCardService.findById(1L)).thenReturn(creditCard);
        when(addressService.findById(1L)).thenReturn(address);
        when(userService.findByEmail("user@example.com")).thenReturn(user);
        when(productService.findById(1L)).thenReturn(product);

        Order addedOrder = orderService.addOrder(orderRequestDto, "user@example.com");

        assertNotNull(addedOrder);
        assertEquals(Enum_OrderStatus.HAZIRLANIYOR, addedOrder.getStatus());
        assertEquals(100.0, addedOrder.getAmount());
        assertEquals(address, addedOrder.getAddress());
        assertEquals(user, addedOrder.getUser());
        assertTrue(addedOrder.getProducts().contains(product));
        assertNotNull(addedOrder.getPayment());
        assertEquals(creditCard, addedOrder.getPayment().getCreditCard());
    }
}
