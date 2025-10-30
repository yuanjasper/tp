package seedu.address.ui;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.schedule.ReadOnlySchedule;
import seedu.address.model.schedule.activity.Activity;
import seedu.address.model.schedule.activity.Day;
import seedu.address.model.schedule.activity.Timeslot;

/**
 * Used ChatGPT to assist
 * Controller for ScheduleWindow.fxml.
 */
public class ScheduleWindow extends UiPart<Region> {
    private static final String FXML = "ScheduleWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(ScheduleWindow.class);
    private ReadOnlySchedule schedule;

    @FXML
    private TableView<Activity> scheduleTable;

    @FXML
    private TableColumn<Activity, String> dayColumn;

    @FXML
    private TableColumn<Activity, String> timeslotColumn;

    @FXML
    private TableColumn<Activity, String> infoColumn;

    /**
     * Creates a {@code ScheduleWindow} with the given {@code ReadOnlySchedule}.
     */
    public ScheduleWindow(ReadOnlySchedule schedule) {
        super(FXML);
        this.schedule = schedule;
        populateTable();
    }

    /**
     * Pass the user's schedule to this controller.
     */
    public void setSchedule() {
        populateTable();
    }

    /**
     * Used to determine order when presenting information on Schedule page
     * Sorts by date as priority followed by the slot
     */
    Comparator<Activity> byDateThenSlot = (a1, a2) -> {
        Day d1 = a1.getDay();
        Day d2 = a2.getDay();
        Timeslot s1 = a1.getTimeslot();
        Timeslot s2 = a2.getTimeslot();

        List<String> dayOrder = List.of(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

        int dayCompare = Integer.compare(
                dayOrder.indexOf(d1.value),
                dayOrder.indexOf(d2.value)
        );

        if (dayCompare != 0) {
            return dayCompare;
        }

        String start1 = s1.value.split("-")[0];
        String start2 = s2.value.split("-")[0];
        return start1.compareTo(start2);
    };

    @FXML
    public void initialize() {
        dayColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDay().toString()));

        timeslotColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTimeslot().toString()));

        infoColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getInfo().toString()));

        dayColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        timeslotColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        infoColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        scheduleTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Populate the TableView with activities from the schedule.
     * Items are presented in chronological order, date prioority followed by timeslot
     */
    private void populateTable() {
        if (schedule != null) {
            SortedList<Activity> sortedActivities = new SortedList<>(schedule.getActivities());
            sortedActivities.setComparator(byDateThenSlot);

            scheduleTable.setItems(sortedActivities);
        }
    }
}
