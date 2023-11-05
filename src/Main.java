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

        //extract excel staff data
        try { 
            FileInputStream file = new FileInputStream( 
                new File("src\\data\\staff_list.xlsx")); 
  
            XSSFWorkbook workbook = new XSSFWorkbook(file); 
            XSSFSheet sheet = workbook.getSheetAt(0); 

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // For each Row.
                Row row = sheet.getRow(rowIndex);
                Cell staffNameFromExcel = CellUtil.getCell(row,0);
                Cell staffEmailFromExcel = CellUtil.getCell(row,1); // Get the Cell at the Index / Column you want.
                Cell staffFacFromExcel = CellUtil.getCell(row,2);
                String staffID = staffEmailFromExcel.getStringCellValue();
                String staffName = staffNameFromExcel.getStringCellValue();
                String staffFac = staffFacFromExcel.getStringCellValue();

                String tempString = "";
                    for (int i = 0; i < staffID.length(); i++) {
                        char c = staffID.charAt(i);
                        if(c == '@'){
                            break;
                        }
                        tempString +=c;   
                    }
                staffID = tempString;
                
                //System.out.println(staffID + " " + staffName + " " + staffFac);

                User u = new Staff(staffID, staffName, staffFac);
                userRepo.addUser(u);
                
            }
            file.close(); 
        } 
        catch (Exception e) {
            e.printStackTrace(); 
        }
        
        //extract excel student data
        try { 
            FileInputStream file = new FileInputStream( 
                new File("src\\data\\student_list.xlsx")); 
  
            XSSFWorkbook workbook = new XSSFWorkbook(file); 
            XSSFSheet sheet = workbook.getSheetAt(0); 

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // For each Row.
                Row row = sheet.getRow(rowIndex);
                Cell studNameFromExcel = CellUtil.getCell(row,0);
                Cell studEmailFromExcel = CellUtil.getCell(row,1); // Get the Cell at the Index / Column you want.
                Cell studFacFromExcel = CellUtil.getCell(row,2);
                
                String studID = studEmailFromExcel.getStringCellValue();
                String studName = studNameFromExcel.getStringCellValue();
                String studFac = studFacFromExcel.getStringCellValue();

                String tempString = "";
                    for (int i = 0; i < studID.length(); i++) {
                        char c = studID.charAt(i);
                        if(c == '@'){
                            break;
                        }
                        tempString +=c;   
                    }
                studID = tempString;
                
                //System.out.println(studID + " " + studName + " " + studFac);

                User u = new Student(studID, studName, studFac);
                userRepo.addUser(u);
                
            }
            file.close(); 
        } 
        catch (Exception e) {
            e.printStackTrace(); 
        }
        

        System.out.println("Welcome to Camp Allocation Management System (CAMS)");
        boolean loginSuccess = false;
        
        while(loginSuccess==false){
            System.out.println("Enter your UserID:");
            String UserID;
            System.out.println("Enter your password:");
            String password;
            UserID = sc.next();

            password = sc.next();

            //get userById using userRepo,check if valid
            User usr = userRepo.getUserById(UserID);

            if (usr ==null){
                System.out.println("User not found");
            }

            //need to determine user type first

            else{
                LoginResult logResult = new LoginResult();
                if (usr instanceof Student) {
                    Student stud = (Student) usr;
                    logResult = stud.login(UserID, password, usr);
                }
                else if (usr instanceof Staff) {    
                    Staff staf = (Staff) usr;
                    logResult = staf.login(UserID, password, usr);
                }
                

                //check if password is correct
                if (logResult.getLoginResult()==true){
                    loginSuccess = true;
                    System.out.println("Login Successful");
                }
                else{
                    System.out.println("Login Failed");
                }
            }
            
            
         }
    }
}