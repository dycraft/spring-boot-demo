package io.dycraft.samples.springbootdemo.api;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Ref: https://stackoverflow.com/questions/47269292/spring-mvc-automatically-return-204-when-rest-controller-response-type-is-void/47273789
 *
 * @author Dayang Li on 17/06/2019
 */
@ControllerAdvice
public class NoContentControllerAdvice implements ResponseBodyAdvice<Void> {

    @Override
    public boolean supports(MethodParameter returnType,
        Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().isAssignableFrom(void.class);
    }

    @Override
    public Void beforeBodyWrite(Void body, MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.NO_CONTENT);
        return body;
    }
}
