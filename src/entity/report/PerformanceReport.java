package entity.report;

import java.util.ArrayList;

import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;

/**
 * The {@code PerformanceReport} class represents a performance report for committees in camps.
 */
public class PerformanceReport extends Report {
    /**
     * The fields to be included in the report.
     */
    private static String[] fields = { "Camp ID", "Camp", "Student ID", "Student", "Points" };
    /**
     * The list of camps for which the performance report is generated.
     */
    private CampList camps;
    /**
     * Constructs a performance report for multiple camps.
     *
     * @param camps The list of camps for which the performance report is generated.
     */

    public PerformanceReport(CampList camps) {
        super();
        this.camps = new CampList();

        camps.forEach((camp) -> {
            this.camps.add(camp);
        });
    }

    /**
     * Constructs a performance report for a single camp.
     *
     * @param camp The camp for which the performance report is generated.
     */
    public PerformanceReport(Camp camp) {
        super();
        this.camps = new CampList();
        this.camps.add(camp);
    }

    /**
     * Serializes the performance report into a two-dimensional ArrayList of strings.
     *
     * @return The serialized performance report data.
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
