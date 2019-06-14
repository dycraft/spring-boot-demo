package io.dycraft.samples.springbootdemo.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Dayang Li on 14/06/2019
 */
@Getter
@Setter
@NoArgsConstructor
public class Identity {
    private Long userId;
    private String username;
}
