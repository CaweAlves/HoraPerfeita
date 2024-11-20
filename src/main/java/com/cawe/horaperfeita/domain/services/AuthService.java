package com.cawe.horaperfeita.domain.services;

import com.cawe.horaperfeita.application.dtos.user.LoginUserDTO;
import com.cawe.horaperfeita.application.dtos.user.RegisterUserDTO;
import com.cawe.horaperfeita.application.dtos.user.ResponseUserTokenDTO;
import com.cawe.horaperfeita.domain.entities.User;
import com.cawe.horaperfeita.domain.repositories.UserRepository;
import com.cawe.horaperfeita.infrastructure.config.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService extends BaseService<User> {

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    @Lazy
    public AuthService(PasswordEncoder passwordEncoder, TokenService tokenService, UserService userService, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userService = userService;
        this.userRepository = userRepository;
        super.setRepository(this.userRepository);
    }

    public User create(RegisterUserDTO userDTO) throws ResponseStatusException {
        if (this.userService.findByUsername(userDTO.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        User user = new User();

        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(this.passwordEncoder.encode(userDTO.password()));

        return this.create(user);
    }

    public ResponseUserTokenDTO login(LoginUserDTO request) throws ResponseStatusException {
        User user = this.userService.findByUsername(request.username()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User credentials do not match"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User credentials do not match");
        }
        String token = this.tokenService.generateToken(user);

        return new ResponseUserTokenDTO(user.getUsername(), token);
    }
}
