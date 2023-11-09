import java.util.ArrayList;
import java.util.List;

import entity.UserRepository;
import entity.CampRepository;
import entity.camp.Camp;
import entity.user.User;

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

    public ArrayList<PerformanceReport> generatePerformanceReport(String id) {
        ArrayList<PerformanceReport> reports = new ArrayList<>();
        // Logic to generate performance report for the camp
        // Assuming there's a method to get the performance reports for a camp
        Camp camp = campRepository.getAll().filterByID(id).get(0);
        if (camp != null) {
            for (User user : camp.getCommittees()) {
                if (user instanceof CommitteeStudent) {
                    CommitteeStudent committeeStudent = (CommitteeStudent) user;
                    reports.add(committeeStudent.getPerformanceReport());
                }
            }
        }
        return reports;
    }
}