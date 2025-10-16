package seedu.address.logic;

import java.time.LocalTime;

import javafx.collections.ObservableList;
import seedu.address.model.schedule.activity.Activity;

/**
 * Checks that the day and time provided for an activity is valid
 * and does not overlap with other activities.
 */
public class DayAndTimeChecker {

    /**
     * Returns true if the activity does not overlap with any existing activity
     * in the user's schedule.
     *
     * @param activity Activity to be added to schedule.
     * @param activities Existing list of activities.
     * @return true if there is no overlap, false otherwise.
     */
    public static boolean hasOverlapWithOtherActivities(Activity activity, ObservableList<Activity> activities) {
        String[] activityTimings = activity.getTimeslot().value.split("-");
        LocalTime startTime = LocalTime.parse(activityTimings[0]);
        LocalTime endTime = LocalTime.parse(activityTimings[1]);
        for (Activity existingActivity : activities) {
            String[] existingActivityTimings = existingActivity.getTimeslot().value.split("-");
            LocalTime existingStartTime = LocalTime.parse(existingActivityTimings[0]);
            LocalTime existingEndTime = LocalTime.parse(existingActivityTimings[1]);
            if (isNotSameDay(activity, existingActivity)) {
                continue;
            }
            if (isEqualOrAfter(startTime, existingEndTime)) {
                continue;
            }
            if (isEqualOrAfter(existingStartTime, endTime)) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static boolean isNotSameDay(Activity activity, Activity existingActivity) {
        return !activity.getDay().equals(existingActivity.getDay());
    }

    /**
     * Returns true if the start and end time of the activity does not overlap.
     */
    public static boolean hasMismatchedStartAndEnd(Activity activity) {
        String[] activityTimings = activity.getTimeslot().value.split("-");
        LocalTime startTime = LocalTime.parse(activityTimings[0]);
        LocalTime endTime = LocalTime.parse(activityTimings[1]);
        return isEqualOrAfter(startTime, endTime);
    }

    private static boolean isEqualOrAfter(LocalTime first, LocalTime second) {
        return first.compareTo(second) >= 0;
    }
}
