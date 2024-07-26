package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.mapper.AddressMapper;
import com.workintech.ecommerce.repository.AddressRepository;
import com.workintech.ecommerce.repository.UserRepository;
import com.workintech.ecommerce.dto.AddressRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements  AddressService{

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        /*
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            return address.get();
        }
        */
        return addressRepository.findById(id).orElseThrow(() -> new ErrorException("Address not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }


    @Override
    public Address delete(Long id) {
        Address address = findById(id);
        addressRepository.delete(address);
        return address;
    }

    @Transactional
    @Override
    public Address addAddress(AddressRequestDto addressRequestDto, String user_mail) {
       User user = userService.findByEmail(user_mail);
            Address address = AddressMapper.addressRequestDtoToAddress(addressRequestDto);
           // user.get().addAddress(address);
            address.addUser(user);
            return address;
    }
}
