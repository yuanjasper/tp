package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddActivityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.activity.Activity;
import seedu.address.model.schedule.activity.Day;
import seedu.address.model.schedule.activity.Info;
import seedu.address.model.schedule.activity.Timeslot;

/**
 * Parses input arguments and creates a new AddActivityCommand object
 */
public class AddActivityCommandParser implements Parser<AddActivityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddActivityCommand
     * and returns a AddActivityCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddActivityCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INFO, PREFIX_DATE, PREFIX_SLOT);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_INFO, PREFIX_DATE, PREFIX_SLOT)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddActivityCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INFO, PREFIX_DATE, PREFIX_SLOT);
        Info info = ParserUtil.parseInfo(argumentMultimap.getValue(PREFIX_INFO).get());
        Day day = ParserUtil.parseDay(argumentMultimap.getValue(PREFIX_DATE).get());
        Timeslot timeslot = ParserUtil.parseTimeslot(argumentMultimap.getValue(PREFIX_SLOT).get());

        Activity activity = new Activity(info, day, timeslot);
        return new AddActivityCommand(activity);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
