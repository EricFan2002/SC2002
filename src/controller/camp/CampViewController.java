package controller.camp;

import entity.camp.Camp;
import entity.RepositoryCollection;
import entity.user.Staff;
import entity.user.Student;

public class CampViewController {

    public static Camp[] getCamp(String school) {
        return RepositoryCollection.getCampRepository()
                .filterBySchool(school).toArray();
    }

    public static Camp[] getCamp() {
        return RepositoryCollection.getCampRepository().toArray();
    }

    public static Camp[] getCamp(Staff staff) {
        return RepositoryCollection.getCampRepository().filterByStaff(staff).toArray();
    }

    public static Camp[] getCamp(Student student) {
        return RepositoryCollection.getCampRepository()
                .filterByStudent(student).toArray();
    }

}
