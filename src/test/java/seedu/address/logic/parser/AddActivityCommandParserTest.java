package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_CS2106_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.INFO_DESC_CS2106_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INFO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_CS2106_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CS2106_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_CS2106_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS2106_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalActivities.CS2106_LECTURE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddActivityCommand;
import seedu.address.model.schedule.activity.Day;
import seedu.address.model.schedule.activity.Info;
import seedu.address.model.schedule.activity.Timeslot;

public class AddActivityCommandParserTest {

    private AddActivityCommandParser parser = new AddActivityCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INFO_DESC_CS2106_LECTURE
                + DAY_DESC_CS2106_LECTURE + TIMESLOT_DESC_CS2106_LECTURE,
                new AddActivityCommand(CS2106_LECTURE));
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddActivityCommand.MESSAGE_USAGE);

        // missing info prefix
        assertParseFailure(parser, VALID_INFO_CS2106_LECTURE + DAY_DESC_CS2106_LECTURE
                + TIMESLOT_DESC_CS2106_LECTURE, expectedMessage);

        // missing day prefix
        assertParseFailure(parser, INFO_DESC_CS2106_LECTURE + VALID_DAY_CS2106_LECTURE
                + TIMESLOT_DESC_CS2106_LECTURE, expectedMessage);

        // missing timeslot prefix
        assertParseFailure(parser, INFO_DESC_CS2106_LECTURE + DAY_DESC_CS2106_LECTURE
                + VALID_TIMESLOT_CS2106_LECTURE, expectedMessage);

        // all prefix missing
        assertParseFailure(parser, VALID_INFO_CS2106_LECTURE + VALID_DAY_CS2106_LECTURE
                + VALID_TIMESLOT_CS2106_LECTURE, expectedMessage);
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedActivityString = INFO_DESC_CS2106_LECTURE + DAY_DESC_CS2106_LECTURE
                + TIMESLOT_DESC_CS2106_LECTURE;

        // multiple infos
        assertParseFailure(parser, INFO_DESC_CS2106_LECTURE + validExpectedActivityString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INFO));

        // multiple days
        assertParseFailure(parser, DAY_DESC_CS2106_LECTURE + validExpectedActivityString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // multiple timeslots
        assertParseFailure(parser, TIMESLOT_DESC_CS2106_LECTURE + validExpectedActivityString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SLOT));

        // multiple fields repeated
        assertParseFailure(parser, validExpectedActivityString
                + INFO_DESC_CS2106_LECTURE + DAY_DESC_CS2106_LECTURE + TIMESLOT_DESC_CS2106_LECTURE
                + validExpectedActivityString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INFO, PREFIX_DATE, PREFIX_SLOT));

        // invalid value followed by valid value

        // invalid info
        assertParseFailure(parser, INVALID_INFO_DESC + validExpectedActivityString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INFO));

        // invalid day
        assertParseFailure(parser, INVALID_DAY_DESC + validExpectedActivityString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // invalid timeslot
        assertParseFailure(parser, INVALID_TIMESLOT_DESC + validExpectedActivityString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SLOT));

        // valid value followed by invalid value

        // invalid info
        assertParseFailure(parser, validExpectedActivityString + INVALID_INFO_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INFO));

        // invalid day
        assertParseFailure(parser, validExpectedActivityString + INVALID_DAY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // invalid timeslot
        assertParseFailure(parser, validExpectedActivityString + INVALID_TIMESLOT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SLOT));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid info
        assertParseFailure(parser, INVALID_INFO_DESC + DAY_DESC_CS2106_LECTURE
                + TIMESLOT_DESC_CS2106_LECTURE, Info.MESSAGE_CONSTRAINTS);

        // invalid day
        assertParseFailure(parser, INFO_DESC_CS2106_LECTURE + INVALID_DAY_DESC
                + TIMESLOT_DESC_CS2106_LECTURE, Day.MESSAGE_CONSTRAINTS);

        // invalid timeslot
        assertParseFailure(parser, INFO_DESC_CS2106_LECTURE + DAY_DESC_CS2106_LECTURE
                + INVALID_TIMESLOT_DESC, Timeslot.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_INFO_DESC + DAY_DESC_CS2106_LECTURE
                + INVALID_TIMESLOT_DESC, Info.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INFO_DESC_CS2106_LECTURE + DAY_DESC_CS2106_LECTURE
                + TIMESLOT_DESC_CS2106_LECTURE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddActivityCommand.MESSAGE_USAGE));
    }
}
