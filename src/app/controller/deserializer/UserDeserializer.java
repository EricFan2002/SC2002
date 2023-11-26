package app.controller.deserializer;

import java.util.ArrayList;

import app.entity.user.Student;
import app.entity.user.User;
import app.entity.user.UserFactory;
import app.entity.user.UserList;

/**
 * The UserDeserializer class contains methods to deserialize user data into a
 * UserList object.
 */
public class UserDeserializer {

    /**
     * Private constructor to prevent instantiation.
     */
    private UserDeserializer() {
    }

    /**
     * Deserializes the provided data into a UserList object containing User
     * entities.
     *
     * @param data The data to deserialize, represented as an ArrayList of
     *             ArrayLists of Strings.
     * @return A UserList object populated with deserialized user data.
     */
    public static UserList deserialize(ArrayList<ArrayList<String>> data) {
        UserList cur = new UserList();
        data.forEach(record -> {
            // Extracting data from the record to create a User object
            // id, name, password, faculty, type, points
            String id = record.get(0);
            String name = record.get(1);
            String password = record.get(2);
            String faculty = record.get(3);
            int type = Integer.parseInt(record.get(4));

            String typeName = (type == 1) ? "Staff" : "Student";

            String pointsRaw = record.get(5);

            // Creating a User object based on the extracted data
            User user = UserFactory.getUser(typeName, id, name, faculty);

            // Checking if the user is an instance of Student to set points
            if (user instanceof Student) {
                ((Student) user).setPoints(Integer.parseInt(pointsRaw));
            }

            // Adding the user to the UserList if it's not null
            if (user != null) {
                user.setPassword(password);
                cur.add(user);
                // add to UserList which extends Repository List
            }

            cur.add(user);
        });

        return cur;

    }
}
