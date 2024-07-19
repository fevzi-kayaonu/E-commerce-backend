package com.workintech.ecommerce.mapper;


import com.workintech.ecommerce.dto.UserRegisterRequestDto;
import com.workintech.ecommerce.dto.UserRequestDto;
import com.workintech.ecommerce.dto.UserResponseDto;
import com.workintech.ecommerce.entity.User;

public class UserMapper {

    public static User userRegisterRequestDtoToUser(UserRegisterRequestDto userRegisterRequestDto){
        User user = new User();
        user.setEmail(userRegisterRequestDto.email());
        user.setFirstName(userRegisterRequestDto.firstName());
        user.setLastName(userRegisterRequestDto.lastName());
        user.setPassword(userRegisterRequestDto.password());
        return user;
    }

    public static User userRequestDtoToUser(UserRequestDto userRequestDto){
         User user = new User();
         user.setEmail(userRequestDto.email());
         return user;
    }

    public static UserResponseDto userToUserResponseDto (User user) {
        return new UserResponseDto(user.getFirstName(),user.getLastName(),user.getEmail()) ;
    }


}
