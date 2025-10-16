package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.activity.Activity;
import seedu.address.model.schedule.activity.Day;
import seedu.address.model.schedule.activity.Info;
import seedu.address.model.schedule.activity.Timeslot;

/**
 * Jackson-friendly version of {@link Activity}.
 */
public class JsonAdaptedActivity {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Activity's %s field is missing!";

    private final String info;
    private final String day;
    private final String timeslot;

    /**
     * Constructs a {@code JsonAdaptedActivity} with the given activity details.
     */
    @JsonCreator
    public JsonAdaptedActivity(@JsonProperty("info") String info, @JsonProperty("day") String day,
                               @JsonProperty("timeslot") String timeslot) {
        this.info = info;
        this.day = day.toLowerCase();
        this.timeslot = timeslot;
    }

    /**
     * Constructs a given {@code Activity} into this class for Jackson use.
     */
    public JsonAdaptedActivity(Activity source) {
        info = source.getInfo().value;
        day = source.getDay().value;
        timeslot = source.getTimeslot().value;
    }

    /**
     * Converts this Jackson friendly adapted activity object into the model's {@code Activity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted activity.
     */
    public Activity toModelType() throws IllegalValueException {
        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Info.class.getSimpleName()));
        }
        if (!Info.isValidInfo(info)) {
            throw new IllegalValueException(Info.MESSAGE_CONSTRAINTS);
        }
        final Info modelInfo = new Info(info);
        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(day)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelDay = new Day(day);
        if (timeslot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Timeslot.class.getSimpleName()));
        }
        if (!Timeslot.isValidTimeslot(timeslot)) {
            throw new IllegalValueException(Timeslot.MESSAGE_CONSTRAINTS);
        }
        final Timeslot modelTimeslot = new Timeslot(timeslot);
        return new Activity(modelInfo, modelDay, modelTimeslot);
    }
}
