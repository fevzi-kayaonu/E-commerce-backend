package com.workintech.ecommerce.service;


import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.dto.AddressRequestDto;

public interface AddressService extends Service<Address> {
    Address addAddress(AddressRequestDto addressRequestDto, String user_mail);
}
