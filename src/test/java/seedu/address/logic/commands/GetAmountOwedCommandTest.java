package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_MULTIPLE_ENTRIES;
import static seedu.address.logic.Messages.MESSAGE_NO_USER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameIsKeywordsPredicate;

public class GetAmountOwedCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameIsKeywordsPredicate firstPredicate = new NameIsKeywordsPredicate((Arrays.asList("Alex")));
        NameIsKeywordsPredicate secondPredicate = new NameIsKeywordsPredicate((Arrays.asList("Alice")));

        GetAmountOwedCommand firstCommand = new GetAmountOwedCommand(firstPredicate);
        GetAmountOwedCommand secondCommand = new GetAmountOwedCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same value -> returns true
        GetAmountOwedCommand firstCommandCopy = new GetAmountOwedCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different persons -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_wrongKeyword_noPersonFound() {
        String expectedMessage = MESSAGE_NO_USER;
        NameIsKeywordsPredicate predicate = preparePredicate("wrong");
        GetAmountOwedCommand command = new GetAmountOwedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_multiplePersonsFound() {
        String expectedMessage = MESSAGE_MULTIPLE_ENTRIES;
        NameIsKeywordsPredicate predicate = preparePredicate("meier");
        GetAmountOwedCommand command = new GetAmountOwedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_fullKeyword_personFound() {
        String expectedMessage = String.format("Here is the amount owed by %s: $%.2f",
                ALICE.getName(), ALICE.getAmountOwed());
        NameIsKeywordsPredicate predicate = preparePredicate("alice");
        GetAmountOwedCommand command = new GetAmountOwedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameIsKeywordsPredicate predicate = new NameIsKeywordsPredicate(Arrays.asList("keyword"));
        GetAmountOwedCommand getAmountOwedCommand = new GetAmountOwedCommand(predicate);
        String expected = GetAmountOwedCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, getAmountOwedCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameIsKeywordsPredicate}.
     */
    private NameIsKeywordsPredicate preparePredicate(String userInput) {
        return new NameIsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
