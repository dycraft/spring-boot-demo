package io.dycraft.samples.springbootdemo.exception;

import io.dycraft.samples.springbootdemo.error.ErrorCode;

/**
 * @author Dayang Li on 14/06/2019
 */
public class InvalidParameterException extends ResponseCodeException {

    public InvalidParameterException() {
        super(ErrorCode.INVALID_PARAMETER, "Invalid parameter");
    }

    public InvalidParameterException(String message) {
        super(ErrorCode.INVALID_PARAMETER, message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(ErrorCode.INVALID_PARAMETER, message, cause);
    }
}
