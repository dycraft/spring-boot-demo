package io.dycraft.samples.springbootdemo.service.impl;

import io.dycraft.samples.springbootdemo.exception.InvalidParameterException;
import io.dycraft.samples.springbootdemo.security.JwtTokenProvider;
import io.dycraft.samples.springbootdemo.service.AuthService;
import io.dycraft.samples.springbootdemo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Dayang Li on 14/06/2019
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException ex) {
            throw new InvalidParameterException("Invalid username/password supplied");
        }
        return jwtTokenProvider.generateToken(username);
    }

    @Override
    public String register(String username, String password) {
        userService.create(username, password);
        return jwtTokenProvider.generateToken(username);
    }
}
