package entity.report;

import java.util.ArrayList;

import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;

/**
 * The {@code PerformanceReport} class represents a report on camp performance, including details about students and their points.
 * It extends the {@code Report} class and implements the {@code ISerializeable} interface.
 */
public class PerformanceReport extends Report {

    /**
     * The fields included in the report.
     */
    private static String[] fields = { "Camp ID", "Camp", "Student ID", "Student", "Points" };

    /**
     * The list of camps to be included in the report.
     */
    private CampList camps;

<<<<<<< Updated upstream
=======
    /**
     * Gets the fields included in the report.
     *
     * @return An array of strings representing the fields included in the report.
     */
    public static String[] getFields() {
        return fields;
    }

    /**
     * Constructs a PerformanceReport object with the specified camp list.
     *
     * @param camps The list of camps to be included in the report.
     */
>>>>>>> Stashed changes
    public PerformanceReport(CampList camps) {
        super();
        this.camps = new CampList();

        camps.forEach((camp) -> {
            this.camps.add(camp);
        });
    }

    /**
     * Constructs a PerformanceReport object with the specified camp.
     *
     * @param camp The camp to be included in the report.
     */
    public PerformanceReport(Camp camp) {
        super();
        this.camps = new CampList();
        this.camps.add(camp);
    }

    /**
     * Serializes the performance report and represents its data as an ArrayList of ArrayList of Strings.
     *
     * @return An {@code ArrayList<ArrayList<String>>} representing the serialized data of the performance report.
     */
    public final ArrayList<ArrayList<String>> serialize() {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        ArrayList<String> header = new ArrayList<String>();
        for (String field : fields) {
            header.add(field);
        }
        data.add(header);
        for (Camp camp : camps) {
            for (Student committee : camp.getCommittees()) {
                ArrayList<String> row = new ArrayList<String>();
                row.add(camp.getID());
                row.add(camp.getName());
                row.add(committee.getID());
                row.add(committee.getName());
                row.add(Integer.toString(committee.getPoints()));
                data.add(row);
            }
        }
        return data;
    }
}

