package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.camp.Camp;
import entity.user.Staff;
import entity.user.Student;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

public class CampRepository extends Repository<Camp> {
    private UserRepository userRepository;

    public CampRepository(String filePath, UserRepository userRepository) {
        super(filePath);
        this.userRepository = userRepository;
    }

    @Override
    public CampList getAll() {
        System.out.println(super.getAll().toString());
        return (CampList) super.getAll();
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

        CampList cur = new CampList();

        try (Reader fileReader = new FileReader(source)) {
            CSVParser parser = CSVParser.parse(fileReader, CSVFormat.RFC4180);
            List<CSVRecord> tmp = parser.getRecords();

            tmp.forEach(record -> {
                // id, name, desc, visibility (0/1), startDate, endDate, closeRegDate, school,
                // location, staff, attend, committee
                String id = record.get(0);
                String name = record.get(1);
                String desc = record.get(2);
                String visibilityRaw = record.get(3);
                boolean visibility = (visibilityRaw.equals("0")) ? false : true;

                String startDateRaw = record.get(4);
                Date startDate = new Date(Long.parseLong(startDateRaw));

                String endDateRaw = record.get(5);
                Date endDate = new Date(Long.parseLong(endDateRaw));

                String closeRegDateRaw = record.get(6);
                Date closeRegDate = new Date(Long.parseLong(closeRegDateRaw));

                String school = record.get(7);
                String location = record.get(8);

                String staffIDRaw = record.get(9);
                UserList tempStaff = userRepository.getAll().filterByID(staffIDRaw);

                Staff staff;
                if (tempStaff.size() > 0) {
                    staff = (Staff) tempStaff.get(0);
                } else {
                    staff = new Staff();
                    // do something if not found
                }
                Camp camp = new Camp(id, name, desc, visibility, startDate, endDate, closeRegDate, school, location,
                        staff);

                String[] attendeeIDsRaw = record.get(10).split(";");
                for (String attendeeIDRaw : attendeeIDsRaw) {
                    UserList tempUser = userRepository.getAll().filterByID(attendeeIDRaw);
                    if (tempUser.size() > 0) {
                        camp.addAttendee((Student) tempUser.get(0));
                    } else {
                        // skip
                    }
                }

                String[] committeeIDsRaw = record.get(11).split(";");
                for (String committeeIDRaw : committeeIDsRaw) {
                    UserList tempUser = userRepository.getAll().filterByID(committeeIDRaw);
                    if (tempUser.size() > 0) {
                        camp.addCommittee((Student) tempUser.get(0));
                    } else {
                        /// skip
                    }
                }

                cur.add(camp);
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
                    // id, name, desc, visibility (0/1), startDate, endDate, closeRegDate, school,

                    String visibility = (camp.isVisible()) ? "1" : "0";
                    String startDate = Long.toString(camp.getStartDate().getTime());
                    String endDate = Long.toString(camp.getEndDate().getTime());
                    String closeRegDate = Long.toString(camp.getCloseRegistrationDate().getTime());

                    String staffID = camp.getStaffInCharge().getID();

                    List<String> attendeeIDsTemp = new ArrayList<String>();
                    for (Student attendee : camp.getAttendees()) {
                        attendeeIDsTemp.add(attendee.getID());
                    }
                    String attendeeIDs = String.join(";", attendeeIDsTemp);

                    List<String> committeeIDsTemp = new ArrayList<String>();
                    for (Student committee : camp.getCommittees()) {
                        committeeIDsTemp.add(committee.getID());
                    }
                    String committeeIDs = String.join(";", committeeIDsTemp);
                    System.out.println(committeeIDs);

                    printer.printRecord(camp.getID(), camp.getName(), camp.getDescription(), visibility, startDate,
                            endDate, closeRegDate, camp.getSchool(), camp.getLocation(), staffID, attendeeIDs,
                            committeeIDs);
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
