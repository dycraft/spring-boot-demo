package io.dycraft.samples.springbootdemo.security;

import java.util.ArrayList;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author Dayang Li on 13/06/2019
 */
public class JwtTokenBasedAuthentication extends AbstractAuthenticationToken {

    private final Identity principal;
    private String token;

    public JwtTokenBasedAuthentication(Identity principal, String token) {
        super(new ArrayList<>());
        this.principal = principal;
        this.token = token;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Identity getPrincipal() {
        return principal;
    }
}
