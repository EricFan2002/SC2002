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
        boolean quit = false;
        User usr = null;

        while(quit==false){
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
                        System.out.println("Login Successful\n");
                    } else {
                        System.out.println("Login Failed\n");
                    }
                }
            }
            boolean logout = false;

            if(loginSuccess==true){
                while(logout==false){

                    if(usr instanceof Student){
                        Student stud = (Student) usr;
                        stud.printMenu();
                        int choice = sc.nextInt();

                        switch(choice){
                            case 1:
                                //
                                break;
                            case 2:
                                //
                                break;
                            case 3:
                                //
                                break;
                            case 4:
                                //
                                break;
                            case 5:
                                //
                                break;
                            case 6:
                                //
                                break;
                            case 7:
                                //
                                break;
                            case 8:
                                //
                                break;
                            case 9:
                                //
                                break;
                            case 10:
                                String newPassword;
                                System.out.println("Enter new password:");
                                newPassword = sc.next();
                                usr.setPassword(newPassword);
                                break;
                            case 11:
                                logout = true;
                                break;
                        }
                    }
                    else if(usr instanceof Staff){
                        Staff staf = (Staff) usr;
                        staf.printMenu();
                        int choice = sc.nextInt();
                        switch(choice){
                            case 1:
                                //
                                break;
                            case 2:
                                //
                                break;
                            case 3:
                                //
                                break;
                            case 4:
                                //
                                break;
                            case 5:
                                //
                                break;
                            case 6:
                                //
                                break;
                            case 7:
                                String newPassword;
                                System.out.println("Enter new password:");
                                newPassword = sc.next();
                                usr.setPassword(newPassword);
                                break;
                            case 8:
                                logout = true;
                                break;               
                        }
                    }
                }
            }
            System.out.println("Do you want to quit? (Y/N)");
            if(sc.next().equals("Y")){
                quit=true;//
            }
            else{
                loginSuccess=false;
            }
        }



    }

}