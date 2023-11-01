public abstract class User {
    protected String ID;
    protected String name;
    protected String password;  // default set to 'password'
    protected String faculty;

    public User(String ID, String name, String faculty) {
        this.ID = ID;
        this.name = name;
        this.password = "password";
        this.faculty = faculty;
    }

    // Abstract methods
//    public abstract void answer(Enquiry enquiry, String answer);
    public abstract LoginResult login(String name, String password);
}

