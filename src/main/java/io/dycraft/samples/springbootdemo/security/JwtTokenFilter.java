package io.dycraft.samples.springbootdemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dycraft.samples.springbootdemo.error.ErrorCode;
import io.dycraft.samples.springbootdemo.error.ErrorResponse;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Dayang Li on 13/06/2019
 */
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        // Note: this function performs the action before the antMatchers taking effect
        try {
            String token = JwtTokenProvider.resolveAuthorization(request.getHeader("Authorization"));
            if (jwtTokenProvider.verifyToken(token)) {
                try {
                    Authentication auth = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    filterChain.doFilter(request, response);
                } catch (UsernameNotFoundException ex) {
                    buildErrorResponse(new ErrorResponse(ErrorCode.FORBIDDEN, ex.getMessage()), response);
                }
            } else {
                // The most outer layer of spring, where we cannot use default error handler
                buildErrorResponse(new ErrorResponse(ErrorCode.UNAUTHORIZED, "Invalid token"), response);
            }
        } catch (InvalidAuthorizationException ignored) {
            filterChain.doFilter(request, response);
        }
    }

    private void buildErrorResponse(ErrorResponse errorResponse, HttpServletResponse response) {
        response.setStatus(errorResponse.getCode().getStatus().value());
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(response.getWriter(), errorResponse);
        } catch (IOException ex) {
            log.error("An error occurred when writing response under JwtTokenFilter", ex);
        }
    }
}
