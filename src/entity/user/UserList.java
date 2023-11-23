package entity.user;

import java.util.ArrayList;
import java.util.List;

import entity.RepositoryList;
import entity.interfaces.IFilterableByID;

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
            // System.out.println(user.getID() + " want " + id);
            if (user.getID().equals(id)) {
                result.add(user);
            }
        }
        return result;
    }

    public User[] toArray() {
        return super.all.toArray(new User[super.all.size()]);
    }

    public boolean isEmpty() {
        return super.all.isEmpty();
    }

    public ArrayList<ArrayList<String>> serialize() {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        all.forEach(userRaw -> {
            User user = userRaw;
            ArrayList<String> record = new ArrayList<String>();
            String userType = (user instanceof Staff) ? "1" : "0";
            String pointString = (user instanceof Student) ? Integer.toString(((Student) user).getPoints())
                    : "0";
            record.add(user.getID());
            record.add(user.getName());
            record.add(user.getPassword());
            record.add(user.getFaculty());
            record.add(userType);
            record.add(pointString);

            result.add(record);
        });

        return result;
    }
}
