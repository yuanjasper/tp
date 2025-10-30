package seedu.address.model.schedule.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CS2106_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_CS2106_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS2106_TUTORIAL;
import static seedu.address.testutil.TypicalActivities.CS2103_TUTORIAL;
import static seedu.address.testutil.TypicalActivities.CS2106_TUTORIAL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ActivityBuilder;

public class ActivityTest {

    @Test
    public void equals() {
        // same values -> returns true
        Activity copyCS2103Tutorial = new ActivityBuilder(CS2103_TUTORIAL).build();
        assertTrue(CS2103_TUTORIAL.equals(copyCS2103Tutorial));

        // same object -> returns true
        assertTrue(CS2103_TUTORIAL.equals(CS2103_TUTORIAL));

        // null -> returns false
        assertFalse(CS2103_TUTORIAL.equals(null));

        // different type -> returns false
        assertFalse(CS2103_TUTORIAL.equals(5.0f));

        // different activity -> returns false
        assertFalse(CS2103_TUTORIAL.equals(CS2106_TUTORIAL));

        // different info -> returns false
        Activity editedCS2103Tutorial = new ActivityBuilder(CS2103_TUTORIAL)
                .withInfo(VALID_INFO_CS2106_TUTORIAL).build();
        assertFalse(CS2103_TUTORIAL.equals(editedCS2103Tutorial));

        // different day -> returns false
        editedCS2103Tutorial = new ActivityBuilder(CS2103_TUTORIAL)
                .withDay(VALID_DAY_CS2106_TUTORIAL).build();
        assertFalse(CS2103_TUTORIAL.equals(editedCS2103Tutorial));

        // different timeslot -> returns false
        editedCS2103Tutorial = new ActivityBuilder(CS2103_TUTORIAL)
                .withTimeslot(VALID_TIMESLOT_CS2106_TUTORIAL).build();
        assertFalse(CS2103_TUTORIAL.equals(editedCS2103Tutorial));
    }

    @Test
    public void toStringMethod() {
        String expected = Activity.class.getCanonicalName() + "{info=" + CS2103_TUTORIAL.getInfo()
                + ", day=" + CS2103_TUTORIAL.getDay() + ", timeslot=" + CS2103_TUTORIAL.getTimeslot() + "}";
        assertEquals(expected, CS2103_TUTORIAL.toString());
    }
}
