package seedu.address.model.schedule.activity;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent's an activity's timeslot in the schedule.
 * Guarantees: Immutable; is valid as declared in {@link #isValidTimeslot(String)}.
 */
public class Timeslot {

    public static final String MESSAGE_CONSTRAINTS =
            "Timeslot for an activity must adhere to the following \n"
                    + "1. The timing formats need to be 24h time, with no space \n"
                    + "2. The two timings must be separated by a hyphen, no space \n "
                    + "example: 16:00-18:00";

    public static final String VALIDATION_REGEX = "([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d";

    public final String value;

    /**
     * Constructs an {@code Timeslot}.
     *
     * @param timeslot A valid timeslot.
     */
    public Timeslot(String timeslot) {
        requireNonNull(timeslot);
        checkArgument(isValidTimeslot(timeslot), MESSAGE_CONSTRAINTS);
        value = timeslot;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidTimeslot(String test) {
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
        if (!(other instanceof Timeslot)) {
            return false;
        }

        Timeslot otherTimeslot = (Timeslot) other;
        return value.equals(otherTimeslot.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
