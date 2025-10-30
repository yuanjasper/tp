package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalActivities.CS2103_TUTORIAL;
import static seedu.address.testutil.TypicalActivities.CS2106_LECTURE;
import static seedu.address.testutil.TypicalActivities.LAB;
import static seedu.address.testutil.TypicalActivities.MISMATCHED_TIME_ACTIVITY;
import static seedu.address.testutil.TypicalActivities.OVERLAPPING_ACTIVITY;
import static seedu.address.testutil.TypicalActivities.VOLUNTEERING;
import static seedu.address.testutil.TypicalActivities.getTypicalSchedule;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AddActivityCommandTest {

    private Model model = new ModelManager(getTypicalSchedule(), new UserPrefs());

    @Test
    public void constructor_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddActivityCommand(null));
    }

    @Test
    public void execute_activityAcceptedByModel_addSuccessful() throws Exception {
        AddActivityCommand addActivityCommand = new AddActivityCommand(CS2106_LECTURE);

        String expectedMessage = String.format(AddActivityCommand.MESSAGE_ADD_ACTIVITY_SUCCESS,
                Messages.format(CS2106_LECTURE));

        Model expectedModel = new ModelManager(model.getSchedule(), new UserPrefs());
        expectedModel.addActivity(CS2106_LECTURE);

        assertCommandSuccess(addActivityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateActivity_throwsCommandException() {
        AddActivityCommand addActivityCommand = new AddActivityCommand(CS2103_TUTORIAL);

        assertCommandFailure(addActivityCommand, model, AddActivityCommand.MESSAGE_DUPLICATE_ACTIVITY);
    }

    @Test
    public void execute_mismatchedTiming_throwsCommandException() {
        AddActivityCommand addActivityCommand = new AddActivityCommand(MISMATCHED_TIME_ACTIVITY);

        assertCommandFailure(addActivityCommand, model, AddActivityCommand.MESSAGE_MISMATCHED_TIMING);
    }

    @Test
    public void execute_overlappingTiming_throwsCommandException() {
        AddActivityCommand addActivityCommand = new AddActivityCommand(OVERLAPPING_ACTIVITY);

        assertCommandFailure(addActivityCommand, model, AddActivityCommand.MESSAGE_OVERLAP_TIMING);
    }

    @Test
    public void equals() {
        AddActivityCommand addActivityVolunteeringCommand = new AddActivityCommand(VOLUNTEERING);
        AddActivityCommand addActivityLabCommand = new AddActivityCommand(LAB);

        // same object -> returns true
        assertTrue(addActivityVolunteeringCommand.equals(addActivityVolunteeringCommand));

        // same values -> returns true
        AddActivityCommand addActivityVolunteeringCommandCopy = new AddActivityCommand(VOLUNTEERING);
        assertTrue(addActivityVolunteeringCommand.equals(addActivityVolunteeringCommandCopy));

        // different types -> returns false
        assertFalse(addActivityVolunteeringCommand.equals(1));

        // null -> returns false
        assertFalse(addActivityVolunteeringCommand.equals(null));

        // different activity -> returns false
        assertFalse(addActivityVolunteeringCommand.equals(addActivityLabCommand));
    }

    @Test
    public void toStringMethod() {
        AddActivityCommand addActivityCommand = new AddActivityCommand(CS2103_TUTORIAL);
        String expected = AddActivityCommand.class.getCanonicalName() + "{activityToAdd=" + CS2103_TUTORIAL + "}";
        assertEquals(expected, addActivityCommand.toString());
    }
}
