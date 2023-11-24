package entity.report;

import java.util.ArrayList;

import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;

/**
 * The {@code CampReport} class represents a report containing information about camps, including students and their roles.
 */
public class CampReport extends Report {

    /** The fields to include in the report. */
    private static String[] fields = { "Camp ID", "Camp", "Student ID", "Name", "Faculty", "Role" };

    /** The list of camps to include in the report. */
    private CampList camp;

    /**
     * The export option that determines which data to include in the report.
     * <p>
     * Bit 0: Include committees, Bit 1: Include attendees
     */
    private int exportOption;

    /**
     * Constructs a {@code CampReport} with the specified list of camps and export options.
     *
     * @param camp              The list of camps to include in the report.
     * @param includeCommittees Whether to include committees in the report.
     * @param includeAttendees  Whether to include attendees in the report.
     */

    public CampReport(CampList camp, boolean includeCommittees, boolean includeAttendees) {
        super();
        this.camp = new CampList();
        camp.forEach((curCamp) -> {
            this.camp.add(curCamp);
        });

        exportOption = 0;
        if (includeCommittees) {
            exportOption |= 1;
        }
        if (includeAttendees) {
            exportOption |= 2;
        }
    }
    /**
     * Constructs a {@code CampReport} with the specified camp and export options.
     *
     * @param camp              The camp to include in the report.
     * @param includeCommittees Whether to include committees in the report.
     * @param includeAttendees  Whether to include attendees in the report.
     */
    public CampReport(Camp camp, boolean includeCommittees, boolean includeAttendees) {
        super();
        this.camp = new CampList();
        this.camp.add(camp);

        exportOption = 0;
        if (includeCommittees) {
            exportOption |= 1;
        }
        if (includeAttendees) {
            exportOption |= 2;
        }
    }
    /**
     * Serializes the camp report into a list of lists of strings.
     *
     * @return An {@link ArrayList} containing lists of strings representing the serialized data.
     */
    public final ArrayList<ArrayList<String>> serialize() {
        boolean INCLUDE_COMMITEES = ((exportOption >> 0) & 1) == 1;
        boolean INCLUDE_ATTENDEES = ((exportOption >> 1) & 1) == 1;

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        ArrayList<String> header = new ArrayList<String>();
        for (String field : fields) {
            header.add(field);
        }
        data.add(header);
        for (Camp camp : this.camp) {
            if (INCLUDE_COMMITEES) {
                for (Student student : camp.getCommittees()) {
                    ArrayList<String> row = new ArrayList<String>();
                    row.add(camp.getID());
                    row.add(camp.getName());
                    row.add(student.getID());
                    row.add(student.getName());
                    row.add(student.getFaculty());
                    row.add("Committee");
                    data.add(row);
                }
            }

            if (INCLUDE_ATTENDEES) {
                for (Student student : camp.getAttendees()) {
                    ArrayList<String> row = new ArrayList<String>();
                    row.add(camp.getID());
                    row.add(camp.getName());
                    row.add(student.getID());
                    row.add(student.getName());
                    row.add(student.getFaculty());
                    row.add("Attendee");
                    data.add(row);
                }
            }
        }
        return data;
    }

}
