package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Enum_Role;
import com.workintech.ecommerce.entity.Role;
import com.workintech.ecommerce.repository.UserRepository;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(String firstName, String lastName, String email, String password) {
        String encodePassword = passwordEncoder.encode(password);
        Role role = roleRepository.findByAuthority(Enum_Role.USER).orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setPassword(encodePassword);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAccountLocked(false);
        user.setEnabled(true);
        user.setRole(role);
       // role.addUser(user);
        return  userRepository.save(user);
    }

}
