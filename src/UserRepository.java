import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class UserRepository extends Repository<User> {
    public void load() {
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
        } catch (IOException eIoException) {
            System.out.println(eIoException.toString());
        } finally {
            try {
                fileReader.close();
            } catch (IOException eIoException) {
                System.out.println(eIoException.toString());
            }
        }
    }
    /*
     * private List<User> userList;
     * 
     * public UserRepository() {
     * this.userList = new ArrayList<>();
     * }
     * 
     * public void addUser(User user) {
     * userList.add(user);
     * }
     * 
     * public User getUserById(String id) {
     * return userList.stream()
     * .filter(user -> user.getID().equals(id))
     * .findFirst()
     * .orElse(null);
     * }
     * 
     * // Static method to load data from an Excel file
     * public static void loadDataFromExcel(String fileName, UserRepository repo) {
     * try {
     * FileInputStream file = new FileInputStream(new File(fileName));
     * Workbook workbook = new XSSFWorkbook(file);
     * Sheet sheet = workbook.getSheetAt(0);
     * 
     * for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
     * Row row = sheet.getRow(rowIndex);
     * if (row != null) {
     * String userId = getCellValueAsString(row.getCell(1)).split("@")[0];
     * String userName = getCellValueAsString(row.getCell(0));
     * String userFac = getCellValueAsString(row.getCell(2));
     * 
     * User u = (fileName.contains("staff")) ? new Staff(userId, userName, userFac)
     * : new Student(userId, userName, userFac);
     * repo.addUser(u);
     * }
     * }
     * file.close();
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     * 
     * private static String getCellValueAsString(Cell cell) {
     * if (cell == null) return "";
     * switch (cell.getCellTypeEnum()) {
     * case STRING: return cell.getStringCellValue();
     * case NUMERIC: return String.valueOf(cell.getNumericCellValue());
     * case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
     * default: return cell.toString();
     * }
     * }
     */
}
