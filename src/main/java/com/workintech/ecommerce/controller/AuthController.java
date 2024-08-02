package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.service.RegisterService;
import com.workintech.ecommerce.dto.UserRegisterRequestDto;
import com.workintech.ecommerce.dto.UserResponseDto;
import com.workintech.ecommerce.mapper.UserMapper;
import com.workintech.ecommerce.service.UserService;
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
    private final UserService userService;

    @Autowired
    public AuthController(RegisterService registerService, UserService userService) {
        this.registerService = registerService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return UserMapper.userToUserResponseDto(registerService.register(userRegisterRequestDto.firstName(), userRegisterRequestDto.lastName(),
                userRegisterRequestDto.email(), userRegisterRequestDto.password()));
    }
}
