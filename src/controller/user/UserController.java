package controller.user;
import entity.user.User;

public class UserController {

    public boolean login(String username, String password) {
        User user = getUserByUsername(username);

        // Validate user's password
        if (user != null && user.getPassword().equals(password)) {
            return true; // Login successful
        } else {
            return false; // Login failed
        }
    }

    public void update(User oldUser, User newUser) {
        // Update the old user with the new user information
        oldUser.setName(newUser.getName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setFaculty(newUser.getFaculty());
    }

    private User getUserByUsername(String username) {
        return null;
    }


}