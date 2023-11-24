package entity.report;

import java.util.ArrayList;

import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;

/**
 * The {@code CampReport} class represents a report on camps, including information about committees and attendees.
 * It extends the {@code Report} class and implements the {@code ISerializeable} interface.
 */
public class CampReport extends Report {

    /**
     * The fields included in the report.
     */
    private static String[] fields = { "Camp ID", "Camp", "Student ID", "Name", "Faculty", "Role" };

    /**
     * The list of camps to be included in the report.
     */
    private CampList camp;

<<<<<<< Updated upstream
=======
    /**
     * The export option to determine which information to include in the report.
     * Bit 0: include committees, Bit 1: include attendees.
     */
    private int exportOption;

    /**
     * Gets the fields included in the report.
     *
     * @return An array of strings representing the fields included in the report.
     */
    public static String[] getFields() {
        return fields;
    }

    /**
     * Constructs a CampReport object with the specified camp list and export options.
     *
     * @param camp              The list of camps to be included in the report.
     * @param includeCommittees Whether to include committee information in the report.
     * @param includeAttendees  Whether to include attendee information in the report.
     */
>>>>>>> Stashed changes
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
     * Constructs a CampReport object with the specified camp and export options.
     *
     * @param camp              The camp to be included in the report.
     * @param includeCommittees Whether to include committee information in the report.
     * @param includeAttendees  Whether to include attendee information in the report.
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
     * Serializes the camp report and represents its data as an ArrayList of ArrayList of Strings.
     *
     * @return An {@code ArrayList<ArrayList<String>>} representing the serialized data of the camp report.
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

