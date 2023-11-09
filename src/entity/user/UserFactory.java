package entity.user;

public class UserFactory {
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
