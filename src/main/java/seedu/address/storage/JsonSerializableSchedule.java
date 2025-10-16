package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.ReadOnlySchedule;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.activity.Activity;

/**
 * An immutable Schedule that is serializable to JSON format.
 */
@JsonRootName(value = "schedule")
public class JsonSerializableSchedule {

    public static final String MESSAGE_DUPLICATE_ACTIVITY = "Schedule contains duplicate activities";
    public static final String MESSAGE_MISMATCHED_TIMING = "Schedule contains activities with "
            + "start time equal to or after end time";
    public static final String MESSAGE_OVERLAPPING_TIMING = "Schedule contains activities with overlapping timeslots";

    private final List<JsonAdaptedActivity> activities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSchedule} with the given activities.
     */
    @JsonCreator
    public JsonSerializableSchedule(@JsonProperty("activities") List<JsonAdaptedActivity> activities) {
        this.activities.addAll(activities);
    }

    /**
     * Converts a given {@code ReadOnlySchedule} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSchedule}.
     */
    public JsonSerializableSchedule(ReadOnlySchedule source) {
        activities.addAll(source.getActivities().stream().map(JsonAdaptedActivity::new).collect(Collectors.toList()));
    }

    /**
     * Converts this schedule into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Schedule toModelType() throws IllegalValueException {
        Schedule schedule = new Schedule();
        for (JsonAdaptedActivity jsonAdaptedActivity : activities) {
            Activity activity = jsonAdaptedActivity.toModelType();
            if (schedule.hasActivity(activity)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ACTIVITY);
            }
            if (schedule.hasMismatchedTime(activity)) {
                throw new IllegalValueException(MESSAGE_MISMATCHED_TIMING);
            }
            if (schedule.hasOverlap(activity)) {
                throw new IllegalValueException(MESSAGE_OVERLAPPING_TIMING);
            }
            schedule.add(activity);
        }
        return schedule;
    }
}
