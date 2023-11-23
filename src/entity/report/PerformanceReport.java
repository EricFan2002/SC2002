package entity.report;

import java.util.ArrayList;

import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;

public class PerformanceReport extends Report {
    private static String[] fields = { "Camp ID", "Camp", "Student ID", "Student", "Points" };
    private CampList camps;

    public static String[] getFields() {
        return fields;
    }

    public PerformanceReport(CampList camps) {
        super();
        this.camps = new CampList();

        camps.forEach((camp) -> {
            this.camps.add(camp);
        });
    }

    public PerformanceReport(Camp camp) {
        super();
        this.camps = new CampList();
        this.camps.add(camp);
    }

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
