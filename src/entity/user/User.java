package entity.user;

import entity.interfaces.ITaggedItem;

public abstract class User implements ITaggedItem {
    protected String ID;
    protected String name;
    protected String password; // default set to 'password'
    protected String faculty;

    public User() {
        this.ID = "";
        this.name = "";
        this.password = "password";
        this.faculty = "";
    }

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

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() { return ID + "@e.ntu.edu.sg"; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
