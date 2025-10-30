package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CS2106_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CS2106_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_MISMATCHED_TIME_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_OVERLAPPING_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_CS2106_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_CS2106_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_MISMATCHED_TIME_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_OVERLAPPING_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS2106_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS2106_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_MISMATCHED_TIME_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_OVERLAPPING_ACTIVITY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.activity.Activity;

/**
 * A utility class containing a list of {@code Activity} objects to be used in tests.
 */
public class TypicalActivities {

    public static final Activity CS2103_TUTORIAL = new ActivityBuilder().withInfo("CS2103 Tutorial")
            .withDay("friday").withTimeslot("09:00-10:00").build();
    public static final Activity CS2103_LECTURE = new ActivityBuilder().withInfo("CS2103 Lecture")
            .withDay("friday").withTimeslot("16:00-18:00").build();
    public static final Activity CS2103_GROUP_MEETING = new ActivityBuilder().withInfo("CS2103 Group Meeting")
            .withDay("wednesday").withTimeslot("17:00-18:00").build();
    public static final Activity CCA_MEETING = new ActivityBuilder().withInfo("CCA Meeting")
            .withDay("wednesday").withTimeslot("15:00-16:30").build();
    public static final Activity EXTRA_LESSON = new ActivityBuilder().withInfo("Extra Lesson")
            .withDay("sunday").withTimeslot("14:00-16:00").build();

    // Manually added
    public static final Activity VOLUNTEERING = new ActivityBuilder().withInfo("Volunteering")
            .withDay("saturday").withTimeslot("10:00-13:00").build();
    public static final Activity LAB = new ActivityBuilder().withInfo("Lab")
            .withDay("thursday").withTimeslot("10:00-16:00").build();

    // Manually added - Activity's details found in {@code CommandTestUtil}
    public static final Activity CS2106_LECTURE = new ActivityBuilder().withInfo(VALID_INFO_CS2106_LECTURE)
            .withDay(VALID_DAY_CS2106_LECTURE).withTimeslot(VALID_TIMESLOT_CS2106_LECTURE).build();
    public static final Activity CS2106_TUTORIAL = new ActivityBuilder().withInfo(VALID_INFO_CS2106_TUTORIAL)
            .withDay(VALID_DAY_CS2106_TUTORIAL).withTimeslot(VALID_TIMESLOT_CS2106_TUTORIAL).build();
    public static final Activity MISMATCHED_TIME_ACTIVITY = new ActivityBuilder()
            .withInfo(VALID_INFO_MISMATCHED_TIME_ACTIVITY).withDay(VALID_DAY_MISMATCHED_TIME_ACTIVITY)
            .withTimeslot(VALID_TIMESLOT_MISMATCHED_TIME_ACTIVITY).build();
    public static final Activity OVERLAPPING_ACTIVITY = new ActivityBuilder()
            .withInfo(VALID_INFO_OVERLAPPING_ACTIVITY).withDay(VALID_DAY_OVERLAPPING_ACTIVITY)
            .withTimeslot(VALID_TIMESLOT_OVERLAPPING_ACTIVITY).build();

    private TypicalActivities() {} // prevents instantiation

    /**
     * Returns a schedule with all the typical activities.
     */
    public static Schedule getTypicalSchedule() {
        Schedule schedule = new Schedule();
        for (Activity activity : getTypicalActivities()) {
            schedule.add(activity);
        }
        return schedule;
    }

    public static List<Activity> getTypicalActivities() {
        return new ArrayList<>(Arrays.asList(CS2103_TUTORIAL, CS2103_LECTURE,
                CS2103_GROUP_MEETING, CCA_MEETING, EXTRA_LESSON));
    }
}
