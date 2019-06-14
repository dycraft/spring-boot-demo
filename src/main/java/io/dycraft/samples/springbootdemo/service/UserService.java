package io.dycraft.samples.springbootdemo.service;

import io.dycraft.samples.springbootdemo.model.User;
import java.util.Optional;

/**
 * @author Dayang Li on 14/06/2019
 */
public interface UserService {

    Optional<User> getById(Long userId);

    Optional<User> getByUsername(String username);

    void create(String username, String password);
}
