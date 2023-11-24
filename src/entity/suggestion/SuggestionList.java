package entity.suggestion;

import java.util.ArrayList;
import java.util.List;

import entity.RepositoryList;
import entity.camp.Camp;
import entity.interfaces.IFilterableByAnsweredBy;
import entity.interfaces.IFilterableByCamp;
import entity.interfaces.IFilterableByID;
import entity.interfaces.IFilterableByStatus;
import entity.interfaces.IFilterableBySender;
import entity.user.User;

/**
 * Represents a list of suggestions, providing functionalities for filtering
 * suggestions based on various criteria.
 * This class extends RepositoryList and implements several interfaces for
 * filtering.
 */
public class SuggestionList extends RepositoryList<Suggestion> implements IFilterableByID<Suggestion>,
        IFilterableByCamp<Suggestion>, IFilterableByStatus<Suggestion, SuggestionStatus>,
        IFilterableBySender<Suggestion>,
        IFilterableByAnsweredBy<Suggestion> {
    /**
     * Constructs a new SuggestionList object.
     *
     * @param all The list of suggestions to be stored in this object.
     */
    public SuggestionList(List<Suggestion> all) {
        super(all);
    }

    /**
     * Constructs a new SuggestionList object.
     */
    public SuggestionList() {
        super();
    }

    /**
     * Filters the list of suggestions by the provided ID.
     *
     * @param id The ID to filter by.
     * @return A new SuggestionList containing suggestions with the specified ID.
     */
    public SuggestionList filterByID(String id) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if (suggestion.getID().equals(id)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    /**
     * Filters the list of suggestions by the provided camp.
     *
     * @param camp The camp to filter by.
     * @return A new SuggestionList containing suggestions related to the specified
     *         camp.
     */
    public SuggestionList filterByCamp(Camp camp) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if (suggestion.getOriginalCamp().equals(camp)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    /**
     * Filters the list of suggestions by the provided status.
     *
     * @param status The status to filter by.
     * @return A new SuggestionList containing suggestions with the specified
     *         status.
     */
    public SuggestionList filterByStatus(SuggestionStatus status) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if ((suggestion.getStatus() != null) && suggestion.getStatus().equals(status)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    /**
     * Filters the list of suggestions by the provided sender.
     *
     * @param sender The sender to filter by.
     * @return A new SuggestionList containing suggestions sent by the specified
     *         user.
     */
    public SuggestionList filterBySender(User sender) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if (suggestion.getSender().equals(sender)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    /**
     * Filters the list of suggestions by the provided staff.
     *
     * @param staff The staff to filter by.
     * @return A new SuggestionList containing suggestions answered by the specified
     *         staff.
     */
    public SuggestionList filterByAnsweredBy(User staff) {
        SuggestionList result = new SuggestionList();
        for (Suggestion suggestion : super.all) {
            if ((suggestion.getReviewedBy() != null) && suggestion.getReviewedBy().equals(staff)) {
                result.add(suggestion);
            }
        }
        return result;
    }

    /**
     * Converts the SuggestionList to an array of Suggestion objects.
     *
     * @return An array of Suggestion objects.
     */
    public Suggestion[] toArray() {
        return super.all.toArray(new Suggestion[super.all.size()]);
    }

    public ArrayList<ArrayList<String>> serialize() {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

        super.all.forEach(suggestionRaw -> {
            ArrayList<String> record = new ArrayList<String>();
            Suggestion camp = (Suggestion) suggestionRaw;
            // id, senderID, campSuggestionString, reviewedByID, status 0/1/2
            // id;name;desc;visibility
            // 0/1;startDate;endDate;closeRegDate;school;location

            String id = camp.getID();
            String senderID = camp.getSender().getID();

            // #region campSuggestionString

            String idString = (camp.getSuggestion().getID() == null) ? "" : camp.getSuggestion().getID();
            String nameString = (camp.getSuggestion().getName() == null) ? "" : camp.getSuggestion().getName();
            String descString = (camp.getSuggestion().getDescription() == null) ? ""
                    : camp.getSuggestion().getDescription();

            String visibilityString = camp.getSuggestion().isVisible() ? "1" : "0";

            String startDateString = (camp.getSuggestion().getStartDate() == null) ? ""
                    : Long.toString(camp.getSuggestion().getStartDate().getTime());

            String endDateString = (camp.getSuggestion().getEndDate() == null) ? ""
                    : Long.toString(camp.getSuggestion().getEndDate().getTime());

            String closeRegDateString = (camp.getSuggestion().getCloseRegistrationDate() == null) ? ""
                    : Long.toString(camp.getSuggestion().getCloseRegistrationDate().getTime());

            String schoolString = (camp.getSuggestion().getSchool() == null) ? ""
                    : camp.getSuggestion().getSchool();

            String locationString = (camp.getSuggestion().getLocation() == null) ? ""
                    : camp.getSuggestion().getLocation();

            String totalSlotsString = (camp.getSuggestion().getTotalSlots() == null) ? ""
                    : Integer.toString(camp.getSuggestion().getTotalSlots());

            String[] campSuggestionArray = { idString, nameString,
                    descString, visibilityString, startDateString, endDateString,
                    closeRegDateString, schoolString, locationString, totalSlotsString };

            String campSuggestionString = String.join(";", campSuggestionArray);

            // #endregion campSuggestionString

            String reviewedByID = (camp.getReviewedBy() == null) ? "" : camp.getReviewedBy().getID();

            String statusString = camp.getStatus() == SuggestionStatus.PENDING ? "0"
                    : camp.getStatus() == SuggestionStatus.APPROVED ? "1" : "2";

            record.add(id);
            record.add(senderID);
            record.add(campSuggestionString);
            record.add(reviewedByID);
            record.add(statusString);

            result.add(record);

        });
        return result;
    }
}
