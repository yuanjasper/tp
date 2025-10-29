package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalActivities.CS2103_LECTURE;
import static seedu.address.testutil.TypicalActivities.CS2103_TUTORIAL;
import static seedu.address.testutil.TypicalActivities.CS2106_LECTURE;
import static seedu.address.testutil.TypicalActivities.MISMATCHED_TIME_ACTIVITY;
import static seedu.address.testutil.TypicalActivities.OVERLAPPING_ACTIVITY;
import static seedu.address.testutil.TypicalActivities.getTypicalSchedule;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.schedule.activity.Activity;
import seedu.address.model.schedule.exceptions.ActivityNotFoundException;
import seedu.address.model.schedule.exceptions.DuplicateActivityException;
import seedu.address.model.schedule.exceptions.MismatchedTimeException;
import seedu.address.model.schedule.exceptions.OverlappingActivityException;
import seedu.address.testutil.ActivityBuilder;

public class ScheduleTest {

    private final Schedule schedule = new Schedule();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), schedule.getActivities());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Schedule newData = getTypicalSchedule();
        schedule.resetData(newData);
        assertEquals(newData, schedule);
    }

    @Test
    public void resetData_withDuplicateActivities_throwsDuplicateActivityException() {
        // Two activities with the same data
        Activity editedCS2106Lecture = new ActivityBuilder(CS2106_LECTURE).build();
        List<Activity> newActivities = Arrays.asList(CS2106_LECTURE, editedCS2106Lecture);
        ScheduleStub newData = new ScheduleStub(newActivities);

        assertThrows(DuplicateActivityException.class, () -> schedule.resetData(newData));
    }

    @Test
    public void hasActivity_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.hasActivity(null));
    }

    @Test
    public void hasActivity_activityNotInAddressBook_returnsFalse() {
        assertFalse(schedule.hasActivity(CS2103_TUTORIAL));
    }

    @Test
    public void hasActivity_activityInAddressBook_returnsTrue() {
        schedule.add(CS2103_TUTORIAL);
        assertTrue(schedule.hasActivity(CS2103_TUTORIAL));
    }

    @Test
    public void hasMismatchedTime_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.hasActivity(null));
    }

    @Test
    public void hasMismatchedTime_activityTimeIsNotMismatched_returnsFalse() {
        assertFalse(schedule.hasMismatchedTime(CS2103_TUTORIAL));
    }

    @Test
    public void hasMismatchedTime_activityTimeIsMismatched_returnsTrue() {
        assertTrue(schedule.hasMismatchedTime(MISMATCHED_TIME_ACTIVITY));
    }

    @Test
    public void hasOverlap_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.hasOverlap(null));
    }

    @Test
    public void hasOverlap_noOverlapBetweenActivities_returnsFalse() {
        schedule.add(CS2103_TUTORIAL);
        assertFalse(schedule.hasOverlap(CS2103_LECTURE));
    }

    @Test
    public void hasOverlap_overlapBetweenActivities_returnsTrue() {
        schedule.add(CS2103_TUTORIAL);
        assertTrue(schedule.hasOverlap(OVERLAPPING_ACTIVITY));
    }

    @Test
    public void getActivity_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.getActivity(null));
    }

    @Test
    public void getActivity_activityNotInSchedule_returnsOptionalEmpty() {
        schedule.add(CS2103_TUTORIAL);
        Optional<Activity> activity = schedule.getActivity(CS2103_LECTURE);
        assertEquals(Optional.empty(), activity);
    }

    @Test
    public void getActivity_activityInSchedule_returnsOptionalActivity() {
        schedule.add(CS2103_TUTORIAL);
        Optional<Activity> activity = schedule.getActivity(CS2103_TUTORIAL);
        assertEquals(Optional.of(CS2103_TUTORIAL), activity);
    }

    @Test
    public void add_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.add(null));
    }

    @Test
    public void add_duplicateActivity_throwsDuplicateActivityException() {
        schedule.add(CS2103_TUTORIAL);
        assertThrows(DuplicateActivityException.class, () -> schedule.add(CS2103_TUTORIAL));
    }

    @Test
    public void add_mismatchedTimeActivity_throwsMismatchedTimeException() {
        assertThrows(MismatchedTimeException.class, () -> schedule.add(MISMATCHED_TIME_ACTIVITY));
    }

    @Test
    public void add_overlappingActivity_throwsOverlappingActivityException() {
        schedule.add(CS2103_TUTORIAL);
        assertThrows(OverlappingActivityException.class, () -> schedule.add(OVERLAPPING_ACTIVITY));
    }

    @Test
    public void add_allRequirementsPass_activityAdded() {
        schedule.add(CS2103_TUTORIAL);
        List<Activity> newActivities = Arrays.asList(CS2103_TUTORIAL);
        Schedule otherSchedule = new Schedule();
        otherSchedule.setActivities(newActivities);
        assertEquals(otherSchedule, schedule);
    }

    @Test
    public void delete_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.delete(null));
    }

    @Test
    public void delete_activityNotInSchedule_throwsActivityNotFoundException() {
        assertThrows(ActivityNotFoundException.class, () -> schedule.delete(CS2103_TUTORIAL));
    }

    @Test
    public void delete_activityInSchedule_activityDeleted() {
        schedule.add(CS2103_TUTORIAL);
        schedule.delete(CS2103_TUTORIAL);
        Schedule otherSchedule = new Schedule();
        assertEquals(otherSchedule, schedule);
    }

    @Test
    public void getActivities_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> schedule.getActivities().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Schedule.class.getCanonicalName() + "{activities=" + schedule.getActivities() + "}";
        assertEquals(expected, schedule.toString());
    }

    /**
     * A stub ReadOnlySchedule whose persons list can violate interface constraints.
     */
    private static class ScheduleStub implements ReadOnlySchedule {
        private final ObservableList<Activity> activities = FXCollections.observableArrayList();

        ScheduleStub(Collection<Activity> activities) {
            this.activities.setAll(activities);
        }

        @Override
        public ObservableList<Activity> getActivities() {
            return activities;
        }
    }
}
