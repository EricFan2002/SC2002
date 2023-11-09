import entity.UserRepository;

public class AccountManagementService {
    private UserRepository userRepository;

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        if (userRepository.getAll().filterByID(username).get(0).getPassword().equals(oldPassword)) {
            userRepository.getAll().filterByID(username).get(0).setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    public String forgetPassword(String username, String email) {
        if (userRepository.getAll().filterByID(username).get(0).getEmail().equals(email)) {
            return userRepository.getAll().filterByID(username).get(0).getPassword();
        } else {
            return null;
        }
    }

    public boolean verifyPassword(String username, String password) {
        if (userRepository.getAll().filterByID(username).get(0).getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyEmail(String username, String email) {
        if (userRepository.getAll().filterByID(username).get(0).getEmail().equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AccountSecurity() {
        return true;
    }
}
