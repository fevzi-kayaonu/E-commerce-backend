package com.workintech.ecommerce.service;


import com.workintech.ecommerce.dto.AddressRequestDto;
import com.workintech.ecommerce.entity.Address;

public interface AddressService extends Service<Address>{
    Address addAddress(AddressRequestDto addressRequestDto, String user_mail);
}
