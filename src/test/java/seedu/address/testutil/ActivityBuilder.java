package seedu.address.testutil;

import seedu.address.model.schedule.activity.Activity;
import seedu.address.model.schedule.activity.Day;
import seedu.address.model.schedule.activity.Info;
import seedu.address.model.schedule.activity.Timeslot;

/**
 * A utility class to help with building {@code Activity} objects.
 */
public class ActivityBuilder {

    public static final String DEFAULT_INFO = "CS2103 Tutorial";
    public static final String DEFAULT_DAY = "friday";
    public static final String DEFAULT_TIMESLOT = "09:00-10:00";

    private Info info;
    private Day day;
    private Timeslot timeslot;

    /**
     * Creates a {@code ActivityBuilder} with the default details.
     */
    public ActivityBuilder() {
        info = new Info(DEFAULT_INFO);
        day = new Day(DEFAULT_DAY);
        timeslot = new Timeslot(DEFAULT_TIMESLOT);
    }

    /**
     * Initializes the ActivityBuilder with the data of {@code activityToCopy}.
     */
    public ActivityBuilder(Activity activityToCopy) {
        info = activityToCopy.getInfo();
        day = activityToCopy.getDay();
        timeslot = activityToCopy.getTimeslot();
    }

    /**
     * Sets the {@code Info} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withInfo(String info) {
        this.info = new Info(info);
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withDay(String day) {
        this.day = new Day(day);
        return this;
    }

    /**
     * Sets the {@code Timeslot} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withTimeslot(String timeslot) {
        this.timeslot = new Timeslot(timeslot);
        return this;
    }

    public Activity build() {
        return new Activity(info, day, timeslot);
    }
}
