package io;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents a CSV reader that returns valid records from a file path.
 *
 * @param <T> record type produced by the reader
 */
public interface CsvFileReader<T> {
    /**
     * Reads valid records from a CSV file.
     *
     * @param path CSV file path
     * @return valid records loaded from the file
     * @throws FileNotFoundException if the file must exist but cannot be opened
     */
    ArrayList<T> read(String path) throws FileNotFoundException;
}
