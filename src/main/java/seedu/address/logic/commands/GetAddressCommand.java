package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Gets the address of the persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class GetAddressCommand extends Command {

    public static final String COMMAND_WORD = "getaddress";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets the address of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public GetAddressCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_NO_USER);
        } else if (model.getFilteredPersonList().size() == 1) {
            return new CommandResult(
                    String.format("Here is the address of %s: %s", model.getFilteredPersonList().get(0).getName(),
                            model.getFilteredPersonList().get(0).getAddress()));
        } else {
            return new CommandResult(Messages.MESSAGE_MULTIPLE_ENTRIES);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GetAddressCommand)) {
            return false;
        }

        GetAddressCommand otherGetAddressCommand = (GetAddressCommand) other;
        return predicate.equals(otherGetAddressCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
