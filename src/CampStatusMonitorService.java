import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import entity.UserRepository;
import entity.CampRepository;
import entity.camp.Camp;
import entity.user.User;
import entity.user.Student;

public class CampStatusMonitorService {
    private CampRepository campRepository;
    private UserRepository userRepository;

    public CampStatusMonitorService(CampRepository campRepository, UserRepository userRepository) {
        this.campRepository = campRepository;
        this.userRepository = userRepository;
    }

    public ArrayList<User> getAttendingStudents(String id) {
        Camp camp = campRepository.getAll().filterByID(id).get(0);
        ArrayList<User> attendingStudents = new ArrayList<>();
        if (camp != null) {
            // Assuming Camp has a method to get all attending students
            attendingStudents.addAll(camp.getAttendees());
        }
        return attendingStudents;
    }

    public Map<String, Integer> generatePerformanceReport(Camp camp) {
        Map<String, Integer> performanceReport = new HashMap<>();
        Set<Student> students = camp.getCommittees();
        for (Student student : students) {
            performanceReport.put(student.getName(), student.getPoints());
        }
        return performanceReport;
    }
}