package app.controller.camp;

import app.entity.camp.CampList;
import app.entity.RepositoryCollection;
import app.entity.suggestion.SuggestionList;
import app.entity.camp.Camp;
import app.entity.camp.CampDetails;
import app.entity.suggestion.Suggestion;
import app.entity.suggestion.SuggestionStatus;
import app.entity.user.Staff;
import app.entity.user.Student;

/**
 * The {@code CampSuggestionController} class manages the operations related to
 * suggestions for camp improvements.
 * It provides functionalities for creating, updating, deleting, and approving
 * suggestions made by students and staff.
 * Additionally, it offers methods to retrieve suggestions based on specific
 * camps or senders.
 *
 * <p>
 * Key functionalities include:
 * <ul>
 * <li>Creating new suggestions and rewarding students with points.
 * <li>Updating existing suggestions.
 * <li>Deleting suggestions.
 * <li>Approving suggestions and applying their changes to camps.
 * <li>Retrieving suggestions filtered by camps or senders.
 * </ul>
 *
 * @see app.entity.camp.Camp
 * @see app.entity.suggestion.Suggestion
 * @see app.entity.user.Student
 * @see app.entity.user.Staff
 */
public class CampSuggestionController {

    /**
     * Private constructor to prevent instantiation.
     */
    private CampSuggestionController() {
    }

    /**
     * Creates a new suggestion for a camp and adds it to the suggestion repository.
     * The method awards points to the student who made the suggestion.
     *
     * @param sender       The {@link Student} who is sending the suggestion.
     * @param suggsetion   The {@link CampDetails} containing the suggestion
     *                     details.
     * @param originalCamp The original {@link Camp} for which the suggestion is
     *                     made.
     * @return boolean True if the suggestion is successfully created and inserted.
     */
    public static boolean createSuggestion(Student sender, CampDetails suggsetion, Camp originalCamp) {
        Suggestion suggestion = new Suggestion(sender, suggsetion, originalCamp);
        RepositoryCollection.getSuggestionRepository().add(suggestion);
        sender.addPoints(1);
        sender.addSuggestion(suggestion);
        originalCamp.addSuggestion(suggestion);
        return true;
    }

    /**
     * Updates an existing suggestion in the suggestion repository.
     *
     * @param newSuggestion The updated {@link Suggestion} to be stored.
     * @return boolean True if the suggestion is successfully updated.
     */
    public static boolean updateSuggestion(Suggestion newSuggestion) {
        // deep copy
        Suggestion oldSuggestion = RepositoryCollection.getSuggestionRepository()
                .filterByID(newSuggestion.getID()).get(0); // get the old suggestion
        if (oldSuggestion.getStatus() != SuggestionStatus.PENDING)
            return false;
        oldSuggestion.setSuggestion(newSuggestion.getSuggestion());
        return true;
    }

    /**
     * Deletes a suggestion from the suggestion repository.
     *
     * @param suggestion The {@link Suggestion} to be removed.
     * @return boolean True if the suggestion is successfully deleted.
     */
    public static boolean deleteSuggestion(Suggestion suggestion) {
        suggestion.getSender().removeSuggestion(suggestion);
        suggestion.getOriginalCamp().removeSuggestion(suggestion);
        RepositoryCollection.getSuggestionRepository().remove(suggestion);
        return true;
    }

    /**
     * Approves or rejects a suggestion. If approved, the suggestion's changes are
     * applied to the respective camp.
     * Points are awarded to the student sender if the suggestion is approved.
     *
     * @param suggestion The {@link Suggestion} to be approved or rejected.
     * @param staff      The {@link Staff} member reviewing the suggestion.
     * @param isApproved True if the suggestion is approved, false otherwise.
     * @return boolean True if the operation is successful.
     */
    public static boolean approveSuggestion(Suggestion suggestion, Staff staff, boolean isApproved) {
        suggestion.setReviewedBy(staff);
        if (isApproved) {
            Student sender = suggestion.getSender();
            sender.addPoints(1);
            suggestion.setStatus(SuggestionStatus.APPROVED);
            String editingID = suggestion.getOriginalCamp().getID();
            CampList editingCamps = RepositoryCollection.getCampRepository().filterByID(editingID);
            if (editingCamps.size() == 0)
                return false;
            Camp editingCamp = editingCamps.get(0);
            if (suggestion.getSuggestion().getDescription() != null)
                editingCamp.setDescription(suggestion.getSuggestion().getDescription());
            if (suggestion.getSuggestion().getEndDate() != null)
                editingCamp.setEndDate(suggestion.getSuggestion().getEndDate());
            if (suggestion.getSuggestion().getStartDate() != null)
                editingCamp.setStartDate(suggestion.getSuggestion().getStartDate());
            if (suggestion.getSuggestion().getSchool() != null)
                editingCamp.setSchool(suggestion.getSuggestion().getSchool());
            if (suggestion.getSuggestion().getCloseRegistrationDate() != null)
                editingCamp.setCloseRegistrationDate(suggestion.getSuggestion().getCloseRegistrationDate());
            if (suggestion.getSuggestion().getName() != null)
                editingCamp.setName(suggestion.getSuggestion().getName());
            if (suggestion.getSuggestion().getLocation() != null)
                editingCamp.setLocation(suggestion.getSuggestion().getLocation());
            sender.addPoints(1);
        } else {
            suggestion.setStatus(SuggestionStatus.REJECTED);
        }
        return true;
    }

    /**
     * Retrieves a list of suggestions for a specific camp.
     *
     * @param camp The {@link Camp} for which suggestions are being retrieved.
     * @return SuggestionList A list of suggestions related to the specified camp.
     */
    public static SuggestionList getSuggestions(Camp camp) {
        return RepositoryCollection.getSuggestionRepository().filterByCamp(camp);
    }

    /**
     * Retrieves a list of suggestions made by a specific staff member.
     *
     * @param staff The {@link Staff} whose suggestions are being retrieved.
     * @return SuggestionList A list of suggestions made by the specified staff
     *         member.
     */
    public static SuggestionList getSuggestions(Staff staff) {
        return RepositoryCollection.getSuggestionRepository().filterBySender(staff);
    }

    /**
     * Retrieves a list of suggestions made by a specific student.
     *
     * @param student The {@link Student} whose suggestions are being retrieved.
     * @return SuggestionList A list of suggestions made by the specified student.
     */
    public static SuggestionList getSuggestions(Student student) {
        return RepositoryCollection.getSuggestionRepository().filterBySender(student);
    }
}
