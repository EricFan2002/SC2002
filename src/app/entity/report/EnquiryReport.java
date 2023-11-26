package app.entity.report;

import java.util.ArrayList;
import java.util.Arrays;

import app.entity.camp.Camp;
import app.entity.camp.CampList;
import app.entity.enquiry.Enquiry;

/**
 * The {@code EnquiryReport} class represents a report on camp enquiries, including details about questions and answers.
 * It extends the {@code Report} class and implements the {@code ISerializeable} interface.
 */
public class EnquiryReport extends Report {

    /**
     * The fields included in the report.
     */
    private static String[] fields = { "Camp ID", "Camp Name", "Sender ID", "Sender Name", "Question",
            "Answered by Name", "Answered by ID", "Answer" };

    /**
     * The list of camps to be included in the report.
     */
    private CampList camp;

    /**
     * Constructs an EnquiryReport object with the specified camp list.
     *
     * @param camp The list of camps to be included in the report.
     */
    public EnquiryReport(CampList camp) {
        super();
        this.camp = new CampList();
        camp.forEach((curCamp) -> {
            this.camp.add(curCamp);
        });
    }

    /**
     * Constructs an EnquiryReport object with the specified camp.
     *
     * @param camp The camp to be included in the report.
     */
    public EnquiryReport(Camp camp) {
        super();
        this.camp = new CampList();
        this.camp.add(camp);
    }

    /**
     * Serializes the enquiry report and represents its data as an ArrayList of ArrayList of Strings.
     *
     * @return An {@code ArrayList<ArrayList<String>>} representing the serialized data of the enquiry report.
     */
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

