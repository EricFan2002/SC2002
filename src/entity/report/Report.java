package entity.report;

import java.util.ArrayList;

import entity.interfaces.ISerializable;

/**
 * The {@code Report} class is an abstract class that implements the {@code ISerializable} interface.
 * It serves as the base class for various types of reports in the system.
 */
public abstract class Report implements ISerializable {

    /**
     * An array of field names for the report.
     */
    private static String[] fields;

    /**
     * Abstract method to serialize the report data into a list of lists of strings.
     *
     * @return An {@code ArrayList<ArrayList<String>>} representing the serialized data of the report.
     */
    public abstract ArrayList<ArrayList<String>> serialize();
}
