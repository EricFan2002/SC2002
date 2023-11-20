package entity.camp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Repository;
import entity.user.Staff;
import entity.user.Student;
import entity.user.UserList;
import entity.user.UserRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

/**
 * Handles the storage, retrieval, and manipulation of camp data.
 * This class extends the Repository class for Camp objects and manages camp data through CSV files.
 */
public class CampRepository extends Repository<Camp> {
    private UserRepository userRepository;

    /**
     * Constructs a new CampRepository with a given file path for the CSV file and a UserRepository for user data.
     *
     * @param filePath       The path to the CSV file where camp data is stored.
     * @param userRepository The repository for user data which will be used to link staff and students to camps.
     */
    public CampRepository(String filePath, UserRepository userRepository) {
        super(filePath);
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all camps as a CampList. This method performs a deep copy of camps.
     *
     * @return A new CampList containing copies of all camps in the repository.
     */
    @Override
    public CampList getAll() {
        // deep copy
        CampList all = new CampList();
        for (Camp item : this.all) {
            all.add(item);
        }
        return all;
    }

    /**
     * Loads camp data from the CSV file.
     * This method parses the CSV file and populates the repository with Camp objects.
     *
     * @return true if the data was loaded successfully, false otherwise.
     */
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

                String totalSlotsRaw = record.get(12);
                int totalSlots = Integer.parseInt(totalSlotsRaw);

                Staff staff;
                if (tempStaff.size() > 0) {
                    staff = (Staff) tempStaff.get(0);
                } else {
                    staff = new Staff();
                    // do something if not found
                }
                Camp camp = new Camp(id, name, desc, visibility, startDate, endDate, closeRegDate, school, location,
                        staff, totalSlots);

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

                // add to CampList which extends RepositoryList
                cur.add(camp);
            });

            // set CampRepo to CampList,called Repo's function
            super.setAll(cur);

            // create connection to user repository
            super.getAll().forEach(camp -> {
                camp.getAttendees().forEach(attendee -> {
                    attendee.addAttendedCamp(camp);
                });
                camp.getCommittees().forEach(committee -> {
                    committee.addCommitteeCamp(camp);
                });
                camp.getStaffInCharge().addOrganizedCamp(camp);
            });

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return false;
        } catch (IOException eIoException) {
            System.out.println(eIoException.toString());
            return false;
        }
        return true;
    }

    /**
     * Saves all camp data to the CSV file.
     * This method writes the current state of the repository to the CSV file.
     *
     * @return true if the data was saved successfully, false otherwise.
     */
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

                    printer.printRecord(camp.getID(), camp.getName(), camp.getDescription(), visibility, startDate,
                            endDate, closeRegDate, camp.getSchool(), camp.getLocation(), staffID, attendeeIDs,
                            committeeIDs, camp.getTotalSlots());
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
