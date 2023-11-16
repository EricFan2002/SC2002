package controller.user;

import entity.RepositoryCollection;

public class UserAccountManagementController {

    public static boolean changePassword(String username, String oldPassword, String newPassword) {
        if (RepositoryCollection.getUserRepository().getAll().filterByID(username).get(0).getPassword()
                .equals(oldPassword)) {
            RepositoryCollection.getUserRepository().getAll().filterByID(username).get(0).setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    public static String forgetPassword(String username, String email) {
        if (RepositoryCollection.getUserRepository().getAll().filterByID(username).get(0).getEmail().equals(email)) {
            return RepositoryCollection.getUserRepository().getAll().filterByID(username).get(0).getPassword();
        } else {
            return null;
        }
    }

    public static boolean verifyPassword(String username, String password) {
        if (RepositoryCollection.getUserRepository().getAll().filterByID(username).get(0).getPassword()
                .equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyEmail(String username, String email) {
        if (RepositoryCollection.getUserRepository().getAll().filterByID(username).get(0).getEmail().equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean AccountSecurity() {
        return true;
    }
}
