package app.entity.user;

/**
 * The {@code UserFactory} class provides a factory method for creating user objects based on the specified user type.
 */
public class UserFactory {

    /**
     * Creates and returns a user object based on the specified user type.
     *
     * @param userType The type of the user (e.g., "student" or "staff").
     * @param ID       The ID of the user.
     * @param name     The name of the user.
     * @param faculty  The faculty of the user.
     * @return A user object of the specified type, or {@code null} if the user type is unknown.
     */
    public static User getUser(String userType, String ID, String name, String faculty) {
        if (userType.equalsIgnoreCase("student")) {
            return new Student(ID, name, faculty);
        } else if (userType.equalsIgnoreCase("staff")) {
            return new Staff(ID, name, faculty);
        } else {
            return null;
        }
    }
}

