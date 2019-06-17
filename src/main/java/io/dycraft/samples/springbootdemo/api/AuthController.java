package io.dycraft.samples.springbootdemo.api;

import io.dycraft.samples.springbootdemo.dto.UserSignInDTO;
import io.dycraft.samples.springbootdemo.dto.UserSignUpDTO;
import io.dycraft.samples.springbootdemo.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity signIn(@Valid @RequestBody UserSignInDTO dto) {
        String token = authService.login(dto.getUsername(), dto.getPassword());
        return Response.ok("token", token);
    }

    @PostMapping("/sign-up")
    ResponseEntity signUp(@Valid @RequestBody UserSignUpDTO dto) {
        String token = authService.register(dto.getUsername(), dto.getPassword());
        return Response.ok("token", token);
    }
}
