package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.GetAddressCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new GetEmail object
 */
public class GetAddressCommandParser implements Parser<GetAddressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetEmail
     * and returns a GetAddress object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetAddressCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAddressCommand.MESSAGE_USAGE));
        }
        if (!Name.isValidName(trimmedArgs)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new GetAddressCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
