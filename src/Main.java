import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellUtil;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserRepository userRepo = new UserRepository();

        // Load staff data
        UserRepository.loadDataFromExcel("src/data/staff_list.xlsx", userRepo);

        // Load student data
        UserRepository.loadDataFromExcel("src/data/student_list.xlsx", userRepo);

    }
}