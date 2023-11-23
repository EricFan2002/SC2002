package controller.user;

import entity.RepositoryCollection;
import entity.user.User;

/**
 * The {@code UserController} class manages user-related operations such as
 * login and user information updates.
 * It facilitates user authentication and maintains a reference to the currently
 * logged-in user.
 *
 * <p>
 * Key functionalities include:
 * <ul>
 * <li>Authenticating users via login.
 * <li>Updating user information.
 * <li>Retrieving the currently logged-in user.
 * </ul>
 *
 * @see entity.RepositoryCollection
 * @see entity.user.User
 */
public class UserController {
    private static User currentUser;

    /**
     * Authenticates a user based on username and password.
     * If authentication is successful, the user is set as the current user.
     *
     * @param username The username of the user trying to log in.
     * @param password The password of the user.
     * @return boolean True if login is successful, false otherwise.
     */
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

    /**
     * Updates an existing user's information with new data provided in another user
     * object.
     * 
     * @param oldUser The existing user object to be updated.
     * @param newUser The new user object containing updated information.
     */
    public static void update(User oldUser, User newUser) {
        // Update the old user with the new user information
        oldUser.setName(newUser.getName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setFaculty(newUser.getFaculty());
    }

    /**
     * Retrieves a user by their username.
     * 
     * @param username The username of the user to retrieve.
     * @return User The user object if found, null otherwise.
     */
    private static User getUserByUsername(String id) {
        var userList = RepositoryCollection.getUserRepository().filterByID(id);
        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            // Handle case where user is not found
            return null;
        }
    }

    /**
     * Gets the currently logged-in user.
     * 
     * @return User The currently logged-in user, or null if no user is logged in.
     */
    public static User getCurrentUser() {
        return currentUser;
    }
}