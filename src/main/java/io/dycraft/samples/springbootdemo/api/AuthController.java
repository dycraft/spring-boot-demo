package io.dycraft.samples.springbootdemo.api;

import io.dycraft.samples.springbootdemo.dto.UserSignInDTO;
import io.dycraft.samples.springbootdemo.dto.UserSignUpDTO;
import io.dycraft.samples.springbootdemo.service.AuthService;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dayang Li on 12/06/2019
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    Map signIn(@Valid @RequestBody UserSignInDTO dto) {
        String token = authService.login(dto.getUsername(), dto.getPassword());
        return Collections.singletonMap("token", token);
    }

    @PostMapping("/sign-up")
    Map signUp(@Valid @RequestBody UserSignUpDTO dto) {
        String token = authService.register(dto.getUsername(), dto.getPassword());
        return Collections.singletonMap("token", token);
    }
}
