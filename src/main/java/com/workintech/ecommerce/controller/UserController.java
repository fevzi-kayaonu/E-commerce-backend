package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.dto.OrderResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {



    //sipariş verme

    @PostMapping("/")
    OrderResponseDto save(@Valid @RequestBody OrderRequestDto orderRequestDto){

    }


    // kendine adrress ekleyebilir


    // kendine credi kartı ekleyebilir


  // isminide güncellesin


}
