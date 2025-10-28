package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PaidFullCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PaidFullCommand object
 */
public class PaidFullCommandParser implements Parser<PaidFullCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PaidFullCommand
     * and returns a PaidFullCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PaidFullCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PaidFullCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidFullCommand.MESSAGE_USAGE), pe);
        }
    }
}
