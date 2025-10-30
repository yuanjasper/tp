package seedu.address.model.schedule.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeslotTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timeslot(null));
    }

    @Test
    public void constructor_invalidTimeslot_throwsIllegalArgumentException() {
        String invalidTimeslot = "";
        assertThrows(IllegalArgumentException.class, () -> new Timeslot(invalidTimeslot));
    }

    @Test
    public void isValidTimeslot() {
        // null timeslot
        assertThrows(NullPointerException.class, () -> Timeslot.isValidTimeslot(null));

        // invalid timeslots
        assertFalse(Timeslot.isValidTimeslot("")); // empty string
        assertFalse(Timeslot.isValidTimeslot(" ")); // spaces only
        assertFalse(Timeslot.isValidTimeslot("1:00pm-2:00pm")); // not 24h format
        assertFalse(Timeslot.isValidTimeslot("1300-1400")); // no colon used
        assertFalse(Timeslot.isValidTimeslot("13:00 - 14:00")); // space between timings
        assertFalse(Timeslot.isValidTimeslot("13:00:00 - 14:00:00")); // seconds included

        // valid timeslots
        assertTrue(Timeslot.isValidTimeslot("13:00-14:00"));
        assertTrue(Timeslot.isValidTimeslot("13:00-13:01"));
        assertTrue(Timeslot.isValidTimeslot("00:00-23:59"));
    }

    @Test
    public void equals() {
        Timeslot timeslot = new Timeslot("13:00-14:00");

        // same values -> returns true
        assertTrue(timeslot.equals(new Timeslot("13:00-14:00")));

        // same object -> returns true
        assertTrue(timeslot.equals(timeslot));

        // null -> returns false
        assertFalse(timeslot.equals(null));

        // different types -> returns false
        assertFalse(timeslot.equals(5.0f));

        // different values -> returns false
        assertFalse(timeslot.equals(new Timeslot("13:30-14:30")));
    }
}
