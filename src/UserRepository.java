import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserRepository {
    private List<User> userList;

    public UserRepository() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User getUserById(String id) {
        return userList.stream()
                .filter(user -> user.getID().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Static method to load data from an Excel file
    public static void loadDataFromExcel(String fileName, UserRepository repo) {
        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    String userId = getCellValueAsString(row.getCell(1)).split("@")[0];
                    String userName = getCellValueAsString(row.getCell(0));
                    String userFac = getCellValueAsString(row.getCell(2));

                    User u = (fileName.contains("staff")) ? new Staff(userId, userName, userFac) : new Student(userId, userName, userFac);
                    repo.addUser(u);
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellTypeEnum()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return cell.toString();
        }
    }
}
