package io.dycraft.samples.springbootdemo.exception;

import io.dycraft.samples.springbootdemo.error.ErrorCode;

/**
 * @author Dayang Li on 14/06/2019
 */
public class DuplicateKeyException extends ResponseCodeException {

    public DuplicateKeyException() {
        super(ErrorCode.DUPLICATE_KEY, "Duplicate Key");
    }

    public DuplicateKeyException(String message) {
        super(ErrorCode.DUPLICATE_KEY, message);
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(ErrorCode.DUPLICATE_KEY, message, cause);
    }
}
