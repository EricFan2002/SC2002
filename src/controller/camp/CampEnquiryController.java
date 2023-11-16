package controller.camp;

import entity.EnquiryList;
import entity.RepositoryCollection;
import entity.enquiry.Enquiry;
import entity.camp.Camp;
import entity.user.Student;
import entity.user.User;

import java.util.List;

public class CampEnquiryController {

    public boolean createEnquiry(Enquiry enquiry) {
        RepositoryCollection.getEnquiryRepository().insert(enquiry);
        return true;
    }

    public Enquiry getEnquiry(String enquiryID) {
        EnquiryList filteredEnquiries = RepositoryCollection.getEnquiryRepository().getAll().filterByID(enquiryID);
        return filteredEnquiries.size() == 0 ? null : filteredEnquiries.get(0);
    }

    public EnquiryList getEnquiriesByCamp(Camp camp) {
        return RepositoryCollection.getEnquiryRepository().getAll().filterByCamp(camp);
    }

    public EnquiryList getEnquiriesByUser(User user) {
        return RepositoryCollection.getEnquiryRepository().getAll().filterBySender(user);
    }

    public boolean updateEnquiry(Enquiry oldEnquiry, Enquiry newEnquiry) {
        return RepositoryCollection.getEnquiryRepository().update(newEnquiry);
    }

    public boolean deleteEnquiry(Enquiry enquiry) {
        return RepositoryCollection.getEnquiryRepository().remove(enquiry);
    }

    public boolean answerEnquiry(Enquiry enquiry, Student answeringUser, String answer) {
        enquiry.setAnswer(answer, answeringUser);
        return RepositoryCollection.getEnquiryRepository().update(enquiry);
    }
}
