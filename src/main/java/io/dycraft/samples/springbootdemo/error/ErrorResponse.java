package io.dycraft.samples.springbootdemo.error;

import io.dycraft.samples.springbootdemo.exception.ResponseCodeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * @author Dayang Li on 12/06/2019
 */
@Getter
@Setter
public class ErrorResponse {

    private ErrorCode code;

    @Nullable
    private Object data;

    private String message;

    public ErrorResponse(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(ErrorCode code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ErrorResponse(ResponseCodeException exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    public ErrorResponse(ResponseCodeException exception, Object data) {
        this(exception);
        this.data = data;
    }
}
