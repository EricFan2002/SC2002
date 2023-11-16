package entity.suggestion;

import java.util.List;

import entity.RepositoryList;
import entity.camp.Camp;
import entity.interfaces.IFilterableByAnsweredBy;
import entity.interfaces.IFilterableByCamp;
import entity.interfaces.IFilterableByID;
import entity.interfaces.IFilterableByStatus;
import entity.interfaces.IFilterableBySender;
import entity.user.User;

public class SuggestionList extends RepositoryList<Suggestion> implements IFilterableByID<Suggestion>,
        IFilterableByCamp<Suggestion>, IFilterableByStatus<Suggestion, SuggestionStatus>,
        IFilterableBySender<Suggestion>,
        IFilterableByAnsweredBy<Suggestion> {
    public SuggestionList(List<Suggestion> all) {
        super(all);
    }

    public SuggestionList() {
        super();
    }

    public SuggestionList filterByID(String id) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if (suggestion.getID().equals(id)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    public SuggestionList filterByCamp(Camp camp) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if (suggestion.getOriginalCamp().equals(camp)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    public SuggestionList filterByStatus(SuggestionStatus status) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if ((suggestion.getStatus() != null) && suggestion.getStatus().equals(status)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    public SuggestionList filterBySender(User sender) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if (suggestion.getSender().equals(sender)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    public SuggestionList filterByAnsweredBy(User staff) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if ((suggestion.getReviewedBy() != null) && suggestion.getReviewedBy().equals(staff)) {
                result.add(suggestion);
            }
        }
        return result;
    }

}
