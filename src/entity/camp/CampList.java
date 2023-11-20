package entity.camp;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import entity.RepositoryList;
import entity.interfaces.IFilterableByDateRange;
import entity.interfaces.IFilterableByID;
import entity.interfaces.IFilterableBySchool;
import entity.interfaces.IFilterableByVisibility;
import entity.interfaces.ISortableByEndDate;
import entity.interfaces.ISortableByID;
import entity.interfaces.ISortableByLocation;
import entity.interfaces.ISortableByName;
import entity.interfaces.ISortableByRegistrationCloseDate;
import entity.interfaces.ISortableByStartingDate;
import entity.user.Staff;
import entity.user.Student;

public class CampList extends RepositoryList<Camp> implements IFilterableByID<Camp>, IFilterableByDateRange<Camp>,
        IFilterableBySchool<Camp>, IFilterableByVisibility<Camp>, ISortableByEndDate<Camp>, ISortableByID<Camp>,
        ISortableByLocation<Camp>, ISortableByName<Camp>, ISortableByRegistrationCloseDate<Camp>,
        ISortableByStartingDate<Camp> {
    public CampList(List<Camp> all) {
        super(all);
    }

    public CampList() {
        super();
    }

    public CampList filterByID(String id) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getID().equals(id)) {
                result.add(camp);
            }
        }
        return result;
    }

    public CampList filterBySchool(String school) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getSchool().equals(school)) {
                result.add(camp);
            }
        }
        return result;
    }

    public CampList filterByVisibility(boolean visible) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.isVisible() == visible) {
                result.add(camp);
            }
        }
        return result;
    }

    public CampList filterByDateRange(Date startDate, Date endDate) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getStartDate().compareTo(startDate) >= 0 && camp.getEndDate().compareTo(endDate) <= 0) {
                result.add(camp);
            }
        }
        return result;
    }

    public CampList filterByStudent(Student student) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getAttendees().contains(student)) {
                result.add(camp);
            }
        }
        return result;
    }

    public CampList filterByStaff(Staff staff) {
        CampList result = new CampList();
        for (Camp camp : super.all) {
            if (camp.getStaffInCharge().equals(staff)) {
                result.add(camp);
            }
        }
        return result;
    }

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

    public CampList getAll() {
        return this;
    }

    public Camp[] toArray() {
        return super.all.toArray(new Camp[super.all.size()]);
    }

}
