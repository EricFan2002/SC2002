package entity.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entity.RepositoryList;
import entity.interfaces.IFilterableByID;
import entity.interfaces.IFilterableBySchool;

/**
 * The {@code UserList} class represents a list of user objects.
 * It extends the {@code RepositoryList} class and implements the {@code IFilterableByID} and {@code ISerializeable} interfaces.
 */
public class UserList extends RepositoryList<User> implements IFilterableByID<User>, IFilterableBySchool<User> {

    /**
     * Constructs a UserList object with the specified list of users.
     *
     * @param all The list of users to be included in the user list.
     */
    public UserList(List<User> all) {
        super(all);
    }

    /**
     * Constructs an empty UserList object.
     */
    public UserList() {
        super();
    }

    /**
     * Filters the user list by the specified user ID.
     *
     * @param id The user ID to filter by.
     * @return A UserList containing users with the specified ID.
     */
    public UserList filterByID(String id) {
        UserList result = new UserList();
        for (User user : super.all) {
            if (user.getID().equals(id)) {
                result.add(user);
            }
        }
        return result;
    }

    public UserList filterBySchool(String school){
        UserList result = new UserList();
        for(User user: super.all){
            if(Objects.equals(user.getSchool(), school)){
                result.add(user);
            }
        }
        return result;
    }

    /**
     * Converts the user list to an array of users.
     *
     * @return An array of users.
     */
    public User[] toArray() {
        return super.all.toArray(new User[super.all.size()]);
    }

    /**
     * Checks if the user list is empty.
     *
     * @return {@code true} if the user list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return super.all.isEmpty();
    }

    /**
     * Serializes the user list and represents its data as an ArrayList of ArrayList of Strings.
     *
     * @return An {@code ArrayList<ArrayList<String>>} representing the serialized data of the user list.
     */
    public ArrayList<ArrayList<String>> serialize() {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        all.forEach(userRaw -> {
            User user = userRaw;
            ArrayList<String> record = new ArrayList<String>();
            String userType = (user instanceof Staff) ? "1" : "0";
            String pointString = (user instanceof Student) ? Integer.toString(((Student) user).getPoints()) : "0";
            record.add(user.getID());
            record.add(user.getName());
            record.add(user.getPassword());
            record.add(user.getSchool());
            record.add(userType);
            record.add(pointString);

            result.add(record);
        });

        return result;
    }
}

