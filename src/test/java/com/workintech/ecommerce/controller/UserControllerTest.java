package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.*;
import com.workintech.ecommerce.entity.*;
import com.workintech.ecommerce.mapper.*;
import com.workintech.ecommerce.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private AddressService addressService;

    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private UserController userController;

    private OrderRequestDto orderRequestDto;
    private AddressRequestDto addressRequestDto;
    private CreditCardRequestDto creditCardRequestDto;
    private PaymentRequestDto paymentRequestDto;
    private OrderResponseDto orderResponseDto;
    private AddressResponseDto addressResponseDto;
    private CreditCardResponseDto creditCardResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        creditCardRequestDto = new CreditCardRequestDto(
                "1234567812345678",
                "John Doe",
                12,
                24,
                123
        );



        orderRequestDto = new OrderRequestDto(
                1L,
                Enum_OrderStatus.HAZIRLANIYOR,
                paymentRequestDto,
                List.of(1L)
        );

        addressRequestDto = new AddressRequestDto(
                "Home",
                "123 Main St",
                "Downtown",
                "Central",
                "Metropolis",
                "12345"
        );



        addressResponseDto = new AddressResponseDto(
                1L,
                "Home",
                "123 Main St",
                "Downtown",
                "Central",
                "Metropolis",
                "12345"
        );

        creditCardResponseDto = new CreditCardResponseDto(
                1L,
                "1234567812345678",
                "John Doe",
                12,
                24,
                123
        );

        orderResponseDto = new OrderResponseDto(
                1L,
                null,
                Enum_OrderStatus.HAZIRLANIYOR,
                addressResponseDto,
                null,
                100.0,
                null,
                null
        );
    }

    @Test
    void saveOrder() {
        Principal principal = () -> "john.doe@example.com";

        User mockUser = new User();
        mockUser.setFirstName("John");
        mockUser.setLastName("Doe");

        Address mockAddress = new Address();
        mockAddress.setId(1L);

        CreditCard mockCreditCard = new CreditCard();
        mockCreditCard.setId(1L);

        Payment mockPayment = new Payment();
        mockPayment.setId(1L);
        mockPayment.setMethod(Enum_PaymentMethod.KART);
        mockPayment.setAmount(10.99);
        mockPayment.setCreditCard(mockCreditCard);

        Order mockOrder = new Order();
        mockOrder.setId(1L);
        mockOrder.setAddress(mockAddress);
        mockOrder.setUser(mockUser);
        mockOrder.setPayment(mockPayment);


        when(orderService.addOrder(any(OrderRequestDto.class), eq(principal.getName()))).thenReturn(mockOrder);
        when(creditCardService.findById(anyLong())).thenReturn(mockCreditCard);


        AddressResponseDto mockAddressResponseDto = AddressMapper.addressToAddressResponseDto(mockAddress);
        UserResponseDto mockUserResponseDto = UserMapper.userToUserResponseDto(mockUser);
        PaymentResponseDto mockPaymentResponseDto = PaymentMapper.paymentToPaymentResponseDto(mockPayment);


        Instant now = Instant.now();
        OrderResponseDto expectedResponse = new OrderResponseDto(
                mockOrder.getId(),
                now,
                mockOrder.getStatus(),
                mockAddressResponseDto,
                mockUserResponseDto,
                mockOrder.getAmount(),
                null,
                mockPaymentResponseDto
        );

        OrderResponseDto response = userController.saveOrder(orderRequestDto, principal);

        assertNotNull(response);

        assertTrue(ChronoUnit.SECONDS.between(response.date(), now) < 1,
                "The date difference is too large");

        assertEquals(expectedResponse.id(), response.id());
        assertEquals(expectedResponse.status(), response.status());
        assertEquals(expectedResponse.addressResponseDto(), response.addressResponseDto());
        assertEquals(expectedResponse.userResponseDto(), response.userResponseDto());
        assertEquals(expectedResponse.amount(), response.amount());
        assertEquals(expectedResponse.paymentResponseDto(), response.paymentResponseDto());

        verify(orderService).addOrder(orderRequestDto, principal.getName());
    }

    @Test
    void saveAddress() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("john.doe@example.com");

        Address mockAddress = new Address();
        mockAddress.setId(1L);
        when(addressService.addAddress(addressRequestDto, principal.getName())).thenReturn(mockAddress);

        AddressResponseDto expectedResponse = AddressMapper.addressToAddressResponseDto(mockAddress);

        AddressResponseDto response = userController.saveAddress(addressRequestDto, principal);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
        verify(addressService).addAddress(addressRequestDto, principal.getName());

    }

    @Test
    void saveCreditCard() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("john.doe@example.com");

        CreditCard mockCreditCard = new CreditCard();
        mockCreditCard.setId(1L);
        when(creditCardService.addCreditCard(creditCardRequestDto, principal.getName())).thenReturn(mockCreditCard);

        CreditCardResponseDto expectedResponse = CreditCardMapper.creditCardToCreditCardResponseDto(mockCreditCard);

        CreditCardResponseDto response = userController.saveCreditCard(creditCardRequestDto, principal);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
        verify(creditCardService).addCreditCard(creditCardRequestDto, principal.getName());
    }
}
