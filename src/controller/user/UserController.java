package controller.user;

import entity.RepositoryCollection;
import entity.user.User;

public class UserController {
    private static User currentUser;

    public static boolean login(String username, String password) {
        User user = getUserByUsername(username);

        // Validate user's password
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true; // Login successful
        } else {
            return false; // Login failed
        }
    }

    public static void update(User oldUser, User newUser) {
        // Update the old user with the new user information
        oldUser.setName(newUser.getName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setFaculty(newUser.getFaculty());
    }

    private static User getUserByUsername(String id) {
        var userList = RepositoryCollection.userRepository.getAll().filterByID(id);
        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            // Handle case where user is not found
            return null;
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}