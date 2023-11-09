package entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import entity.user.Staff;
import entity.user.User;
import entity.user.UserFactory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

public class UserRepository extends Repository<User> {
    public boolean load() {
        super.all.clear();
        Reader fileReader = null;
        try {
            fileReader = new FileReader(super.filePath);
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
                    super.all.add(user);
                }
            });
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return false;
        } catch (IOException eIoException) {
            System.out.println(eIoException.toString());
            return false;
        } finally {
            try {
                fileReader.close();
            } catch (IOException eIoException) {
                System.out.println(eIoException.toString());
                return false;
            }
        }
        return true;
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
}
