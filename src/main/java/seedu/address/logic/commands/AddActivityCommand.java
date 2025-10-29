package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.activity.Activity;

/**
 * Adds an Activity to Schedule.
 */
public class AddActivityCommand extends Command {
    public static final String COMMAND_WORD = "addactivity";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the user's activity.\n"
            + "Parameters: "
            + PREFIX_INFO + "ACTIVITY_INFO "
            + PREFIX_DATE + "DAY_OF_WEEK "
            + PREFIX_SLOT + "START_TIME-END_TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INFO + "lesson "
            + PREFIX_DATE + "friday "
            + PREFIX_SLOT + "0900-1000";

    public static final String MESSAGE_ADD_ACTIVITY_SUCCESS = "Added activity: %1$s";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "Activity already exists in the schedule";
    public static final String MESSAGE_MISMATCHED_TIMING = "Start time of activity is equal to or after end time";
    public static final String MESSAGE_OVERLAP_TIMING = "There is already something scheduled during this timeslot";

    private final Activity activity;

    /**
     * Creates an AddActivityCommand to add the specified {@code Activity}.
     */
    public AddActivityCommand(Activity activity) {
        requireNonNull(activity);
        this.activity = activity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasActivity(activity)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
        }
        if (model.hasMismatchedTime(activity)) {
            throw new CommandException(MESSAGE_MISMATCHED_TIMING);
        }
        if (model.hasOverlap(activity)) {
            throw new CommandException(MESSAGE_OVERLAP_TIMING);
        }
        model.addActivity(activity);
        return new CommandResult(String.format(MESSAGE_ADD_ACTIVITY_SUCCESS, Messages.format(activity)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddActivityCommand)) {
            return false;
        }
        AddActivityCommand otherAddActivityCommand = (AddActivityCommand) other;
        return activity.equals(otherAddActivityCommand.activity);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("activityToAdd", activity)
                .toString();
    }
}
