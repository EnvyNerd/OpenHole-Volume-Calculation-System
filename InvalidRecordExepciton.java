package openholevolume.io;

/**
 * Custom checked exception class for handling invalid data records
 * when reading the openhole input CSV file.
 *
 * This exception is thrown when a record is incorrectly formatted,
 * missing values, or contains non-numeric fields that prevent parsing.
 *
 * Author: Witschi Mihan
 * Project: Openhole Volume Calculation (Java OOP)
 */
public class InvalidRecordException extends Exception {

    /**
     * Constructs a new InvalidRecordException with a specific message.
     *
     * @param message Description of the error.
     */
    public InvalidRecordException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidRecordException with a message and a cause.
     *
     * @param message Description of the error.
     * @param cause   The underlying cause (e.g., NumberFormatException).
     */
    public InvalidRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}
