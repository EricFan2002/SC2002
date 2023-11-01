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
    public LoginResult login(String name, String password) {
        // Implementation for Student login
        // Example: return new Result(status, userType);
        return null;  // Placeholder
    }
}
