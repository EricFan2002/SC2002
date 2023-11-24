package entity.report;

import java.util.ArrayList;

import entity.interfaces.ISerializable;

<<<<<<< Updated upstream
public abstract class Report implements ISerializable {
    private static String[] fields;

=======
/**
 * The {@code Report} abstract class provides a basis for creating reports that can be serialized.
 * It implements the {@code ISerializeable} interface.
 */
public abstract class Report implements ISerializeable {

    /**
     * The fields included in the report.
     */
    private static String[] fields;

    /**
     * Gets the fields included in the report.
     *
     * @return An array of strings representing the fields included in the report.
     */
    public static String[] getFields() {
        return fields;
    }

    /**
     * Abstract method to serialize the report and represent its data as an ArrayList of ArrayList of Strings.
     *
     * @return An {@code ArrayList<ArrayList<String>>} representing the serialized data of the report.
     */
>>>>>>> Stashed changes
    public abstract ArrayList<ArrayList<String>> serialize();
}

