public abstract class User {
    protected String ID;
    protected String name;
    protected String password; // default set to 'password'
    protected String faculty;

    public User(String ID, String name, String faculty) {
        this.ID = ID;
        this.name = name;
        this.password = "password";
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Abstract methods
    // public abstract void answer(Enquiry enquiry, String answer);
    public abstract LoginResult login(String name, String password, User usr);
}
