package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.AddressRequestDto;
import com.workintech.ecommerce.dto.AddressResponseDto;
import com.workintech.ecommerce.dto.CreditCardRequestDto;
import com.workintech.ecommerce.dto.CreditCardResponseDto;
import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.dto.OrderResponseDto;
import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.entity.CreditCard;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.mapper.AddressMapper;
import com.workintech.ecommerce.mapper.CreditCardMapper;
import com.workintech.ecommerce.mapper.OrderMapper;
import com.workintech.ecommerce.service.AddressService;
import com.workintech.ecommerce.service.CreditCardService;
import com.workintech.ecommerce.service.OrderService;

import jakarta.validation.Valid;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private final OrderService orderService;
    private final AddressService addressService;
    private final CreditCardService creditCardService;

    public UserController(OrderService orderService, AddressService addressService, CreditCardService creditCardService) {
        this.orderService = orderService;
        this.addressService = addressService;
        this.creditCardService = creditCardService;
    }

    // Sipariş verme
    @PostMapping("/order")
    public OrderResponseDto saveOrder(@Valid @RequestBody OrderRequestDto orderRequestDto,
                                      @AuthenticationPrincipal String user_mail) {

        Order order = orderService.addOrder(orderRequestDto, user_mail);
        return OrderMapper.orderToOrderResponseDto(order);
    }

    // Adres ekleme
    @PostMapping("/address")
    public AddressResponseDto saveAddress(@Valid @RequestBody AddressRequestDto addressRequestDto,
                                          Principal principal) {//otantikeyt olmayı isteyen alanalra request atarken kullanıcı bilgileri elimize her seferinde nereden geliyor
        String user_mail =  principal.getName();
        System.out.println(user_mail);
        Address address = addressService.addAddress(addressRequestDto,user_mail);
        return AddressMapper.addressToAddressResponseDto(address);
    }

    // Kredi kartı ekleme
    @PostMapping("/credit-card")
    public CreditCardResponseDto saveCreditCard(@Valid @RequestBody CreditCardRequestDto creditCardRequestDto,
                                                @AuthenticationPrincipal String user_mail) {
        CreditCard creditCard = creditCardService.addCreditCard(creditCardRequestDto, user_mail);
        return CreditCardMapper.creditCardToCreditCardResponseDto(creditCard);
    }

}

