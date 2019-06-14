package io.dycraft.samples.springbootdemo.config;

import io.dycraft.samples.springbootdemo.security.UserDetailsServiceImpl;
import io.dycraft.samples.springbootdemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Dayang Li on 14/06/2019
 */
@Configuration
public class DefaultBeanConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new UserDetailsServiceImpl(userService);
    }
}
