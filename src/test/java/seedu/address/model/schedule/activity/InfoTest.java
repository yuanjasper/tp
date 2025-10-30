package seedu.address.model.schedule.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InfoTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Info(null));
    }

    @Test
    public void constructor_invalidInfo_throwsIllegalArgumentException() {
        String invalidInfo = "";
        assertThrows(IllegalArgumentException.class, () -> new Info(invalidInfo));
    }

    @Test
    public void isValidInfo() {
        // null info
        assertThrows(NullPointerException.class, () -> Info.isValidInfo(null));

        // invalid infos
        assertFalse(Info.isValidInfo("")); // empty string
        assertFalse(Info.isValidInfo(" ")); // spaces only

        // valid infos
        assertTrue(Info.isValidInfo("CS2103 Lecture"));
        assertTrue(Info.isValidInfo("a")); // one character
        assertTrue(Info.isValidInfo("A very long activity description for a very long activity")); // long info
    }

    @Test
    public void equals() {
        Info info = new Info("CS2103 Lecture");

        // same values -> returns true
        assertTrue(info.equals(new Info("CS2103 Lecture")));

        // same object -> returns true
        assertTrue(info.equals(info));

        // null -> returns false
        assertFalse(info.equals(null));

        // different types -> returns false
        assertFalse(info.equals(5.0f));

        // different values -> returns false
        assertFalse(info.equals(new Info("CS2103 Tutorial")));
    }
}
