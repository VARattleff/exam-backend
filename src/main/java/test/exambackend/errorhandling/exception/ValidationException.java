package test.exambackend.errorhandling.exception;

public class ValidationException extends RuntimeException {
    /**
     * This constructor creates a new ValidationException with a message.
     */
    public ValidationException(String message) {
        super("Validation error: " + message);
    }
}

