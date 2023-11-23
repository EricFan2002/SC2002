package controller.deserializer;

import java.util.ArrayList;

import entity.user.Student;
import entity.user.User;
import entity.user.UserFactory;
import entity.user.UserList;

public class UserDeserializer {

    public static UserList deserialize(ArrayList<ArrayList<String>> data) {
        UserList cur = new UserList();
        data.forEach(record -> {
            // id, name, password, faculty, type, points
            String id = record.get(0);
            String name = record.get(1);
            String password = record.get(2);
            String faculty = record.get(3);
            int type = Integer.parseInt(record.get(4));

            String typeName = (type == 1) ? "Staff" : "Student";

            String pointsRaw = record.get(5);

            User user = UserFactory.getUser(typeName, id, name, faculty);
            if (user instanceof Student) {
                ((Student) user).setPoints(Integer.parseInt(pointsRaw));
            }

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
