package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.AddressResponseDto;
import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.dto.AddressRequestDto;

public class AddressMapper {

    public static Address addressRequestDtoToAddress(AddressRequestDto addressRequestDto) {
        Address address = new Address();
        address.setCity(addressRequestDto.city());
        address.setDescription(addressRequestDto.description());
        address.setDistrict(addressRequestDto.district());
        address.setStreet(addressRequestDto.street());
        address.setPostalCode(addressRequestDto.postalCode());
        address.setNeighborhood(addressRequestDto.neighborhood());
        return address;
    }

    public static AddressResponseDto addressToAddressResponseDto(Address address) {
        return new AddressResponseDto(address.getId(), address.getDescription(), address.getStreet(), address.getNeighborhood(),
                address.getDistrict(), address.getCity(), address.getPostalCode());
    }

}
