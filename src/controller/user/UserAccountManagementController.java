package controller.user;

import entity.RepositoryCollection;

/**
 * The {@code UserAccountManagementController} class manages user account
 * operations within the system.
 * It includes methods for changing passwords, retrieving forgotten passwords,
 * and verifying user credentials.
 *
 * <p>
 * Key functionalities include:
 * <ul>
 * <li>Changing a user's password.
 * <li>Handling forgotten password scenarios.
 * <li>Verifying user passwords and emails.
 * </ul>
 *
 * @see entity.RepositoryCollection
 */
public class UserAccountManagementController {

    /**
     * Changes a user's password if the old password provided is correct.
     * 
     * @param username    The username of the user whose password is being changed.
     * @param oldPassword The current password of the user for verification.
     * @param newPassword The new password to set for the user.
     * @return boolean True if the password change is successful, false if the old
     *         password is incorrect.
     */
    public static boolean changePassword(String username, String oldPassword, String newPassword) {
        if (RepositoryCollection.userRepository.getAll().filterByID(username).get(0).getPassword()
                .equals(oldPassword)) {
            RepositoryCollection.userRepository.getAll().filterByID(username).get(0).setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves the user's password if the provided email matches the one
     * associated with the username.
     * Used in scenarios where a user forgets their password.
     * 
     * @param username The username of the user who forgot their password.
     * @param email    The email to be validated against the user's email.
     * @return String The user's password if the email matches, or null if it does
     *         not.
     */
    public static String forgetPassword(String username, String email) {
        if (RepositoryCollection.userRepository.getAll().filterByID(username).get(0).getEmail().equals(email)) {
            return RepositoryCollection.userRepository.getAll().filterByID(username).get(0).getPassword();
        } else {
            return null;
        }
    }

    /**
     * Verifies if the given password matches the password of the specified user.
     * 
     * @param username The username of the user whose password is to be verified.
     * @param password The password to check against the user's actual password.
     * @return boolean True if the provided password matches the user's password,
     *         false otherwise.
     */
    public static boolean verifyPassword(String username, String password) {
        if (RepositoryCollection.userRepository.getAll().filterByID(username).get(0).getPassword()
                .equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifies if the given email matches the email of the specified user.
     * 
     * @param username The username of the user whose email is to be verified.
     * @param email    The email to check against the user's actual email.
     * @return boolean True if the provided email matches the user's email, false
     *         otherwise.
     */
    public static boolean verifyEmail(String username, String email) {
        if (RepositoryCollection.userRepository.getAll().filterByID(username).get(0).getEmail().equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Placeholder method for account security checks. Currently, it always returns
     * true.
     * 
     * @return boolean Always returns true.
     */
    public static boolean AccountSecurity() {
        return true;
    }
}
