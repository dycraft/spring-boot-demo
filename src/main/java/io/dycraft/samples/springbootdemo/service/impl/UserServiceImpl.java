package io.dycraft.samples.springbootdemo.service.impl;

import io.dycraft.samples.springbootdemo.exception.DuplicateKeyException;
import io.dycraft.samples.springbootdemo.model.User;
import io.dycraft.samples.springbootdemo.repository.UserRepository;
import io.dycraft.samples.springbootdemo.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Dayang Li on 14/06/2019
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void create(String username, String password) {
        if (getByUsername(username).isPresent()) {
            throw new DuplicateKeyException("Username " + username + " is already in use");
        }
        String en = passwordEncoder.encode(password);
        userRepository.save(new User(username, en));
        System.out.println(en);
        log.info("New user [" + username + "] created");
    }
}
