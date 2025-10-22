package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteActivityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.activity.Day;
import seedu.address.model.schedule.activity.Timeslot;

/**
 * Parses input argument and creates a new DeleteActivityCommand object.
 */
public class DeleteActivityCommandParser implements Parser<DeleteActivityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteActivityCommand
     * and returns an DeleteActivityCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteActivityCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_SLOT);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_DATE, PREFIX_SLOT)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteActivityCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE, PREFIX_SLOT);
        Day day = ParserUtil.parseDay(argumentMultimap.getValue(PREFIX_DATE).get());
        Timeslot timeslot = ParserUtil.parseTimeslot(argumentMultimap.getValue(PREFIX_SLOT).get());

        return new DeleteActivityCommand(day, timeslot);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
