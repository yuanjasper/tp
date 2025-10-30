package seedu.address.logic.commands;


import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_nonEmptyList_success() {
        SortCommand sortCommand = new SortCommand();

        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.sortListByDate(Model.BY_DATE_THEN_SLOT);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyList_failure() {
        // Model with no persons
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        SortCommand sortCommand = new SortCommand();

        String expectedMessage = "There is nothing in the list to sort.";

        assertCommandFailure(sortCommand, emptyModel, expectedMessage);
    }
}
