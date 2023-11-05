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
}
