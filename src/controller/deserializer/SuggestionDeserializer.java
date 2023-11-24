package controller.deserializer;

import java.util.ArrayList;
import java.util.Date;

import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.camp.CampList;
import entity.suggestion.Suggestion;
import entity.suggestion.SuggestionList;
import entity.suggestion.SuggestionStatus;
import entity.user.Staff;
import entity.user.Student;
import entity.user.UserList;

/**
 * The SuggestionDeserializer class contains methods to deserialize data into a
 * SuggestionList object.
 */
public class SuggestionDeserializer {

    /**
     * Private constructor to prevent instantiation.
     */
    private SuggestionDeserializer() {
    }

    /**
     * Deserializes the provided data into a SuggestionList object.
     *
     * @param data     The data to deserialize, represented as an ArrayList of
     *                 ArrayLists of Strings.
     * @param userList The UserList containing user information to associate with
     *                 suggestions.
     * @param campList The CampList containing camp information to associate with
     *                 suggestions.
     * @return A SuggestionList object populated with deserialized suggestion data.
     */

    public static SuggestionList deserialize(ArrayList<ArrayList<String>> data, UserList userList, CampList campList) {

        SuggestionList cur = new SuggestionList();

        data.forEach(record -> {
            // id, senderID, campSuggestionString, reviewedByID, status 0/1/2
            String id = record.get(0);
            String senderID = record.get(1);
            UserList senderTmp = userList.filterByID(senderID);
            Student sender = null;
            if (senderTmp.size() > 0 && senderTmp.get(0) instanceof Student) {
                sender = (Student) senderTmp.get(0);
            }

            String campSuggestionRaw = record.get(2);

            // id;name;desc;visibility
            // 0/1;startDate;endDate;closeRegDate;school;location

            String[] campSuggestionArr = campSuggestionRaw.split(";", -1);

            String campSuggestionID = campSuggestionArr[0];
            String name = campSuggestionArr[1] != "" ? campSuggestionArr[1] : null;
            String desc = campSuggestionArr[2] != "" ? campSuggestionArr[2] : null;
            String visibilityRaw = campSuggestionArr[3];
            Boolean visibility = campSuggestionArr[3] != "" ? (visibilityRaw == "0" ? false : true) : null;
            Date startDate = campSuggestionArr[4] != "" ? new Date(Long.parseLong(campSuggestionArr[4])) : null;
            Date endDate = campSuggestionArr[5] != "" ? new Date(Long.parseLong(campSuggestionArr[5])) : null;
            Date closeRegDate = campSuggestionArr[6] != "" ? new Date(Long.parseLong(campSuggestionArr[6])) : null;
            String school = campSuggestionArr[7] != "" ? campSuggestionArr[7] : null;
            String location = campSuggestionArr[8] != "" ? campSuggestionArr[8] : null;
            Integer totalSlots = campSuggestionArr[9] != "" ? Integer.parseInt(campSuggestionArr[9]) : null;

            CampDetails campDetails = new CampDetails(campSuggestionID, name, desc,
                    visibility, startDate, endDate, closeRegDate, school,
                    location, totalSlots);

            String reviewedByID = record.get(3);
            UserList reviewedByTmp = userList.filterByID(reviewedByID);
            Staff reviewedBy = null;
            if (reviewedByTmp.size() > 0) {
                reviewedBy = (Staff) reviewedByTmp.get(0);
            }

            String statusRaw = record.get(4);
            SuggestionStatus status = statusRaw.equals("0") ? SuggestionStatus.PENDING
                    : statusRaw.equals("1") ? SuggestionStatus.APPROVED : SuggestionStatus.REJECTED;

            Camp originalCamp = campList.filterByID(campSuggestionID).get(0);
            Suggestion suggestion = new Suggestion(id, sender, campDetails, originalCamp, reviewedBy, status);

            cur.add(suggestion);
        });

        // Creates references to other entity
        cur.forEach(suggestion -> {
            if (suggestion.getSender() != null) {
                suggestion.getSender().addSuggestion(suggestion);
            }

            if (suggestion.getOriginalCamp() != null) {
                suggestion.getOriginalCamp().addSuggestion(suggestion);
            }
        });

        return cur;
    }
}
