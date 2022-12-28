package singletonPattern.exception;

public class SessionCreationException extends RuntimeException {
    public SessionCreationException(String message) {
        super(message);
    }
}
