import java.util.ArrayList;
import java.util.List;

public class CampStatusMonitorService {
    private CampRepository campRepository;
    private UserRepository userRepository;

    public CampStatusMonitorService(CampRepository campRepository, UserRepository userRepository) {
        this.campRepository = campRepository;
        this.userRepository = userRepository;
    }

    public ArrayList<User> getAttendingStudents(int id) {
        Camp camp = campRepository.get(id);
        ArrayList<User> attendingStudents = new ArrayList<>();
        if (camp != null) {
            // Assuming Camp has a method to get all attending students
            attendingStudents.addAll(camp.getAttendees());
        }
        return attendingStudents;
    }

    public ArrayList<PerformanceReport> generatePerformanceReport(int id) {
        ArrayList<PerformanceReport> reports = new ArrayList<>();
        // Logic to generate performance report for the camp
        // Assuming there's a method to get the performance reports for a camp
        Camp camp = campRepository.get(id);
        if (camp != null) {
            for(User user : camp.getCommittees()){
                if(user instanceof CommitteeStudent) {
                    CommitteeStudent committeeStudent = (CommitteeStudent)user;
                    reports.add(committeeStudent.getPerformanceReport());
                }
            }
        }
        return reports;
    }
}