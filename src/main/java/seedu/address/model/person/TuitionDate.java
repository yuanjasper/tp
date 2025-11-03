package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;

/**
 * Represents a date of tuition in the address book.
 */
public class TuitionDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Date is the full name of the date of tuition being held, example: monday";
    public static final List<String> VALIDATION_REGEX = List.of("monday", "tuesday",
            "wednesday", "thursday", "friday", "saturday", "sunday");
    public final String date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public TuitionDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = date.substring(0, 1).toUpperCase() + date.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidDate(String test) {
        return VALIDATION_REGEX.contains(test.toLowerCase());
    }

    @Override
    public String toString() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionDate)) {
            return false;
        }

        TuitionDate otherDate = (TuitionDate) other;
        return date.equals(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
