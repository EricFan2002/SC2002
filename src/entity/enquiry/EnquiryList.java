package entity.enquiry;

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
 * Represents a list of enquiries, providing functionalities for filtering enquiries based on various criteria.
 * This class extends RepositoryList and implements several interfaces for filtering.
 */
public class EnquiryList extends RepositoryList<Enquiry> implements IFilterableByID<Enquiry>,
        IFilterableByCamp<Enquiry>, IFilterableByStatus<Enquiry, Boolean>, IFilterableBySender<Enquiry>,
        IFilterableByAnsweredBy<Enquiry> {
    /**
     * Constructs a new EnquiryList with the specified list of enquiries.
     *
     * @param all A list of Enquiry objects to be managed.
     */
    public EnquiryList(List<Enquiry> all) {
        super(all);
    }

    /**
     * Constructs an empty EnquiryList.
     */
    public EnquiryList() {
        super();
    }

    /**
     * Filters the list of enquiries by a specific ID.
     *
     * @param id The ID to filter by.
     * @return A new EnquiryList containing enquiries that match the given ID.
     */
    public EnquiryList filterByID(String id) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if (enquiry.getID().equals(id)) {
                result.add(enquiry);
            }
        }
        return result;
    }

    /**
     * Filters the list of enquiries by a specific camp.
     *
     * @param camp The camp to filter by.
     * @return A new EnquiryList containing enquiries that match the given camp.
     */
    public EnquiryList filterByCamp(Camp camp) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if (enquiry.getCamp().equals(camp)) {
                result.add(enquiry);
            }
        }
        return result;
    }

    /**
     * Filters the list of enquiries by a specific status.
     *
     * @param status The status to filter by.
     * @return A new EnquiryList containing enquiries that match the given status.
     */
    public EnquiryList filterByStatus(Boolean status) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if ((enquiry.getAnsweredBy() == null) != status) {
                result.add(enquiry);
            }
        }
        return result;
    }

    /**
     * Filters the list of enquiries by a specific sender.
     *
     * @param sender The sender to filter by.
     * @return A new EnquiryList containing enquiries that match the given sender.
     */
    public EnquiryList filterBySender(User sender) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if (enquiry.getSender().equals(sender)) {
                result.add(enquiry);
            }
        }
        return result;
    }

    /**
     * Filters the list of enquiries by a specific user who answered the enquiry.
     *
     * @param answeredBy The user who answered the enquiry to filter by.
     * @return A new EnquiryList containing enquiries that match the given user who answered the enquiry.
     */
    public EnquiryList filterByAnsweredBy(User answeredBy) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if (enquiry.getAnsweredBy() != null && enquiry.getAnsweredBy().equals(answeredBy)) {
                result.add(enquiry);
            }
        }
        return result;
    }

    /**
     * Converts the EnquiryList to an array of Enquiry objects.
     *
     * @return An array of Enquiry objects.
     */
    public Enquiry[] toArray() {
        return super.all.toArray(new Enquiry[super.all.size()]);
    }

}
