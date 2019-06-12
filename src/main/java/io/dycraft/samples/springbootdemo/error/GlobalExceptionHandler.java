package io.dycraft.samples.springbootdemo.error;

import io.dycraft.samples.springbootdemo.exception.ResponseCodeException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * @author Dayang Li on 12/06/2019
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleExceptionInternal(
        Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST, ex.getMessage());
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            errorResponse.setCode(ErrorCode.ERROR);
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(errorResponse, headers, status);
    }

    @ExceptionHandler(ResponseCodeException.class)
    public ErrorResponse handleResponseCodeException(HttpServletResponse response, ResponseCodeException exception) {
        response.setStatus(exception.getStatus().value());
        return new ErrorResponse(exception);
    }
}
