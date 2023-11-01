import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> userList;

    public UserRepository() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User getUserById(String id) {
        return userList.stream()
                .filter(user -> user.getID().equals(id))
                .findFirst()
                .orElse(null); // Returns null if User is not found
    }

    public void updateUser(User user) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getID().equals(user.getID())) {
                userList.set(i, user);
                return;
            }
        }
    }

    public void removeUser(String id) {
        userList.removeIf(user -> user.getID().equals(id));
    }

}
