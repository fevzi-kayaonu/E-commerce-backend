package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Address;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.repository.AddressRepository;
import com.workintech.ecommerce.dto.AddressRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Address address;
    private User user;
    private AddressRequestDto addressRequestDto;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setId(1L);
        address.setDescription("Test Address");
        address.setStreet("123 Test St");
        address.setNeighborhood("Test Neighborhood");
        address.setDistrict("Test District");
        address.setCity("Test City");
        address.setPostalCode("12345");

        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");

        addressRequestDto = new AddressRequestDto(
                "Test Address",
                "123 Test St",
                "Test Neighborhood",
                "Test District",
                "Test City",
                "12345"
        );
    }

    @Test
    void findAll() {
        when(addressRepository.findAll()).thenReturn(new ArrayList<>());

        assertTrue(addressService.findAll().isEmpty());
    }

    @Test
    void findById() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Address foundAddress = addressService.findById(1L);

        assertNotNull(foundAddress);
        assertEquals("Test Address", foundAddress.getDescription());
    }

    @Test
    void findById_NotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        ErrorException exception = assertThrows(ErrorException.class, () -> addressService.findById(1L));

        assertEquals("Address not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void save() {
        when(addressRepository.save(address)).thenReturn(address);

        Address savedAddress = addressService.save(address);

        assertNotNull(savedAddress);
        assertEquals("Test Address", savedAddress.getDescription());
    }

    @Test
    void delete() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Address deletedAddress = addressService.delete(1L);

        assertNotNull(deletedAddress);
        assertEquals("Test Address", deletedAddress.getDescription());

        verify(addressRepository, times(1)).delete(address);
    }

    @Test
    void addAddress() {
        when(userService.findByEmail("user@example.com")).thenReturn(user);

        Address addedAddress = addressService.addAddress(addressRequestDto, "user@example.com");

        assertNotNull(addedAddress);
        assertEquals("Test Address", addedAddress.getDescription());
        assertTrue(addedAddress.getUsers().contains(user));
    }
}
