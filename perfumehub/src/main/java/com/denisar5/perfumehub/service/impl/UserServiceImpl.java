package com.denisar5.perfumehub.service.impl;

import com.denisar5.perfumehub.exception.DuplicateUserException;
import com.denisar5.perfumehub.model.dto.UserLoginDto;
import com.denisar5.perfumehub.model.dto.UserRegisterDto;
import com.denisar5.perfumehub.model.entity.UserEntity;
import com.denisar5.perfumehub.model.enums.UserRole;
import com.denisar5.perfumehub.repository.UserRepository;
import com.denisar5.perfumehub.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByUsername(userRegisterDto.getUsername())) {
            throw new DuplicateUserException("Username already exists");
        }

        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new DuplicateUserException("Email already exists");
        }

        UserRole role = userRepository.count() == 0 ? UserRole.ADMIN : UserRole.USER;

        UserEntity user = UserEntity.builder()
                .username(userRegisterDto.getUsername())
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);
    }

    @Override
    public UserEntity login(UserLoginDto userLoginDto) {
        UserEntity user = userRepository
                .findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        boolean passwordMatches = passwordEncoder.matches(
                userLoginDto.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid username or password");
        }

        return user;
    }
}