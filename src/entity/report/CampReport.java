package entity.report;

import java.util.ArrayList;

import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;

public class CampReport extends Report {
    private static String[] fields = { "Camp ID", "Camp", "Student ID", "Name", "Faculty", "Role" };

    private CampList camp;
    private int exportOption; // 0th bit: include committees, 1st bit: include attendees

    public static String[] getFields() {
        return fields;
    }

    public CampReport(CampList camp, boolean includeCommittees, boolean includeAttendees) {
        super();
        camp.forEach((curCamp) -> {
            this.camp.add(curCamp);
        });
        this.exportOption = 0;
        if (includeCommittees) {
            this.exportOption |= 1;
        }
        if (includeAttendees) {
            this.exportOption |= 2;
        }
    }

    public CampReport(Camp camp, int option) {
        super();
        this.camp = new CampList();
        this.camp.add(camp);
    }

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
