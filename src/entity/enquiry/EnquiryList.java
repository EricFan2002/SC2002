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

public class EnquiryList extends RepositoryList<Enquiry> implements IFilterableByID<Enquiry>,
        IFilterableByCamp<Enquiry>, IFilterableByStatus<Enquiry, Boolean>, IFilterableBySender<Enquiry>,
        IFilterableByAnsweredBy<Enquiry> {
    public EnquiryList(List<Enquiry> all) {
        super(all);
    }

    public EnquiryList() {
        super();
    }

    public EnquiryList filterByID(String id) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if (enquiry.getID().equals(id)) {
                result.add(enquiry);
            }
        }
        return result;
    }

    public EnquiryList filterByCamp(Camp camp) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if (enquiry.getCamp().equals(camp)) {
                result.add(enquiry);
            }
        }
        return result;
    }

    public EnquiryList filterByStatus(Boolean status) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if ((enquiry.getAnsweredBy() == null) != status) {
                result.add(enquiry);
            }
        }
        return result;
    }

    public EnquiryList filterBySender(User sender) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if (enquiry.getSender().equals(sender)) {
                result.add(enquiry);
            }
        }
        return result;
    }

    public EnquiryList filterByAnsweredBy(User answeredBy) {
        EnquiryList result = new EnquiryList();
        for (Enquiry enquiry : super.all) {
            if (enquiry.getAnsweredBy() != null && enquiry.getAnsweredBy().equals(answeredBy)) {
                result.add(enquiry);
            }
        }
        return result;
    }

    public Enquiry[] toArray() {
        return super.all.toArray(new Enquiry[super.all.size()]);
    }

}
