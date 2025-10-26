package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.BillablePerson;
import seedu.address.model.person.Person;

/**
 * Sets the number of unpaid hours and amount owed to 0 of a selected person using it's displayed index from the address book.
 */
public class PaidFullCommand extends Command{

    public static final String COMMAND_WORD = "paidfull";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the number of unpaid hours and amount owed to 0 of the person identified " +
            "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_PAID_FULL_SUCCESS = "Paid in full: %1$s";

    private final Index targetIndex;

    public PaidFullCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToPay = lastShownList.get(targetIndex.getZeroBased());
        BillablePerson updatedPerson;
        if (!(personToPay instanceof BillablePerson)) {
            throw new CommandException(Messages.MESSAGE_INVALID_BILLABLE_PERSON);
        } else {
            BillablePerson billablePerson = (BillablePerson) personToPay;
            if (billablePerson.getAmountOwed() == 0.0) {
                throw new CommandException(Messages.MESSAGE_INVALID_BILLING);
            }
            updatedPerson = new BillablePerson(personToPay.getName(), personToPay.getPhone(),
                    personToPay.getEmail(), personToPay.getAddress(), personToPay.getDate(), personToPay.getSlot(),
                    personToPay.getTags(), personToPay.getContacts(), personToPay.getRemark(), 0, 0.0);
            model.setPerson(personToPay, updatedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }
        return new CommandResult(String.format(MESSAGE_PAID_FULL_SUCCESS, Messages.format(updatedPerson)));
    }
}
