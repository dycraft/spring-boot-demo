package io.dycraft.samples.springbootdemo.security;

/**
 * @author Dayang Li on 13/06/2019
 */
public class InvalidAuthorizationException extends Exception {

    public InvalidAuthorizationException() {
        super("Invalid authorization");
    }

    public InvalidAuthorizationException(String message) {
        super(message);
    }

    public InvalidAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAuthorizationException(Throwable cause) {
        super(cause);
    }
}
