package io.dycraft.samples.springbootdemo.exception;

import io.dycraft.samples.springbootdemo.error.ErrorCode;
import lombok.Getter;
import org.springframework.lang.Nullable;

/**
 * @author Dayang Li on 13/06/2019
 */
public class FileIOException extends ResponseCodeException {

    @Getter
    @Nullable
    private String fileName;

    public FileIOException() {
        super(ErrorCode.FILE_IO_ERROR, "An error occurred when processing file");
        this.fileName = null;
    }

    public FileIOException(String fileName) {
        super(ErrorCode.RESOURCE_NOT_FOUND, initReason(fileName));
        this.fileName = fileName;
    }

    private static String initReason(String fileName) {
        return "An error occurred when processing file '" + fileName + "'";
    }
}
