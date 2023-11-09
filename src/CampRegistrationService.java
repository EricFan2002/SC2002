import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.camp.Camp;
import entity.user.Student;
import entity.user.User;
import entity.CampRepository;

public class CampRegistrationService {
    private CampRepository campRepository;

    public CampRegistrationService(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public boolean addStudent(Student student, String campId) {
        Camp camp = campRepository.getAll().filterByID(campId).get(0);
        if (camp != null) {
            return camp.addAttendee(student);
        } else {
            // Handle the situation where the camp is full or not found
            return false;
        }
    }

    public boolean addCommitteeStudent(Student student, String campId) {
        Camp camp = campRepository.getAll().filterByID(campId).get(0);
        if (camp != null) {
            return camp.addCommittee(student);
        } else {
            // Handle the situation where the camp is full or not found
            return false;
        }
    }

    public boolean withdrawStudent(Student student, String campId) {
        Camp camp = campRepository.getAll().filterByID(campId).get(0);
        if (camp != null) {
            return camp.removeAttendee(student);
        } else {
            // Handle the situation where the camp is not found
            return false;
        }
    }

    public ArrayList<Camp> getCommitteeSlots() {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp: campRepository.getAll()) {
            if (camp.getCommittees().size() < Camp.MAX_COMMITTEE) {
                camps.add(camp);
            }
        }
        return camps;
    }

    public ArrayList<Camp> getJoinedCamps(User user) {
        ArrayList<Camp> joinedCamps = new ArrayList<>();
        for (Camp camp: campRepository.getAll()) {
            Set<Student> attendees = camp.getAttendees();
            Set<Student> committees = camp.getCommittees();
            if (attendees.contains(user) || committees.contains(user)) {
                joinedCamps.add(camp);
            }
        }
        return joinedCamps;
    }
}
