package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.GetAmountOwedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NameIsKeywordsPredicate;

/**
 * Parses input arguments and creates a new GetAmountOwed object
 */
public class GetAmountOwedCommandParser implements Parser<GetAmountOwedCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GetAmountOwed
     * and returns a GetAmountOwed object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetAmountOwedCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAmountOwedCommand.MESSAGE_USAGE));
        }
        if (!Name.isValidName(trimmedArgs)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new GetAmountOwedCommand(new NameIsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
