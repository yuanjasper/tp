package seedu.address.model.schedule.exceptions;

/**
 * Signals that the operation will result in duplicate Activities.
 */
public class DuplicateActivityException extends RuntimeException {
    public DuplicateActivityException() {
        super("Operation would result in duplicate activity");
    }
}
