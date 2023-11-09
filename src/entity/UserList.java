package entity;

import java.util.List;

import entity.user.User;

public class UserList extends RepositoryList<User> implements IFilterableByID<User> {
    public UserList(List<User> all) {
        super(all);
    }

    public UserList() {
        super();
    }

    public UserList filterByID(String id) {
        UserList result = new UserList();
        for (User user : super.all) {
            if (user.getID().equals(id)) {
                result.add(user);
            }
        }
        return result;
    }
}