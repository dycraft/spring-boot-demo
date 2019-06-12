package io.dycraft.samples.springbootdemo.error;

import org.springframework.http.HttpStatus;

/**
 * @author Dayang Li on 12/06/2019
 */
public enum ErrorCode {

    ERROR(HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST(HttpStatus.BAD_REQUEST),

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus status;

    private ErrorCode(final HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
