package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.tag.BillingContact;

/**
 * Gets the Billing Contact of the persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class GetBillingContactCommand extends Command {

    public static final String COMMAND_WORD = "getbillingcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets the billing contact of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice wonderland";

    private final NameIsKeywordsPredicate predicate;

    public GetBillingContactCommand(NameIsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_NO_USER);
        } else if (model.getFilteredPersonList().size() == 1) {
            String billingContact = model.getFilteredPersonList().get(0).getContacts()
                                    .stream().map(BillingContact::toString)
                                    .reduce("", (x, y) -> x + " " + y);
            String message = billingContact.isBlank()
                    ? String.format("No billing contact found for %s. Returning phone number instead: %s",
                    model.getFilteredPersonList().get(0).getName(), model.getFilteredPersonList().get(0).getPhone())
                    : String.format("Here is the billing contact of %s: %s",
                    model.getFilteredPersonList().get(0).getName(), billingContact);
            return new CommandResult(message);
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
        if (!(other instanceof GetBillingContactCommand)) {
            return false;
        }

        GetBillingContactCommand otherGetBillingContactCommand = (GetBillingContactCommand) other;
        return predicate.equals(otherGetBillingContactCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
