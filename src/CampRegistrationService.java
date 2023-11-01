import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CampRegistrationService {
    private CampRepository campRepository;

    public CampRegistrationService(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public boolean addStudent(Student student, int campId) {
        Camp camp = campRepository.get(campId);
        if (camp != null) {
            return camp.addAttendee(student);
        } else {
            // Handle the situation where the camp is full or not found
            return false;
        }
    }

    public boolean addCommitteeStudent(Student student, int campId) {
        Camp camp = campRepository.get(campId);
        if (camp != null) {
            return camp.addCommittee(student);
        } else {
            // Handle the situation where the camp is full or not found
            return false;
        }
    }

    public boolean withdrawStudent(Student student, int campId) {
        Camp camp = campRepository.get(campId);
        if (camp != null) {
            return camp.removeAttendee(student);
        } else {
            // Handle the situation where the camp is not found
            return false;
        }
    }

    public ArrayList<Camp> getCommitteeSlots() {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : campRepository.getAllCamps()) {
            if (camp.getCommittees().size() < camp.getCommitteeSlots()) {
                camps.add(camp);
            }
        }
        return camps;
    }

    public ArrayList<Camp> getJoinedCamps(User user) {
        ArrayList<Camp> joinedCamps = new ArrayList<>();
        for (Camp camp : campRepository.getAllCamps()) {
            Set<Student> attendees = camp.getAttendees();
            Set<Student> committees = camp.getCommittees();
            if (attendees.contains(user) || committees.contains(user)) {
                joinedCamps.add(camp);
            }
        }
        return joinedCamps;
    }
}
