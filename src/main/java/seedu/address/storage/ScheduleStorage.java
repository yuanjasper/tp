package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.schedule.ReadOnlySchedule;

/**
 * Represents a storage for {@link seedu.address.model.schedule}.
 */
public interface ScheduleStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getScheduleFilePath();

    /**
     * Returns Schedule data as a {@link ReadOnlySchedule}.
     * Returns {@code Optional.empty()} if storage file not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlySchedule> readSchedule() throws DataLoadingException;

    /**
     * @see #readSchedule()
     */
    Optional<ReadOnlySchedule> readSchedule(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlySchedule} to the storage.
     *
     * @param schedule cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSchedule(ReadOnlySchedule schedule) throws IOException;

    /**
     * @see #saveSchedule(ReadOnlySchedule)
     */
    void saveSchedule(ReadOnlySchedule schedule, Path filePath) throws IOException;
}
