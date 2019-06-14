package io.dycraft.samples.springbootdemo.exception;

import io.dycraft.samples.springbootdemo.error.ErrorCode;

/**
 * @author Dayang Li on 14/06/2019
 */
public class InvalidParameterException extends ResponseCodeException {

    public InvalidParameterException() {
        super(ErrorCode.BAD_REQUEST, "Invalid parameter");
    }

    public InvalidParameterException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(ErrorCode.DUPLICATE_KEY, message, cause);
    }
}
