package seedu.address.model.schedule.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an activity's info in the schedule.
 * Guarantees: Immutable; is valid as declared in {@link #isValidInfo(String)}.
 */
public class Info {

    public static final String MESSAGE_CONSTRAINTS = "Info can take any values, and it should not be blank";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        value = info;
    }

    /**
     * Returns true if a given string is a valid info.
     */
    public static boolean isValidInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Info)) {
            return false;
        }

        Info otherInfo = (Info) other;
        return value.equals(otherInfo.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
