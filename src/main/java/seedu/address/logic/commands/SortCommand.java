package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.BY_DATE_THEN_SLOT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts and lists all tutee in the address book by the date and time of their class.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sortbydate";

    public static final String MESSAGE_SUCCESS = "All tutees sorted by date and time.";

    public static final String MESSAGE_EMPTY_LIST = "There is nothing in the list to sort.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (model.getFilteredPersonList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_LIST);
        }
        model.sortListByDate(BY_DATE_THEN_SLOT);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
