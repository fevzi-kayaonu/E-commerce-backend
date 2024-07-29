package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.UserBanRequestDto;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.repository.UserRepository;
import com.workintech.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

    public User banUser(UserBanRequestDto userBanRequestDto) {
        User user = userRepository.findById(userBanRequestDto.userId())
                .orElseThrow(() -> new ErrorException("User not found with id: " + userBanRequestDto.userId(), HttpStatus.NOT_FOUND));

        // Kullanıcıyı banlama işlemi
        user.setAccountLocked(true); // Kullanıcıyı kilitle
        user.setEnabled(false); // Kullanıcıyı devre dışı bırak
        // Ban sebebini kaydetmek için ek bir alan varsa, burada güncelleyebilirsiniz.

       return userRepository.save(user); // Güncellenmiş kullanıcıyı kaydet
    }


}