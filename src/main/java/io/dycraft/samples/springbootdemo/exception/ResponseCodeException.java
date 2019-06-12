package io.dycraft.samples.springbootdemo.exception;

import io.dycraft.samples.springbootdemo.error.ErrorCode;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Dayang Li on 12/06/2019
 */
public class ResponseCodeException extends ResponseStatusException {

    private final ErrorCode code;

    public ResponseCodeException(ErrorCode code) {
        this(code, null, null);
    }

    public ResponseCodeException(ErrorCode code, @Nullable String reason) {
        this(code, reason, null);
    }

    public ResponseCodeException(ErrorCode code, @Nullable String reason, @Nullable Throwable cause) {
        super(code.getStatus(), reason, cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return getReason();
    }
}
