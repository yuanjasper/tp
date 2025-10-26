package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOURS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditHoursCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditHoursCommand object
 */
public class EditHoursCommandParser implements Parser<EditHoursCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditHoursCommand
     * and returns an EditHoursCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditHoursCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HOURS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditHoursCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap,PREFIX_HOURS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditHoursCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_HOURS);

        int editedHours = ParserUtil.parseInt(argMultimap.getValue(PREFIX_HOURS).get());

        return new EditHoursCommand(index, editedHours);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
