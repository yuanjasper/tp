package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.activity.Activity;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command, type ‘help’ to show available commands.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_NO_USER = "No such user exists!";
    public static final String MESSAGE_MULTIPLE_ENTRIES = "There are multiple entries for search requirements";
    public static final String MESSAGE_INVALID_BILLABLE_PERSON = "This person is not a tutee! No bill records found";
    public static final String MESSAGE_INVALID_BILLING = "Tutee has no outstanding bills!";
    public static final String MESSAGE_EXCESS_BILLING = "Amount collected exceeds outstanding bills! Check your input again.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     * Includes billing information if the person is a {@code BillablePerson}.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Date: ")
                .append(person.getDate())
                .append("; Slot: ")
                .append(person.getSlot())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        builder.append("; Billing Contacts: ");
        person.getContacts().forEach(builder::append);
        if (person instanceof seedu.address.model.person.BillablePerson) {
            seedu.address.model.person.BillablePerson billable =
                    (seedu.address.model.person.BillablePerson) person;

            builder.append("; Unpaid Hours: ")
                    .append(billable.getUnpaidHours())
                    .append("; Amount Owed: $")
                    .append(String.format("%.2f", billable.getAmountOwed()));
        }
        return builder.toString();
    }

    /**
     * Formats the {@code activity} for display to the user.
     */
    public static String format(Activity activity) {
        final StringBuilder builder = new StringBuilder();
        builder.append(activity.getInfo())
                .append("; Day:  ")
                .append(activity.getDay())
                .append("; Timeslot: ")
                .append(activity.getTimeslot());
        return builder.toString();
    }

}
