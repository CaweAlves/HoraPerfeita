package com.cawe.horaperfeita.domain.services;

import com.cawe.horaperfeita.domain.entities.User;
import com.cawe.horaperfeita.domain.repositories.UserRepository;
import com.cawe.horaperfeita.infrastructure.config.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends BaseService<User> {
    private final UserRepository userRepository;

    @Autowired
    @Lazy
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        super.setRepository(userRepository);
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
