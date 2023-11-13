//To delete as this is the old code



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import entity.user.Staff;
import entity.user.Student;

// This class is used to filter the list of camps based on the visibility of the camp to show the students
public class CampList implements ICampListFilter {
    private List<Camp> camps;

    public CampList() {
        this.camps = new ArrayList<>();
    }

    public void addCamp(Camp camp) {
        camps.add(camp);
    }

    // seen by students
    public CampList filterForStudents(boolean isVisible, String group) {
        CampList filteredList = new CampList();
        Date currentDate = new Date();

        for (Camp camp : this.camps) {
            // Here, we assume the visibility is determined by whether the camp is still
            // open for registration.
            if (camp.getRegistrationClosingDate().after(currentDate) && isVisible && camp.getGroup().equals(group)) { // might
                                                                                                                      // need
                                                                                                                      // to
                                                                                                                      // change
                                                                                                                      // to
                                                                                                                      // account
                                                                                                                      // for
                                                                                                                      // all
                                                                                                                      // of
                                                                                                                      // NTU
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }

    // student can see camps already registered for and his roles

    // cannot register for full camps but still can see them
    // cannot register for the same camp again if withdrawn, can still see the camp
    // but give some message like "you cannot register for this camp again"

    // seen by all
    @Override
    public CampList filterByGroup(String group) {
        CampList filteredList = new CampList();

        for (Camp camp : this.camps) {
            if (camp.getGroup().equals(group)) {
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }

    @Override
    public CampList filterByLocation(String location) {
        CampList filteredList = new CampList();

        for (Camp camp : this.camps) {
            if (camp.getLocation().equals(location)) {
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }

    public CampList filterByDate(Date startDate, Date endDate) {
        CampList filteredList = new CampList();

        for (Camp camp : this.camps) {
            if (camp.getStartDate().after(startDate) && camp.getEndDate().before(endDate)) {
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }

    @Override
    public CampList filterByRegistrationClosingDate(Date registrationClosingDate) {
        CampList filteredList = new CampList();

        for (Camp camp : this.camps) {
            if (camp.getRegistrationClosingDate().equals(registrationClosingDate)) {
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }

    // see created camps by you
    @Override
    public CampList filterByStaff(Staff staff) {
        CampList filteredList = new CampList();

        for (Camp camp : this.camps) {
            if (camp.getStaffInCharge().equals(staff)) {
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }

    // not sure of its use
    @Override
    public CampList filterByAttendees(Set<Student> attendees) {
        CampList filteredList = new CampList();

        for (Camp camp : this.camps) {
            if (camp.getAttendees().equals(attendees)) {
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }

    @Override
    public CampList filterByCommittees(Set<Student> committees) {
        CampList filteredList = new CampList();

        for (Camp camp : this.camps) {
            if (camp.getCommittees().equals(committees)) {
                filteredList.addCamp(camp);
            }
        }
        return filteredList;
    }
}
