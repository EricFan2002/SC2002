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

        System.out.println("Welcome to Camp Allocation Management System (CAMS)");
        boolean loginSuccess = false;
        User usr = null;

        while (loginSuccess == false) {
            System.out.println("Enter your UserID:");
            String UserID;
            System.out.println("Enter your password:");
            String password;
            UserID = sc.next();

            password = sc.next();

            // get userById using userRepo,check if valid
            usr = userRepo.getUserById(UserID);

            if (usr == null) {
                System.out.println("User not found");
            } else {
                LoginResult logResult = new LoginResult();
                if (usr instanceof Student) {
                    Student stud = (Student) usr;
                    logResult = stud.login(UserID, password, usr);
                } else if (usr instanceof Staff) {
                    Staff staf = (Staff) usr;
                    logResult = staf.login(UserID, password, usr);
                }

                // check if password is correct
                if (logResult.getLoginResult() == true) {
                    loginSuccess = true;
                    System.out.println("Login Successful");
                } else {
                    System.out.println("Login Failed");
                }
            }
        }

        if(loginSuccess==true){
            if(usr instanceof Student){
                Student stud = (Student) usr;
                stud.printMenu();
            }
            else if(usr instanceof Staff){
                Staff staf = (Staff) usr;
                staf.printMenu();
            }
        }



    }

}