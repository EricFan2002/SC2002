import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            CampRepository campRepo = new CampRepository();

            if(loginSuccess==true){
                while(logout==false){

                    if(usr instanceof Student){
                        Student stud = (Student) usr;
                        stud.printMenu();
                        int choice = sc.nextInt();

                        switch(choice){
                            case 1:

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
                                //to create camp, create Camp object, add Camp to CampRepo using CampModificationService,
                                System.out.println("Enter camp ID/name:");
                                System.out.println("Enter camp description:");
                                System.out.println("Enter camp start date:");
                                System.out.println("Enter camp end date:");
                                System.out.println("Enter camp registration closing date:");
                                System.out.println("Enter camp group:");
                                System.out.println("Enter camp location:");
                                System.out.println("Enter staff in charge:");
                                System.out.println("Enter number of attendee slots:");
                                
                                //need to check for uniqueness of camp ID/name
                                    //if not unique, prompt user to enter again
                                    //else print everything else
                                
                                int id = sc.nextInt();
                                String description = sc.next();

                                Date startDate = null;
                                 // Prompt the user to enter a date in a specific format
                                final String INVALID_DATE_FORMAT_MSG = "Invalid date format. Please use yyyy-MM-dd.";


                                while(startDate == null){
                                    System.out.print("Enter a camp start date (yyyy-MM-dd): ");
                                    String userInput = sc.next();

                                    // Define a SimpleDateFormat pattern for the expected date format
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                                    try {
                                        // Parse the user input into a Date object
                                        startDate = dateFormat.parse(userInput);
                                        if (startDate != null) {
                                            System.out.println("Start Date: " + startDate);
                                        } else {
                                            System.out.println(INVALID_DATE_FORMAT_MSG);
                                        }
                                    } catch (ParseException e) {
                                        System.out.println(INVALID_DATE_FORMAT_MSG);
                                    }
                                }
                                
                                Date endDate = null;
                                 // Prompt the user to enter a date in a specific format
                                while(endDate == null){
                                    System.out.print("Enter a camp end date (yyyy-MM-dd): ");
                                    String userInput = sc.next();

                                    // Define a SimpleDateFormat pattern for the expected date format
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                                    try {
                                        // Parse the user input into a Date object
                                        endDate = dateFormat.parse(userInput);
                                        if (endDate != null) {
                                            System.out.println("End Date: " + endDate);
                                        } else {
                                            System.out.println(INVALID_DATE_FORMAT_MSG);
                                        }
                                    } catch (ParseException e) {
                                        System.out.println(INVALID_DATE_FORMAT_MSG);
                                    }
                                }

                                Date registrationClosingDate = null;
                                 // Prompt the user to enter a date in a specific format
                                while(registrationClosingDate == null){
                                    System.out.print("Enter a camp registration closing date (yyyy-MM-dd): ");
                                    String userInput = sc.next();

                                    // Define a SimpleDateFormat pattern for the expected date format
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                                    try {
                                        // Parse the user input into a Date object
                                        registrationClosingDate = dateFormat.parse(userInput);
                                        if (registrationClosingDate != null) {
                                            System.out.println("Registration Closing Date: " + registrationClosingDate);
                                        } else {
                                            System.out.println(INVALID_DATE_FORMAT_MSG);
                                        }
                                    } catch (ParseException e) {
                                        System.out.println(INVALID_DATE_FORMAT_MSG);
                                    }
                                }
                                
                                
                                String group = sc.next();
                                String location = sc.next();
                                
                                //Staff staffInCharge = new Staff();
                                
                                int attendeeSlots = sc.nextInt();
                                
                                

                                //Camp camp = new Camp();


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