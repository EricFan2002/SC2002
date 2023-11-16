package controller.camp;

import entity.camp.Camp;
import entity.RepositoryCollection;
import entity.user.Staff;
import entity.user.Student;

public class CampViewController {

    public static Camp[] getCamp(String school) {
        return RepositoryCollection.campRepository.getAll()
                .filterBySchool(school).toArray();
    }

    public static Camp[] getCamp() {
        return RepositoryCollection.campRepository.getAll().toArray();
    }

    public static Camp[] getCamp(Staff staff) {
        return RepositoryCollection.campRepository.getAll().filterByStaff(staff).toArray();
    }

    public static Camp[] getCamp(Student student) {
        return RepositoryCollection.campRepository.getAll()
                .filterByStudent(student).toArray();
    }

}
