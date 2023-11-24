package entity.user;

import entity.interfaces.ITaggedItem;

/**
 * The {@code User} abstract class provides a basis for creating user objects with common attributes.
 * It implements the {@code ITaggedItem} interface.
 */
public abstract class User implements ITaggedItem {

    /**
     * The user's ID.
     */
    protected String ID;

    /**
     * The user's name.
     */
    protected String name;

    /**
     * The user's password (default set to 'password').
     */
    protected String password;

    /**
     * The user's faculty.
     */
    protected String faculty;

    /**
     * Constructs a User object with default values.
     */
    public User() {
        this.ID = "";
        this.name = "";
        this.password = "password";
        this.faculty = "";
    }

    /**
     * Constructs a User object with the specified ID, name, and faculty.
     *
     * @param ID      The ID of the user.
     * @param name    The name of the user.
     * @param faculty The faculty of the user.
     */
    public User(String ID, String name, String faculty) {
        this.ID = ID;
        this.name = name;
        this.password = "password";
        this.faculty = faculty;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public String getID() {
        return ID;
    }

    /**
     * Sets the ID of the user.
     *
     * @param ID The ID to be set.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Sets the faculty of the user.
     *
     * @param faculty The faculty to be set.
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Gets the faculty of the user.
     *
     * @return The faculty of the user.
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return ID + "@e.ntu.edu.sg";
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

