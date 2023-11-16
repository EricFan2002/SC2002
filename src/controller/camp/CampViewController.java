package controller.camp;

import entity.camp.Camp;
import entity.CampList;
import entity.user.Staff;
import entity.user.Student;

import java.util.List;


public class CampViewController {

    private CampList allCamps; // Assuming this holds all camps

    // Constructor to initialize with the list of camps
    public CampViewController(CampList allCamps) {
        this.allCamps = allCamps;
    }

    public Camp[] getCamp(String school) {
        List<Camp> filteredCamps = (List<Camp>) allCamps.filterBySchool(school).getAll();
        return filteredCamps.toArray(new Camp[filteredCamps.size()]);
    }

    public Camp[] getCamp() {
        List<Camp> allCampsList = (List<Camp>) allCamps.getAll();
        return allCampsList.toArray(new Camp[allCampsList.size()]);
    }

    public Camp[] getCamp(Staff staff) {
        List<Camp> filteredCamps = (List<Camp>) allCamps.filterByStaff(staff).getAll();
        return filteredCamps.toArray(new Camp[filteredCamps.size()]);
    }

    public Camp[] getCamp(Student student) {
        List<Camp> filteredCamps = (List<Camp>) allCamps.filterByStudent(student).getAll();
        return filteredCamps.toArray(new Camp[filteredCamps.size()]);
    }


}



