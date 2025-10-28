package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOURS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.BillablePerson;
import seedu.address.model.person.Person;

/**
 * Edits the unpaid hours of a specified tutee by the value given by the user.
 */
public class EditHoursCommand extends Command {
    public static final String COMMAND_WORD = "edithours";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the specified tutee's unpaid hours.\n"
            + "Parameters: "
            + PREFIX_HOURS + "UNPAID_HOURS\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_HOURS + "5";

    public static final String MESSAGE_EDIT_HOURS_SUCCESS = "Edited tutee unpaid hours: %1$s";
    public static final String MESSAGE_SAME_HOURS = "Hours are the same, no edits to be made.";
    public static final String MESSAGE_NEGATIVE_HOURS = "Unpaid hours cannot be negative.";

    private final Index index;
    private final int editedHours;

    /**
     * @param index of the person in the filtered person list to edit the hours
     * @param editedHours updated unpaid hours to edit the person with
     */
    public EditHoursCommand(Index index, int editedHours) {
        requireNonNull(index);
        requireNonNull(editedHours);

        this.index = index;
        this.editedHours = editedHours;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        BillablePerson updatedPerson;
        if (!(personToEdit instanceof BillablePerson)) {
            throw new CommandException(Messages.MESSAGE_INVALID_BILLABLE_PERSON);
        } else {
            BillablePerson billablePerson = (BillablePerson) personToEdit;
            if (editedHours < 0) {
                throw new CommandException(MESSAGE_NEGATIVE_HOURS);
            } else if (editedHours == billablePerson.getUnpaidHours()) {
                throw new CommandException(MESSAGE_SAME_HOURS);
            } else {
                updatedPerson = new BillablePerson(personToEdit.getName(), personToEdit.getPhone(),
                        personToEdit.getEmail(), personToEdit.getAddress(), personToEdit.getDate(), personToEdit.getSlot(),
                        personToEdit.getTags(), personToEdit.getContacts(), personToEdit.getRemark(), editedHours, 0);
                model.setPerson(personToEdit, updatedPerson);
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            }
            return new CommandResult(String.format(MESSAGE_EDIT_HOURS_SUCCESS, Messages.format(updatedPerson)));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EditHoursCommand)) {
            return false;
        }
        EditHoursCommand otherEditHoursCommand = (EditHoursCommand) other;
        return index.equals(otherEditHoursCommand.index)
                && editedHours == otherEditHoursCommand.editedHours;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", index)
                .add("editedHours", editedHours)
                .toString();
    }
}
