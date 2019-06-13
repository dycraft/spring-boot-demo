package io.dycraft.samples.springbootdemo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Dayang Li on 13/06/2019
 */
public class JwtTokenBasedAuthentication extends AbstractAuthenticationToken {

    private final UserDetails principal;
    private String token;

    public JwtTokenBasedAuthentication(UserDetails principal, String token) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.token = token;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public UserDetails getPrincipal() {
        return principal;
    }
}
