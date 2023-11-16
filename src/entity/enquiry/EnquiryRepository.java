package entity.enquiry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import entity.Repository;
import entity.camp.Camp;
import entity.camp.CampList;
import entity.camp.CampRepository;
import entity.user.Student;
import entity.user.User;
import entity.user.UserList;
import entity.user.UserRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

public class EnquiryRepository extends Repository<Enquiry> {
    UserRepository userRepository;
    CampRepository campRepository;

    @Override
    public EnquiryList getAll() {
        // deep copy
        EnquiryList all = new EnquiryList();
        for (Enquiry item : this.all) {
            all.add(item);
        }
        return all;
    }

    public EnquiryRepository(String filePath, UserRepository userRepository, CampRepository campRepository) {
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

        EnquiryList cur = new EnquiryList();

        try (Reader fileReader = new FileReader(source)) {
            CSVParser parser = CSVParser.parse(fileReader, CSVFormat.RFC4180);
            List<CSVRecord> tmp = parser.getRecords();

            tmp.forEach(record -> {
                // id, senderID, question, answer, campID, answeredByID

                String id = record.get(0);
                String senderID = record.get(1);
                UserList senderTmp = userRepository.getAll().filterByID(senderID);
                Student sender = null;
                if (senderTmp.size() > 0 && senderTmp.get(0) instanceof Student) {
                    sender = (Student) senderTmp.get(0);
                }

                String question = record.get(2);
                String answer = record.get(3);

                String campID = record.get(4);
                CampList campTmp = campRepository.getAll().filterByID(campID);
                Camp camp = null;
                if (campTmp.size() > 0) {
                    camp = campTmp.get(0);
                }

                String answeredByID = record.get(5);
                UserList answeredByTmp = userRepository.getAll().filterByID(answeredByID);
                User answeredBy = null;
                if (answeredByTmp.size() > 0) {
                    answeredBy = (Student) answeredByTmp.get(0);
                }

                Enquiry enquiry = new Enquiry(id, sender, question, answer, camp, answeredBy);

                // add Enquiry to EnquiryList
                cur.add(enquiry);
            });

            // set EnquiryRepo to EnquiryList,called Repo's function
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
                    // id, senderID, question, answer, campID, answeredByID
                    String senderID = camp.getSender().getID();
                    String answeredByID = (camp.getAnsweredBy() == null) ? "" : camp.getAnsweredBy().getID();
                    String campID = camp.getCamp().getID();
                    printer.printRecord(camp.getID(), senderID, camp.getQuestion(), camp.getAnswer(), campID,
                            answeredByID);
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