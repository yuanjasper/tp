package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_OVERLAPPING_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_MISMATCHED_TIME_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_OVERLAPPING_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalActivities.getTypicalSchedule;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.activity.Tuition;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalSchedule(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getSchedule(), new UserPrefs());
        expectedModel.addActivity(new Tuition(validPerson));
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_hasMismatchedTime_throwsCommandException() {
        Person personWithMismatchedTime = new PersonBuilder()
                .withSlot(VALID_TIMESLOT_MISMATCHED_TIME_ACTIVITY).build();
        assertCommandFailure(new AddCommand(personWithMismatchedTime), model,
                AddActivityCommand.MESSAGE_MISMATCHED_TIMING);
    }

    @Test
    public void execute_hasOverlap_throwsCommandException() {
        Person personWithOverlappingTimeslot = new PersonBuilder().withDate(VALID_DAY_OVERLAPPING_ACTIVITY)
                .withSlot(VALID_TIMESLOT_OVERLAPPING_ACTIVITY).build();
        assertCommandFailure(new AddCommand(personWithOverlappingTimeslot), model,
                AddActivityCommand.MESSAGE_OVERLAP_TIMING);
    }
}
