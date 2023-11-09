package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.suggestion.Suggestion;
import entity.suggestion.SuggestionStatus;
import entity.user.Staff;
import entity.user.Student;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

public class SuggestionRepository extends Repository<Suggestion> {
    UserRepository userRepository;
    CampRepository campRepository;

    @Override
    public SuggestionList getAll() {
        // deep copy
        SuggestionList all = new SuggestionList();
        for (Suggestion item : this.all) {
            all.add(item);
        }
        return all;
    }

    public SuggestionRepository(String filePath, UserRepository userRepository, CampRepository campRepository) {
        super(filePath);
        this.userRepository = userRepository;
        this.campRepository = campRepository;
    }

    public boolean load() {
        // Creates file if not exist
        File source = new File(super.filePath);
        try {
            source.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }

        SuggestionList cur = new SuggestionList();

        try (Reader fileReader = new FileReader(source)) {
            CSVParser parser = CSVParser.parse(fileReader, CSVFormat.RFC4180);
            List<CSVRecord> tmp = parser.getRecords();

            tmp.forEach(record -> {
                // id, senderID, campSuggestionString, reviewedByID, status 0/1/2

                String id = record.get(0);
                String senderID = record.get(1);
                UserList senderTmp = userRepository.getAll().filterByID(senderID);
                Student sender = null;
                if (senderTmp.size() > 0 && senderTmp.get(0) instanceof Student) {
                    sender = (Student) senderTmp.get(0);
                }

                String campSuggestionRaw = record.get(2);

                // id;name;desc;visibility
                // 0/1;startDate;endDate;closeRegDate;school;location;staff;attend;committee
                String[] campSuggestionArr = campSuggestionRaw.split(";");
                String campSuggestionID = campSuggestionArr[0];
                String visibilityRaw = campSuggestionArr[3];
                Boolean visibility = visibilityRaw == "0" ? false : true;
                Date startDate = new Date(Long.parseLong(campSuggestionArr[4]));
                Date endDate = new Date(Long.parseLong(campSuggestionArr[5]));

                CampDetails campDetails = new CampDetails(campSuggestionID, campSuggestionArr[1], campSuggestionArr[2],
                        visibility, startDate, endDate, null, campSuggestionArr[7],
                        campSuggestionArr[8]);

                String reviewedByID = record.get(4);
                UserList reviewedByTmp = userRepository.getAll().filterByID(reviewedByID);
                Staff reviewedBy = null;
                if (reviewedByTmp.size() > 0) {
                    reviewedBy = (Staff) reviewedByTmp.get(0);
                }

                String statusRaw = record.get(5);
                SuggestionStatus status = statusRaw == "0" ? SuggestionStatus.PENDING
                        : statusRaw == "1" ? SuggestionStatus.APPROVED : SuggestionStatus.REJECTED;

                Camp originalCamp = campRepository.getAll().filterByID(campSuggestionID).get(0);

                Suggestion suggestion = new Suggestion(id, sender, campDetails, originalCamp, reviewedBy, status);
                cur.add(suggestion);
            });

            super.setAll(cur);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return false;
        } catch (IOException eIoException) {
            System.out.println(eIoException.toString());
            return false;
        }
        return true;

    }

    public boolean save() {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(super.filePath), CSVFormat.DEFAULT)) {
            super.all.forEach(camp -> {
                try {
                    // id, senderID, campSuggestionString, reviewedByID, status 0/1/2
                    String id = camp.getID();
                    String senderID = camp.getSender().getID();

                    String visibilityString = camp.getSuggestion().isVisible() ? "1" : "0";

                    String campSuggestionString = camp.getSuggestion().getID() + ";" + camp.getSuggestion().getName()
                            + ";"
                            + camp.getSuggestion().getDescription() + ";" + visibilityString + ";"
                            + camp.getSuggestion().getStartDate().getTime() + ";"
                            + camp.getSuggestion().getEndDate().getTime()
                            + ";" + camp.getSuggestion().getSchool() + ";" + camp.getSuggestion().getLocation();

                    String reviewedByID = (camp.getReviewedBy() == null) ? "" : camp.getReviewedBy().getID();

                    String statusString = camp.getStatus() == SuggestionStatus.PENDING ? "0"
                            : camp.getStatus() == SuggestionStatus.APPROVED ? "1" : "2";

                    printer.printRecord(id, senderID, campSuggestionString, reviewedByID, statusString);

                } catch (IOException e) {
                    System.out.println(e.toString());

                }
            });
        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }
}
