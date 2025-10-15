package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.GetBillingContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new GetBillingContactCommand object
 */
public class GetBillingContactCommandParser implements Parser<GetBillingContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetEmail
     * and returns a GetBillingContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetBillingContactCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetBillingContactCommand.MESSAGE_USAGE));
        }
        if (!Name.isValidName(trimmedArgs)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new GetBillingContactCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
