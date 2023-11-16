package entity.camp;

import java.util.Date;
import java.util.List;

import entity.RepositoryList;
import entity.interfaces.IFilterableByDateRange;
import entity.interfaces.IFilterableByID;
import entity.interfaces.IFilterableBySchool;
import entity.interfaces.IFilterableByVisibility;
import entity.user.Staff;
import entity.user.Student;

public class CampList extends RepositoryList<Camp> implements IFilterableByID<Camp>, IFilterableByDateRange<Camp>,
        IFilterableBySchool<Camp>, IFilterableByVisibility<Camp> {
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

    public CampList getAll() {
        return this;
    }

}
