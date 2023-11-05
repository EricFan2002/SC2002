public class Staff extends User {
	public Staff(String ID, String name, String faculty) {
        super(ID, name, faculty);
    }

//    @Override
//    public void answer(Enquiry enquiry, String answer) {
//        // Implementation for Staff
//        enquiry.setAnswer(answer);
//        enquiry.setAnsweredBy(this);
//    }

    @Override
    public LoginResult login(String name, String password,User usr) {
        LoginResult logResult = new LoginResult();
        logResult.setLoginResult(false);   // 0 = Staff, 1 = Student   
        logResult.setUserType(0);

        if (usr.getPassword().equals(password)){
            logResult.setLoginResult(true);
        }
        else{
            logResult.setLoginResult(false);
        }

        return logResult;
    }

    public void printMenu(){
        System.out.println("1. Create Camps");
        System.out.println("2. Edit Camps");
        System.out.println("3. Delete Camps");
        System.out.println("4. Toggle Visibility of Camps");
        System.out.println("5. View All Camps");
        System.out.println("6. View Camps Created by You");
    }

    //triggered by option 6 from above
    public void printCreatedCampsMenu(){
        System.out.println("1. View Enquiries");
        System.out.println("2. Reply Enquiries");
        System.out.println("3. View Suggestions");
        System.out.println("4. Approve/Reject Suggestions");
        System.out.println("5. Genereate Attendance Report");
        System.out.println("6. Generate Performance Report");
    }
}
