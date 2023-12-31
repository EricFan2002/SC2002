package app.controller.camp;

import app.entity.enquiry.EnquiryList;
import app.entity.RepositoryCollection;
import app.entity.enquiry.Enquiry;
import app.entity.camp.Camp;
import app.entity.user.Student;
import app.entity.user.User;

/**
 * The {@code CampEnquiryController} class manages the operations related to
 * enquiries within a camp context.
 * It provides functionalities to create, retrieve, update, and delete
 * enquiries, along with specific methods
 * to handle enquiries by camp or user. It also includes a feature to answer an
 * enquiry and award points to
 * students for their participation.
 * 
 * <p>
 * Methods include:
 * <ul>
 * <li>Creating new enquiries.
 * <li>Retrieving enquiries by ID, camp, or user.
 * <li>Updating existing enquiries.
 * <li>Deleting enquiries.
 * <li>Answering enquiries with an optional reward system for student users.
 * </ul>
 * 
 * <p>
 * This app.controller interacts with the {@code RepositoryCollection} to perform
 * data operations,
 * ensuring a separation of concerns between the data layer and business logic.
 * 
 * @see app.entity.enquiry.Enquiry
 * @see app.entity.camp.Camp
 * @see app.entity.user.User
 */
public class CampEnquiryController {
    /**
     * Private constructor to prevent instantiation.
     */
    private CampEnquiryController() {
    }

    /*
     * Creates a new enquiry by inserting it into the enquiry repository.
     * This method always returns true after the insertion operation.
     *
     * @param enquiry The {@link Enquiry} object to be inserted.
     * 
     * @return boolean True after successful insertion.
     */
    public static boolean createEnquiry(Enquiry enquiry) {
        RepositoryCollection.getEnquiryRepository().add(enquiry);
        enquiry.getSender().addEnquiry(enquiry);
        enquiry.getCamp().addEnquiry(enquiry);
        return true;
    }

    /**
     * Retrieves an enquiry based on its unique ID. If no enquiry is found with the
     * given ID, this method returns null.
     *
     * @param enquiryID The unique identifier of the enquiry.
     * 
     * @return Enquiry The enquiry matching the given ID, or null if none found.
     */
    public static Enquiry getEnquiry(String enquiryID) {
        EnquiryList filteredEnquiries = RepositoryCollection.getEnquiryRepository().filterByID(enquiryID);
        return filteredEnquiries.size() == 0 ? null : filteredEnquiries.get(0);
    }

    /**
     * Retrieves all enquiries from the enquiry repository.
     *
     * @return EnquiryList A list of all enquiries.
     */
    public static EnquiryList getEnquiries() {
        return RepositoryCollection.getEnquiryRepository();
    }

    /**
     * Retrieves all enquiries related to a specific camp.
     *
     * @param camp The {@link Camp} for which enquiries are to be retrieved.
     * 
     * @return EnquiryList A list of enquiries related to the specified camp.
     */
    public static EnquiryList getEnquiries(Camp camp) {
        return new EnquiryList(camp.getEnquiryList());
    }

    /**
     * Retrieves all enquiries made by a specific user.
     *
     * @param user The {@link User} who made the enquiries.
     * 
     * @return EnquiryList A list of enquiries made by the specified user.
     */
    public static EnquiryList getEnquiries(Student user) {
        return new EnquiryList(user.getEnquiryList());
    }

    /**
     * Updates an existing enquiry in the repository.
     *
     * @param newEnquiry The updated {@link Enquiry} object.
     * 
     * @return boolean True if the update operation is successful.
     */
    public static boolean updateEnquiry(Enquiry newEnquiry) {
        Enquiry oldEnquiry = getEnquiry(newEnquiry.getID());
        if (oldEnquiry.getAnsweredBy() != null) {
            return false;
        }
        oldEnquiry.setAnswer(newEnquiry.getAnswer(), newEnquiry.getAnsweredBy());
        oldEnquiry.setCamp(newEnquiry.getCamp());
        oldEnquiry.setSender(newEnquiry.getSender());
        oldEnquiry.setQuestion(newEnquiry.getQuestion());

        return true;
    }

    /**
     * Deletes an enquiry from the repository.
     *
     * @param enquiry The {@link Enquiry} to be deleted.
     * 
     * @return boolean True if the deletion is successful.
     */
    public static boolean deleteEnquiry(Enquiry enquiry) {
        enquiry.getSender().removeEnquiry(enquiry);
        enquiry.getCamp().removeEnquiry(enquiry);
        RepositoryCollection.getEnquiryRepository().remove(enquiry);
        return true;
    }

    /**
     * Answers an enquiry and, if the answering user is a student, awards them
     * points.
     * The method updates the enquiry with the provided answer and user information.
     *
     * @param enquiry       The {@link Enquiry} to be answered.
     * 
     * @param answeringUser The {@link User} who is answering the enquiry.
     * 
     * @param answer        The answer to the enquiry.
     * 
     * @return boolean True if the update operation after answering is successful.
     */
    public static boolean answerEnquiry(Enquiry enquiry, User answeringUser, String answer) {
        enquiry.setAnswer(answer, answeringUser);
        if (answeringUser instanceof Student answeringStudent) {
            answeringStudent.addPoints(1);
        }
        return RepositoryCollection.getEnquiryRepository().update(enquiry);
    }
}