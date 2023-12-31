package app.entity.camp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import app.entity.RepositoryList;
import app.entity.interfaces.IFilterableByAttendee;
import app.entity.interfaces.IFilterableByCampCommittee;
import app.entity.interfaces.IFilterableByDateRange;
import app.entity.interfaces.IFilterableByID;
import app.entity.interfaces.IFilterableBySchool;
import app.entity.interfaces.IFilterableByStudent;
import app.entity.interfaces.IFilterableByVisibility;
import app.entity.interfaces.ISortableByEndDate;
import app.entity.interfaces.ISortableByID;
import app.entity.interfaces.ISortableByLocation;
import app.entity.interfaces.ISortableByName;
import app.entity.interfaces.ISortableByRegistrationCloseDate;
import app.entity.interfaces.ISortableByStartingDate;
import app.entity.user.Staff;
import app.entity.user.Student;

/**
 * Represents a list of camps, providing functionality for filtering and sorting
 * camps based on various criteria.
 * This class extends RepositoryList and implements several interfaces for
 * filtering and sorting.
 */
public class CampList extends RepositoryList<Camp> implements IFilterableByID<Camp>, IFilterableByDateRange<Camp>,
        IFilterableBySchool<Camp>, IFilterableByVisibility<Camp>, ISortableByEndDate<Camp>, ISortableByID<Camp>,
        ISortableByLocation<Camp>, ISortableByName<Camp>, ISortableByRegistrationCloseDate<Camp>,
        ISortableByStartingDate<Camp>, IFilterableByAttendee<Camp>, IFilterableByCampCommittee<Camp>,
        IFilterableByStudent<Camp> {
    /**
     * Constructs a new CampList with the specified list of camps.
     *
     * @param all A list of Camp objects to be managed.
     */
    public CampList(List<Camp> all) {
        super(all);
    }

    /**
     * Constructs an empty CampList.
     */
    public CampList() {
        super();
    }

    /**
     * Filters the list of camps by the specified ID.
     *
     * @param id The ID to filter by.
     * @return A new CampList containing camps that match the given ID.
     */
    public CampList filterByID(String id) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getID().equals(id)) {
                result.add(camp);
            }
        }
        return result;
    }

    /**
     * Filters the list of camps by the specified school.
     *
     * @param school The school to filter by.
     * @return A new CampList containing camps associated with the given school.
     */
    public CampList filterBySchool(String school) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getSchool().equals(school)) {
                result.add(camp);
            }
        }
        return result;
    }

    /**
     * Filters the list of camps by their visibility status.
     *
     * @param visible The visibility status to filter by.
     * @return A new CampList containing camps with the specified visibility status.
     */
    public CampList filterByVisibility(boolean visible) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.isVisible() == visible) {
                result.add(camp);
            }
        }
        return result;
    }

    /**
     * Filters the list of camps within a specific date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A new CampList containing camps within the specified date range.
     */
    public CampList filterByDateRange(Date startDate, Date endDate) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getStartDate().compareTo(startDate) >= 0 && camp.getEndDate().compareTo(endDate) <= 0) {
                result.add(camp);
            }
        }
        return result;
    }

    /**
     * Filters the list of camps by registration date.
     *
     * @param currentDate The date to compare registration dates against.
     * @return A new CampList containing camps whose registration date is on or
     *         after the specified date.
     */
    public CampList filterByRegistrationDate(Date currentDate) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getCloseRegistrationDate().compareTo(currentDate) >= 0) {
                result.add(camp);
            }
        }
        return result;
    }

    /**
     * Filters the list of camps by a specific student attendee.
     *
     * @param student The student to filter by.
     * @return A new CampList containing camps that the given student is attending.
     */
    public CampList filterByStudent(Student student) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getAttendees().contains(student)) {
                result.add(camp);
            }
        }
        return result;
    }

    /**
     * Filters the list of camps by a specific staff member in charge.
     *
     * @param staff The staff member to filter
     *              by.ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffsssssssssssssssssssssssssssssssssssssssssssss
     * @return A new CampList containing camps managed by the given staff member.
     */
    public CampList filterByStaff(Staff staff) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getStaffInCharge().equals(staff)) {
                result.add(camp);
            }
        }
        return result;
    }

    public CampList filterByCampCommittee(Student student) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getCommittees().contains(student)) {
                result.add(camp);
            }
        }
        return result;
    }

    public CampList filterByAttendee(Student attendee) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getAttendees().contains(attendee)) {
                result.add(camp);
            }
        }
        return result;
    }

    /**
     * Sorts the list of camps by their names.
     *
     * @return A new CampList sorted by camp names.
     */
    public CampList sortByName() {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            result.add(camp);
        }
        Comparator<Camp> comparator = new Comparator<Camp>() {
            @Override
            public int compare(Camp o1, Camp o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        result.all.sort(comparator);

        return result;
    }

    /**
     * Sorts the list of camps by their registration close dates.
     *
     * @return A new CampList sorted by camp registration close dates.
     */
    public CampList sortByRegistrationCloseDate() {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            result.add(camp);
        }
        Comparator<Camp> comparator = new Comparator<Camp>() {
            @Override
            public int compare(Camp o1, Camp o2) {
                return o1.getCloseRegistrationDate().compareTo(o2.getCloseRegistrationDate());
            }
        };
        result.all.sort(comparator);

        return result;
    }

    /**
     * Sorts the list of camps by their starting dates.
     *
     * @return A new CampList sorted by camp starting dates.
     */
    public CampList sortByStartingDate() {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            result.add(camp);
        }
        Comparator<Camp> comparator = new Comparator<Camp>() {
            @Override
            public int compare(Camp o1, Camp o2) {
                return o1.getStartDate().compareTo(o2.getStartDate());
            }
        };
        result.all.sort(comparator);

        return result;
    }

    /**
     * Sorts the list of camps by their end dates.
     *
     * @return A new CampList sorted by camp end dates.
     */
    public CampList sortByEndDate() {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            result.add(camp);
        }
        Comparator<Camp> comparator = new Comparator<Camp>() {
            @Override
            public int compare(Camp o1, Camp o2) {
                return o1.getEndDate().compareTo(o2.getEndDate());
            }
        };
        result.all.sort(comparator);

        return result;
    }

    /**
     * Sorts the list of camps by their locations.
     *
     * @return A new CampList sorted by camp locations.
     */
    public CampList sortByLocation() {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            result.add(camp);
        }
        Comparator<Camp> comparator = new Comparator<Camp>() {
            @Override
            public int compare(Camp o1, Camp o2) {
                return o1.getLocation().compareTo(o2.getLocation());
            }
        };
        result.all.sort(comparator);

        return result;
    }

    /**
     * Sorts the list of camps by their IDs.
     *
     * @return A new CampList sorted by camp IDs.
     */
    public CampList sortByID() {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            result.add(camp);
        }
        Comparator<Camp> comparator = new Comparator<Camp>() {
            @Override
            public int compare(Camp o1, Camp o2) {
                return o1.getID().compareTo(o2.getID());
            }
        };
        result.all.sort(comparator);

        return result;
    }

    /**
     * Returns all camps in the list.
     *
     * @return This CampList.
     */
    public CampList getAll() {
        return this;
    }

    /**
     * Converts the CampList to an array of Camp objects.
     *
     * @return An array of Camp objects.
     */
    public Camp[] toArray() {
        return super.all.toArray(new Camp[super.all.size()]);
    }

    public ArrayList<ArrayList<String>> serialize() {

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        super.all.forEach(campRaw -> {
            Camp camp = (Camp) campRaw;
            // id, name, desc, visibility (0/1), startDate, endDate, closeRegDate, school,
            ArrayList<String> record = new ArrayList<String>();
            String visibility = (camp.isVisible()) ? "1" : "0";
            String startDate = Long.toString(camp.getStartDate().getTime());
            String endDate = Long.toString(camp.getEndDate().getTime());
            String closeRegDate = Long.toString(camp.getCloseRegistrationDate().getTime());

            String staffID = camp.getStaffInCharge().getID();

            List<String> attendeeIDsTemp = new ArrayList<String>();
            for (Student attendee : camp.getAttendees()) {
                attendeeIDsTemp.add(attendee.getID());
            }
            String attendeeIDs = String.join(";", attendeeIDsTemp);

            List<String> committeeIDsTemp = new ArrayList<String>();
            for (Student committee : camp.getCommittees()) {
                committeeIDsTemp.add(committee.getID());
            }
            String committeeIDs = String.join(";", committeeIDsTemp);

            List<String> registeredIDsTemp = new ArrayList<String>();
            for (Student registered : camp.getRegisteredStudents()) {
                registeredIDsTemp.add(registered.getID());
            }
            String registeredIDs = String.join(";", registeredIDsTemp);

            record.add(camp.getID());
            record.add(camp.getName());
            record.add(camp.getDescription());
            record.add(visibility);
            record.add(startDate);
            record.add(endDate);
            record.add(closeRegDate);
            record.add(camp.getSchool());
            record.add(camp.getLocation());
            record.add(staffID);
            record.add(attendeeIDs);
            record.add(committeeIDs);
            record.add(registeredIDs);
            record.add(Integer.toString(camp.getTotalSlots()));
            record.add(Integer.toString(camp.getTotalCommitteeSlots()));

            result.add(record);

        });

        return result;
    }

}
