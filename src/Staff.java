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
    public LoginResult login(String name, String password) {
        // Implementation for Staff login
        return null;  // Placeholder
    }
}
