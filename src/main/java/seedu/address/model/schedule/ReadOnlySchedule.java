package seedu.address.model.schedule;

import javafx.collections.ObservableList;
import seedu.address.model.schedule.activity.Activity;

/**
 * Unmodifiable view of a schedule.
 */
public interface ReadOnlySchedule {

    /**
     * Returns an unmodifiable view of the activities.
     * The list will not contain any duplicate activities.
     */
    ObservableList<Activity> getActivities();
}
