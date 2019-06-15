package io.dycraft.samples.springbootdemo.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Dayang Li on 14/06/2019
 */
@Data
public class UserSignUpDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
