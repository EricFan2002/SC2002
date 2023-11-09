import java.util.Date;

import entity.CampList;
import entity.CampRepository;
import entity.UserList;
import entity.UserRepository;
import entity.camp.Camp;
import entity.user.Staff;
import entity.user.Student;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository("data/user.csv");
        userRepository.load();

        CampRepository campRepository = new CampRepository("data/camp.csv", userRepository);
        campRepository.load();

        System.out.println(campRepository.getAll().size());

        Student att1 = new Student("1", "John Doe", "NBS");
        Student att2 = new Student("2", "Jane Doe", "EEE");
        Student att3 = new Student("3", "John Smith", "MAE");
        Student com = new Student("4", "Alan Walker", "SPMS");
        Staff staff = new Staff("5", "John Cena", "SPMS");

        UserList userList = userRepository.getAll();
        userList.add(att1);
        userList.add(att2);
        userRepository.insert(att3);
        userRepository.insert(com);
        userRepository.insert(staff);

        Camp test = new Camp("420", "Camp Nou", "Soccer camp for young students!", true, new Date(1), new Date(1),
                new Date(1), "1", "1", staff);

        campRepository.insert(test);
        test.addAttendee(att3);
        test.addAttendee(att2);
        test.addAttendee(att1);
        test.addCommittee(com);

        campRepository.save();
        userRepository.save();

    }
}
