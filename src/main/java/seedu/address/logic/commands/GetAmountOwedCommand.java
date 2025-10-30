package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Gets the amount owed of the persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class GetAmountOwedCommand extends Command {

    public static final String COMMAND_WORD = "getamountowed";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets the amount owed of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice wonderland";

    private final NameIsKeywordsPredicate predicate;

    public GetAmountOwedCommand(NameIsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_NO_USER);
        } else if (model.getFilteredPersonList().size() == 1) {
            Person person = model.getFilteredPersonList().get(0);

            if (person instanceof seedu.address.model.person.BillablePerson) {
                seedu.address.model.person.BillablePerson billablePerson =
                        (seedu.address.model.person.BillablePerson) person;

                double amountOwed = billablePerson.getAmountOwed();

                return new CommandResult(
                    String.format("Here is the amount owed by %s: $%.2f", billablePerson.getName(),
                            amountOwed));
            } else {
                return new CommandResult(Messages.MESSAGE_INVALID_BILLABLE_PERSON);
            }
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
        if (!(other instanceof GetAmountOwedCommand)) {
            return false;
        }

        GetAmountOwedCommand otherGetAmountOwedCommand = (GetAmountOwedCommand) other;
        return predicate.equals(otherGetAmountOwedCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
