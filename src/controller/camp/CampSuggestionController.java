package controller.camp;

import entity.CampList;
import entity.RepositoryCollection;
import entity.SuggestionList;
import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.suggestion.Suggestion;
import entity.suggestion.SuggestionStatus;
import entity.user.Staff;
import entity.user.Student;

public class CampSuggestionController {

    public boolean createSuggestion(Student sender, CampDetails suggsetion, Camp originalCamp) {
        Suggestion suggestion = new Suggestion(sender, suggsetion, originalCamp);
        RepositoryCollection.getSuggestionRepository().insert(suggestion);
        return true;
    }

    public boolean updateSuggestion(Suggestion newSuggestion) {
        return RepositoryCollection.getSuggestionRepository().update(newSuggestion);
    }

    public boolean deleteSuggestion(Suggestion suggestion) {
        return RepositoryCollection.getSuggestionRepository().remove(suggestion);
    }

    public boolean approveSuggestion(Suggestion suggestion, Staff staff, boolean isApproved) {
        suggestion.setReviewedBy(staff);
        if (isApproved) {
            suggestion.setStatus(SuggestionStatus.APPROVED);
            String editingID = suggestion.getID();
            CampList editingCamps = RepositoryCollection.getCampRepository().getAll().filterByID(editingID);
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
        } else {
            suggestion.setStatus(SuggestionStatus.REJECTED);
        }
        return true;
    }

    public SuggestionList getSuggestions(Camp camp) {
        return RepositoryCollection.getSuggestionRepository().getAll().filterByCamp(camp);
    }

    public SuggestionList getSuggestions(Staff staff) {
        return RepositoryCollection.getSuggestionRepository().getAll().filterBySender(staff);
    }

    public SuggestionList getSuggestions(Student student) {
        return RepositoryCollection.getSuggestionRepository().getAll().filterBySender(student);
    }
}
