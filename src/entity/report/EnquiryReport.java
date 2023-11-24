package entity.report;

import java.util.ArrayList;
import java.util.Arrays;

import entity.camp.Camp;
import entity.camp.CampList;
import entity.enquiry.Enquiry;

public class EnquiryReport extends Report {
    private static String[] fields = { "Camp ID", "Camp Name", "Sender ID", "Sender Name", "Question",
            "Answered by Name", "Answered by ID", "Answer" };
    private CampList camp;

    public EnquiryReport(CampList camp) {
        super();
        this.camp = new CampList();
        camp.forEach((curCamp) -> {
            this.camp.add(curCamp);
        });
    }

    public EnquiryReport(Camp camp) {
        super();
        this.camp = new CampList();
        this.camp.add(camp);
    }

    public final ArrayList<ArrayList<String>> serialize() {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> header = new ArrayList<String>(Arrays.asList(fields));
        data.add(header);

        for (Camp camp : this.camp) {
            ArrayList<Enquiry> enquiries = camp.getEnquiryList();
            for (Enquiry enquiry : enquiries) {
                ArrayList<String> row = new ArrayList<String>();
                row.add(camp.getID());
                row.add(camp.getName());
                row.add(enquiry.getSender().getID());
                row.add(enquiry.getSender().getName());
                row.add(enquiry.getQuestion());
                if(enquiry.getAnsweredBy() != null){
                    row.add(enquiry.getAnsweredBy().getID());
                    row.add(enquiry.getAnsweredBy().getName());
                } else {
                    row.add("");
                    row.add("");
                }
                row.add(enquiry.getAnswer());
                data.add(row);
            }
        }
        return data;
    }
}
