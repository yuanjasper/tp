package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalActivities.CS2103_TUTORIAL;
import static seedu.address.testutil.TypicalActivities.LAB;
import static seedu.address.testutil.TypicalActivities.VOLUNTEERING;
import static seedu.address.testutil.TypicalActivities.getTypicalSchedule;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.activity.Activity;
import seedu.address.model.schedule.activity.Info;
import seedu.address.model.schedule.activity.Tuition;

public class DeleteActivityCommandTest {

    private static final Info toDelete = new Info("to delete");

    private Model model = new ModelManager(getTypicalSchedule(), new UserPrefs());

    @Test
    public void execute_activityInSchedule_success() {
        Activity activityToDelete = new Activity(toDelete,
                CS2103_TUTORIAL.getDay(), CS2103_TUTORIAL.getTimeslot());
        Optional<Activity> activityInScheduleToDelete = model.getSameDateTimeActivity(activityToDelete);
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(CS2103_TUTORIAL);

        String expectedMessage = String.format(DeleteActivityCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS,
                Messages.format(activityInScheduleToDelete.get()));

        Model expectedModel = new ModelManager(model.getSchedule(), new UserPrefs());
        expectedModel.deleteActivity(activityInScheduleToDelete.get());

        assertCommandSuccess(deleteActivityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_activityNotInSchedule_throwsCommandException() {
        Activity activityToDelete = new Activity(toDelete, LAB.getDay(), LAB.getTimeslot());
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(activityToDelete);

        assertCommandFailure(deleteActivityCommand, model, DeleteActivityCommand.MESSAGE_ACTIVITY_NOT_FOUND);
    }

    @Test
    public void execute_activityIsTuition_throwsCommandException() {
        Activity activityToDelete = new Tuition(toDelete,
                VOLUNTEERING.getDay(), VOLUNTEERING.getTimeslot());
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(activityToDelete);

        assertCommandFailure(deleteActivityCommand, model, DeleteActivityCommand.MESSAGE_ACTIVITY_IS_TUITION);
    }

    @Test
    public void equals() {
        DeleteActivityCommand deleteActivityVolunteeringCommand = new DeleteActivityCommand(VOLUNTEERING);
        DeleteActivityCommand deleteActivityLabCommand = new DeleteActivityCommand(LAB);

        // same object -> returns true
        assertTrue(deleteActivityVolunteeringCommand.equals(deleteActivityVolunteeringCommand));

        // same values -> returns true
        DeleteActivityCommand deleteActivityVolunteeringCommandCopy = new DeleteActivityCommand(VOLUNTEERING);
        assertTrue(deleteActivityVolunteeringCommand.equals(deleteActivityVolunteeringCommandCopy));

        // different types -> returns false
        assertFalse(deleteActivityVolunteeringCommand.equals(1));

        // null -> returns false
        assertFalse(deleteActivityVolunteeringCommand.equals(null));

        // different activity -> returns false
        assertFalse(deleteActivityVolunteeringCommand.equals(deleteActivityLabCommand));
    }

    @Test
    public void toStringMethod() {
        Activity activityToDelete = new Activity(toDelete, CS2103_TUTORIAL.getDay(),
                CS2103_TUTORIAL.getTimeslot());
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(activityToDelete);
        String expected = DeleteActivityCommand.class.getCanonicalName() + "{toDelete="
                + activityToDelete + "}";
        assertEquals(expected, deleteActivityCommand.toString());
    }
}
