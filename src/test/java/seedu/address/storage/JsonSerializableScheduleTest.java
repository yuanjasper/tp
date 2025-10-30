package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.TypicalActivities;

public class JsonSerializableScheduleTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableScheduleTest");
    private static final Path TYPICAL_ACTIVITIES_FILE = TEST_DATA_FOLDER.resolve("typicalActivitiesSchedule.json");
    private static final Path INVALID_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve("invalidActivitySchedule.json");
    private static final Path DUPLICATE_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve("duplicateActivitySchedule.json");
    private static final Path MISMATCHED_TIMING_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve(
            "mismatchedTimingActivitySchedule.json");
    private static final Path OVERLAPPING_TIMING_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve(
            "overlappingTimingActivitySchedule.json");

    @Test
    public void toModelType_typicalActivitiesFile_success() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(TYPICAL_ACTIVITIES_FILE,
                JsonSerializableSchedule.class).get();
        Schedule scheduleFromFile = dataFromFile.toModelType();
        Schedule typicalActivitiesSchedule = TypicalActivities.getTypicalSchedule();
        assertEquals(scheduleFromFile, typicalActivitiesSchedule);
    }

    @Test
    public void toModelType_invalidActivityFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(INVALID_ACTIVITY_FILE,
                JsonSerializableSchedule.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateActivityFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ACTIVITY_FILE,
                JsonSerializableSchedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSchedule.MESSAGE_DUPLICATE_ACTIVITY,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_mismatchedTimingActivityFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(MISMATCHED_TIMING_ACTIVITY_FILE,
                JsonSerializableSchedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSchedule.MESSAGE_MISMATCHED_TIMING,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_overlappingTimingActivityFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(OVERLAPPING_TIMING_ACTIVITY_FILE,
                JsonSerializableSchedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSchedule.MESSAGE_OVERLAPPING_TIMING,
                dataFromFile::toModelType);
    }

}
