package io.dycraft.samples.springbootdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author Dayang Li on 13/06/2019
 */
@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    private String issuer;

    private Long expiresIn;

    private Algorithm algorithm;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.issuer}") String issuer,
        @Value("${jwt.expires-in}") Long expiresIn, UserDetailsService userDetailsService) {

        this.issuer = issuer;
        this.expiresIn = expiresIn;
        this.algorithm = Algorithm.HMAC512(secret);
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String identity) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + expiresIn * 60 * 1000);
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(identity)
            .withIssuedAt(now)
            .withExpiresAt(expiredAt)
            .sign(algorithm);
    }

    public boolean verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(issuer)
            .build();
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    public static String getSubject(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (JWTDecodeException ex) {
            return null;
        }
    }

    public static String resolveAuthorization(String bearerToken) throws InvalidAuthorizationException {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            throw new InvalidAuthorizationException();
        }
    }

    public Authentication getAuthentication(String token) {
        String username = getSubject(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new JwtTokenBasedAuthentication(userDetails, token);
    }
}
