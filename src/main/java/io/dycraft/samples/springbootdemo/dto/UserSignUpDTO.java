package io.dycraft.samples.springbootdemo.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dayang Li on 14/06/2019
 */
@Getter
@Setter
public class UserSignUpDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
