package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements  AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
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
        return addressRepository.findById(id).orElseThrow(null) ;
    }

    @Transactional
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    // buna koymaya gerek var mı sonuçta dml
    @Transactional
    @Override
    public Address delete(Long id) {
        Address address = findById(id);
        addressRepository.delete(address);
        return address;
    }
}
