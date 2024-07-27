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

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private CreditCardMapper creditCardMapper;

    @InjectMocks
    private UserController userController;

    private OrderRequestDto orderRequestDto;
    private AddressRequestDto addressRequestDto;
    private CreditCardRequestDto creditCardRequestDto;

    private OrderResponseDto orderResponseDto;
    private AddressResponseDto addressResponseDto;
    private CreditCardResponseDto creditCardResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orderRequestDto = new OrderRequestDto(
                1L,
                Enum_OrderStatus.HAZIRLANIYOR,
                null, // Assuming PaymentRequestDto can be null or set as needed
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

        creditCardRequestDto = new CreditCardRequestDto(
                "1234567812345678",
                "John Doe",
                12,
                24,
                123
        );

        orderResponseDto = new OrderResponseDto(
                1L,
                null, // Assuming this needs to be set or mocked
                Enum_OrderStatus.HAZIRLANIYOR,
                addressResponseDto,
                null, // Assuming UserResponseDto can be null or set as needed
                100.0,
                null, // Assuming this needs to be set or mocked
                null // Assuming this needs to be set or mocked
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
    }

    @Test
    void saveOrder() {
        Principal principal = () -> "john.doe@example.com";
        Order mockOrder = new Order();
        when(orderService.addOrder(orderRequestDto, principal.getName())).thenReturn(mockOrder);
        when(orderMapper.orderToOrderResponseDto(mockOrder)).thenReturn(orderResponseDto);

        OrderResponseDto response = userController.saveOrder(orderRequestDto, principal);
        assertNotNull(response);
        assertEquals(orderResponseDto, response);
        verify(orderService).addOrder(orderRequestDto, principal.getName());
        verify(orderMapper).orderToOrderResponseDto(mockOrder); // Check if the mapper is called
    }

    @Test
    void saveAddress() {
        Principal principal = () -> "john.doe@example.com";
        Address mockAddress = new Address();
        when(addressService.addAddress(addressRequestDto, principal.getName())).thenReturn(mockAddress);
        when(addressMapper.addressToAddressResponseDto(mockAddress)).thenReturn(addressResponseDto);

        AddressResponseDto response = userController.saveAddress(addressRequestDto, principal);
        assertNotNull(response);
        assertEquals(addressResponseDto, response);
        verify(addressService).addAddress(addressRequestDto, principal.getName());
        verify(addressMapper).addressToAddressResponseDto(mockAddress); // Check if the mapper is called
    }

    @Test
    void saveCreditCard() {
        Principal principal = () -> "john.doe@example.com";
        CreditCard mockCreditCard = new CreditCard();
        when(creditCardService.addCreditCard(creditCardRequestDto, principal.getName())).thenReturn(mockCreditCard);
        when(creditCardMapper.creditCardToCreditCardResponseDto(mockCreditCard)).thenReturn(creditCardResponseDto);

        CreditCardResponseDto response = userController.saveCreditCard(creditCardRequestDto, principal);
        assertNotNull(response);
        assertEquals(creditCardResponseDto, response);
        verify(creditCardService).addCreditCard(creditCardRequestDto, principal.getName());
        verify(creditCardMapper).creditCardToCreditCardResponseDto(mockCreditCard); // Check if the mapper is called
    }
}
