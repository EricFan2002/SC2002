
/*class 1)inserts camp into camp repo, 
        2)finds a camp from id 
        3)gets all camps
        4)updates camp
        5)removes camp
*/
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import entity.Repository;
import entity.UserList;
import entity.user.Staff;
import entity.user.Student;
import entity.user.User;
import entity.user.UserFactory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

//public class CampRepository implements ICommitteeReportGenerator {

//this class contains all the created camps even those not visible
public class CampRepository<Camp> extends Repository<Camp> {
    protected String filePath;
    protected List<Camp> all;

    protected int size;
    protected Map<Student, Camp> committee;

    public CampRepository() {
        super("src/data/camps.csv");
        this.all = new ArrayList<>();
        this.committee = new HashMap<>();
    }

    public Camp getByID(String id) {
        return all.stream()
                .filter(camp -> camp.getID().equals(id))
                .findFirst()
                .orElse(null); // Returns null if Camp is not found
    }

    public boolean save() {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(super.filePath), CSVFormat.DEFAULT)) {
            super.all.forEach(user -> {
                try {
                    String userType = (user instanceof Staff) ? "1" : "0";
                    printer.printRecord(user.getID(), user.getName(), user.getPassword(), user.getFaculty(), userType);
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

    public boolean load() {
        File source = new File(super.filePath);
        try {
            source.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }

        UserList cur = new UserList();

        try (Reader fileReader = new FileReader(source)) {
            CSVParser parser = CSVParser.parse(fileReader, CSVFormat.RFC4180);
            List<CSVRecord> tmp = parser.getRecords();

            tmp.forEach(record -> {
                // id, name, password, faculty, type

                String id = record.get(0);
                String name = record.get(1);
                String password = record.get(2);
                String faculty = record.get(3);
                int type = Integer.parseInt(record.get(4));

                String typeName = (type == 0) ? "Staff" : "Student";

                User user = UserFactory.getUser(typeName, id, name, faculty);

                if (user != null) {
                    user.setPassword(password);
                    cur.add(user);
                }
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

    public boolean add(Camp camp) {
        return all.add(camp);
    }

    public boolean update(Camp camp) {
        if (!all.contains(camp)) {
            return false;
        }
        all.set(all.indexOf(camp), camp);
        return true;
    }

    public boolean updateAll(List<Camp> all) {
        if (all == null) {
            return false;
        }
        this.all = all;
        return true;
    }

    public boolean remove(Camp camp) {
        if (!all.contains(camp)) {
            return false;
        }
        all.remove(camp);
        return true;
    }

    public Iterator<Camp> iterator() {
        return all.iterator();
    }

    public List<Camp> findByFilter(IFilter filter) {
        return null;
    }

    public boolean contains(Camp camp) {
        return all.contains(camp);
    }

    public boolean clear() {
        all.clear();
    }

    public Camp get(String id) {
        return all.stream()
                .filter(camp -> camp.getID().equals(id))
                .findFirst()
                .orElse(null); // Returns null if Camp is not found
    }

    public List<Camp> getAllCamps() {
        return all;
    }
}
