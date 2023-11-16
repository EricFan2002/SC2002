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
                // 0/1;startDate;endDate;closeRegDate;school;location

                String[] campSuggestionArr = campSuggestionRaw.split(";", -1);

                String campSuggestionID = campSuggestionArr[0];
                String name = campSuggestionArr[1] != "" ? campSuggestionArr[1] : null;
                String desc = campSuggestionArr[2] != "" ? campSuggestionArr[2] : null;
                String visibilityRaw = campSuggestionArr[3];
                Boolean visibility = campSuggestionArr[3] != "" ? (visibilityRaw == "0" ? false : true) : null;
                Date startDate = campSuggestionArr[4] != "" ? new Date(Long.parseLong(campSuggestionArr[4])) : null;
                Date endDate = campSuggestionArr[5] != "" ? new Date(Long.parseLong(campSuggestionArr[5])) : null;
                Date closeRegDate = campSuggestionArr[6] != "" ? new Date(Long.parseLong(campSuggestionArr[6])) : null;
                String school = campSuggestionArr[7] != "" ? campSuggestionArr[7] : null;
                String location = campSuggestionArr[8] != "" ? campSuggestionArr[8] : null;

                CampDetails campDetails = new CampDetails(campSuggestionID, name, desc,
                        visibility, startDate, endDate, closeRegDate, school,
                        location);

                String reviewedByID = record.get(3);
                UserList reviewedByTmp = userRepository.getAll().filterByID(reviewedByID);
                Staff reviewedBy = null;
                if (reviewedByTmp.size() > 0) {
                    reviewedBy = (Staff) reviewedByTmp.get(0);
                }

                String statusRaw = record.get(4);
                SuggestionStatus status = statusRaw == "0" ? SuggestionStatus.PENDING
                        : statusRaw == "1" ? SuggestionStatus.APPROVED : SuggestionStatus.REJECTED;

                Camp originalCamp = campRepository.getAll().filterByID(campSuggestionID).get(0);

                Suggestion suggestion = new Suggestion(id, sender, campDetails, originalCamp, reviewedBy, status);

                //add Suggestion to SuggestionList
                cur.add(suggestion);
            });

            //set SuggestionRepo to SuggestionList
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
                    // id;name;desc;visibility
                    // 0/1;startDate;endDate;closeRegDate;school;location

                    String id = camp.getID();
                    String senderID = camp.getSender().getID();

                    // #region campSuggestionString

                    String idString = (camp.getSuggestion().getID() == null) ? "" : camp.getSuggestion().getID();
                    String nameString = (camp.getSuggestion().getName() == null) ? "" : camp.getSuggestion().getName();
                    String descString = (camp.getSuggestion().getDescription() == null) ? ""
                            : camp.getSuggestion().getDescription();

                    String visibilityString = camp.getSuggestion().isVisible() ? "1" : "0";

                    String startDateString = (camp.getSuggestion().getStartDate() == null) ? ""
                            : Long.toString(camp.getSuggestion().getStartDate().getTime());

                    String endDateString = (camp.getSuggestion().getEndDate() == null) ? ""
                            : Long.toString(camp.getSuggestion().getEndDate().getTime());

                    String closeRegDateString = (camp.getSuggestion().getCloseRegistrationDate() == null) ? ""
                            : Long.toString(camp.getSuggestion().getCloseRegistrationDate().getTime());

                    String schoolString = (camp.getSuggestion().getSchool() == null) ? ""
                            : camp.getSuggestion().getSchool();

                    String locationString = (camp.getSuggestion().getLocation() == null) ? ""
                            : camp.getSuggestion().getLocation();

                    String[] campSuggestionArray = { idString, nameString,
                            descString, visibilityString, startDateString, endDateString,
                            closeRegDateString, schoolString, locationString };

                    String campSuggestionString = String.join(";", campSuggestionArray);

                    // #endregion campSuggestionString

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
