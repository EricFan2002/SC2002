package controller.camp;

import entity.enquiry.EnquiryList;
import entity.RepositoryCollection;
import entity.enquiry.Enquiry;
import entity.camp.Camp;
import entity.user.Student;
import entity.user.User;

public class CampEnquiryController {

    public static boolean createEnquiry(Enquiry enquiry) {
        RepositoryCollection.enquiryRepository.insert(enquiry);
        return true;
    }

    public static Enquiry getEnquiry(String enquiryID) {
        EnquiryList filteredEnquiries = RepositoryCollection.enquiryRepository.getAll().filterByID(enquiryID);
        return filteredEnquiries.size() == 0 ? null : filteredEnquiries.get(0);
    }

    public static EnquiryList getEnquiries() {
        return RepositoryCollection.enquiryRepository.getAll();
    }

    public static EnquiryList getEnquiries(Camp camp) {
        return RepositoryCollection.enquiryRepository.getAll().filterByCamp(camp);
    }

    public static EnquiryList getEnquiries(User user) {
        return RepositoryCollection.enquiryRepository.getAll().filterBySender(user);
    }

    public static boolean updateEnquiry(Enquiry newEnquiry) {
        return RepositoryCollection.enquiryRepository.update(newEnquiry);
    }

    public static boolean deleteEnquiry(Enquiry enquiry) {
        return RepositoryCollection.enquiryRepository.remove(enquiry);
    }

    public static boolean answerEnquiry(Enquiry enquiry, User answeringUser, String answer) {
        enquiry.setAnswer(answer, answeringUser);
        if (answeringUser instanceof Student answeringStudent) {
            answeringStudent.addPoints(1);
        }
        return RepositoryCollection.enquiryRepository.update(enquiry);
    }
}
