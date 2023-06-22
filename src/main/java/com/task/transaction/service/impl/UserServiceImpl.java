package com.task.transaction.service.impl;

import com.task.transaction.domain.User;
import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.LoginDto;
import com.task.transaction.dto.UserDto;
import com.task.transaction.dto.enums.ResponseStatus;
import com.task.transaction.repository.UserRepository;
import com.task.transaction.security.JwtUserDetails;
import com.task.transaction.security.jwt.TokenProvider;
import com.task.transaction.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public CommonResponse signUp(UserDto userDto) {
        if (userRepository.existsByUsernameOrEmail(userDto.getUsername(), userDto.getEmail())) {
            return new CommonResponse(ResponseStatus.FAILED, "User with given username or email is already exist");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return new CommonResponse(ResponseStatus.SUCCESS, "User " + user.getUsername() + " is successfully created");
    }

    @Override
    public CommonResponse login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        JwtUserDetails userDetails = (JwtUserDetails) authenticate.getPrincipal();
        String token = tokenProvider.generateToken(userDetails, userDetails.getEmail());
        return new CommonResponse(ResponseStatus.SUCCESS, token);
    }
}
