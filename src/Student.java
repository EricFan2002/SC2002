public class Student extends User {
    public Student(String ID, String name, String faculty) {
        super(ID, name, faculty);
        
    }

//    @Override
//    public void answer(Enquiry enquiry, String answer) {
//        // Implementation for Student
//        enquiry.setAnswer(answer);
//        enquiry.setAnsweredBy(this);
//    }

    @Override
    public LoginResult login(String name, String password,User usr) {
        LoginResult logResult = new LoginResult();
        logResult.setLoginResult(false);   // 0 = Staff, 1 = Student   
        logResult.setUserType(1);

        if (usr.getPassword().equals(password)){
            logResult.setLoginResult(true);
        }
        else{
            logResult.setLoginResult(false);
        }

        return logResult;
    }

    public void printMenu(){
        System.out.println("1. View Camps");    //Own User Group and Visibility ON,can filter
        System.out.println("2. View Remaining Vacancies"); //Own User Group and Visibility ON
        System.out.println("3. Register for Camp"); //as attendee or committee, camp not full, before deadline, no date clashes
        System.out.println("4. Submit Camp Enquiry");
        System.out.println("5. View Camp Enquiry");//before processed
        System.out.println("6. Edit Camp Equiry");//before processed
        System.out.println("7. Delete Camp Enquiry");//before processed
        System.out.println("8. View Status");//see registered camps and roles
        System.out.println("9. Withdraw From Camp");
    }
}
