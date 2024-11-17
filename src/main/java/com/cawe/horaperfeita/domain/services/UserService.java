package com.cawe.horaperfeita.domain.services;

import com.cawe.horaperfeita.application.dtos.user.RegisterUserDTO;
import com.cawe.horaperfeita.domain.entities.BaseEntity;
import com.cawe.horaperfeita.domain.entities.User;
import com.cawe.horaperfeita.domain.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super.setRepository(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User create(RegisterUserDTO userDTO) {
        User user = new User();

        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(this.passwordEncoder.encode(userDTO.password()));

        return this.create(user);
    }
}
