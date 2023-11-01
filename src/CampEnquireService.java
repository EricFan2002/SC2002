import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CampEnquireService {
    private CampRepository campRepository;
    private List<Enquiry> enquires;

    public CampEnquireService(CampRepository campRepository) {
        this.campRepository = campRepository;
        this.enquires = new ArrayList<>();
    }

    public void enquiry(Student student, String question) {
        Enquiry newEnquiry = new Enquiry(student, question);
        enquires.add(newEnquiry);
    }

    public List<Enquiry> getSubmittedEnquires(int campId, Student student) {
        return enquires.stream()
                       .filter(enquiry -> enquiry.getSender().equals(student) && 
                                          campRepository.get(campId) != null)
                       .collect(Collectors.toList());
    }

    public List<Enquiry> getRespondedEnquires(User user) {
        return enquires.stream()
                       .filter(enquiry -> enquiry.getAnsweredBy() != null && enquiry.getAnsweredBy().equals(user))
                       .collect(Collectors.toList());
    }
}
