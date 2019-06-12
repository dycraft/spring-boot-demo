package io.dycraft.samples.springbootdemo.exception;

import io.dycraft.samples.springbootdemo.error.ErrorCode;
import lombok.Getter;
import org.springframework.lang.Nullable;

/**
 * @author Dayang Li on 12/06/2019
 */
public class ResourceNotFoundException extends ResponseCodeException {

    @Getter
    @Nullable
    private String resource;

    @Getter
    @Nullable
    private String id;

    public ResourceNotFoundException(@Nullable String reason) {
        super(ErrorCode.RESOURCE_NOT_FOUND, reason);
        this.resource = null;
        this.id = null;
    }

    public ResourceNotFoundException(String resource, @Nullable String id) {
        super(ErrorCode.RESOURCE_NOT_FOUND, initReason(resource, id));
        this.resource = resource;
        this.id = id;
    }

    public ResourceNotFoundException(String resource, @Nullable Long id) {
        this(resource, id.toString());
    }

    private static String initReason(String resource, @Nullable String id) {
        return "Resource '" + resource + (id != null ? ":" + id : "") + "' not found";
    }
}
