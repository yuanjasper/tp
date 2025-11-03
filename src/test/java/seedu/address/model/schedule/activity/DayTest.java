package seedu.address.model.schedule.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
    }

    @Test
    public void constructor_invalidDay_throwsIllegalArgumentException() {
        String invalidDay = "";
        assertThrows(IllegalArgumentException.class, () -> new Day(invalidDay));
    }

    @Test
    public void isValidDay() {
        // null day
        assertThrows(NullPointerException.class, () -> Day.isValidDay(null));

        // invalid days
        assertFalse(Day.isValidDay("")); // empty string
        assertFalse(Day.isValidDay(" ")); // spaces only
        assertFalse(Day.isValidDay("mon")); // abbreviated form of day
        assertFalse(Day.isValidDay("1")); // numerical representation of day

        // valid days
        assertTrue(Day.isValidDay("monday"));
        assertTrue(Day.isValidDay("Monday")); // capitalised day
        assertTrue(Day.isValidDay("MONDAY")); // fully capitalised string
        assertTrue(Day.isValidDay("mONdaY")); // randomly capitalised string
    }

    @Test
    public void equals() {
        Day day = new Day("monday");

        // same values -> returns true
        assertTrue(day.equals(new Day("monday")));

        // same object -> returns true
        assertTrue(day.equals(day));

        // fully capitalised day -> returns true
        assertTrue(day.equals(new Day("MONDAY")));

        // randomly capitalised day -> returns true
        assertTrue(day.equals(new Day("mOnDAy")));

        // null -> returns false
        assertFalse(day.equals(null));

        // different types -> returns false
        assertFalse(day.equals(5.0f));

        // different values -> returns false
        assertFalse(day.equals(new Day("tuesday")));
    }
}
