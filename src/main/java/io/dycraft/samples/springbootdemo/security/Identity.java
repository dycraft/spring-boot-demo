package io.dycraft.samples.springbootdemo.security;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dayang Li on 14/06/2019
 */
@Data
@NoArgsConstructor
public class Identity {
    private Long userId;
    private String username;
}
