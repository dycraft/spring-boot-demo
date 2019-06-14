package io.dycraft.samples.springbootdemo.repository;

import io.dycraft.samples.springbootdemo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dayang Li on 14/06/2019
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
