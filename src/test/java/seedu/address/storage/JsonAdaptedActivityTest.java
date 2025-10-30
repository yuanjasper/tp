package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedActivity.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedActivity.TYPE_INCORRECT_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalActivities.CS2103_LECTURE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.activity.Day;
import seedu.address.model.schedule.activity.Info;
import seedu.address.model.schedule.activity.Timeslot;

public class JsonAdaptedActivityTest {
    private static final String INVALID_TYPE = "something";
    private static final String INVALID_INFO = " ";
    private static final String INVALID_DAY = "mon";
    private static final String INVALID_TIMESLOT = "2000 - 2100";

    private static final String VALID_TYPE = "activity";
    private static final String VALID_INFO = CS2103_LECTURE.getInfo().toString();
    private static final String VALID_DAY = CS2103_LECTURE.getDay().toString();
    private static final String VALID_TIMESLOT = CS2103_LECTURE.getTimeslot().toString();

    @Test
    public void toModelType_validActivityDetails_returnsActivity() throws Exception {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(CS2103_LECTURE);
        assertEquals(CS2103_LECTURE, activity.toModelType());
    }

    @Test
    public void toModelType_nullInfo_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(VALID_TYPE, null, VALID_DAY, VALID_TIMESLOT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Info.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidInfo_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(VALID_TYPE, INVALID_INFO, VALID_DAY, VALID_TIMESLOT);
        String expectedMessage = Info.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullDay_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(VALID_TYPE, VALID_INFO, null, VALID_TIMESLOT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(VALID_TYPE, VALID_INFO, INVALID_DAY, VALID_TIMESLOT);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullTimeslot_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(VALID_TYPE, VALID_INFO, VALID_DAY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Timeslot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidTimeslot_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(VALID_TYPE, VALID_INFO, VALID_DAY, INVALID_TIMESLOT);
        String expectedMessage = Timeslot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(null, VALID_INFO, VALID_DAY, VALID_TIMESLOT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Type");
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(INVALID_TYPE, VALID_INFO, VALID_DAY, VALID_TIMESLOT);
        String expectedMessage = TYPE_INCORRECT_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }
}
