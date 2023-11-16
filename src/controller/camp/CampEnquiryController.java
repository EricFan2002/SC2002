package controller.camp;

import entity.EnquiryList;
import entity.RepositoryCollection;
import entity.enquiry.Enquiry;
import entity.camp.Camp;
import entity.user.Student;
import entity.user.User;

public class CampEnquiryController {

    public static boolean createEnquiry(Enquiry enquiry) {
        RepositoryCollection.getEnquiryRepository().insert(enquiry);
        return true;
    }

    public static Enquiry getEnquiry(String enquiryID) {
        EnquiryList filteredEnquiries = RepositoryCollection.getEnquiryRepository().getAll().filterByID(enquiryID);
        return filteredEnquiries.size() == 0 ? null : filteredEnquiries.get(0);
    }

    public static EnquiryList getEnquiries() {
        return RepositoryCollection.getEnquiryRepository().getAll();
    }

    public static EnquiryList getEnquiries(Camp camp) {
        return RepositoryCollection.getEnquiryRepository().getAll().filterByCamp(camp);
    }

    public static EnquiryList getEnquiries(User user) {
        return RepositoryCollection.getEnquiryRepository().getAll().filterBySender(user);
    }

    public static boolean updateEnquiry(Enquiry newEnquiry) {
        return RepositoryCollection.getEnquiryRepository().update(newEnquiry);
    }

    public static boolean deleteEnquiry(Enquiry enquiry) {
        return RepositoryCollection.getEnquiryRepository().remove(enquiry);
    }

    public static boolean answerEnquiry(Enquiry enquiry, User answeringUser, String answer) {
        enquiry.setAnswer(answer, answeringUser);
        if (answeringUser instanceof Student answeringStudent) {
            answeringStudent.addPoints(1);
        }
        return RepositoryCollection.getEnquiryRepository().update(enquiry);
    }
}
