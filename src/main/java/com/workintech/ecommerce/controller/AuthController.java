package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.service.RegisterService;
import com.workintech.ecommerce.dto.UserRegisterRequestDto;
import com.workintech.ecommerce.dto.UserResponseDto;
import com.workintech.ecommerce.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterService registerService;

    @Autowired
    public AuthController(RegisterService registerService) {
        this.registerService = registerService;
    }


    //kayıt olabilmeli
    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto){
         return UserMapper.userToUserResponseDto(registerService.register(userRegisterRequestDto.firstName(),userRegisterRequestDto.lastName(),
                                                 userRegisterRequestDto.email(),userRegisterRequestDto.password()));
    }


}
