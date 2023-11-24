package controller.camp;

import entity.camp.Camp;
import entity.RepositoryCollection;
import entity.user.Staff;
import entity.user.Student;

/**
 * The CampViewController class provides methods to retrieve information about
 * camps.
 */
public class CampViewController {
    /**
     * Private constructor to prevent instantiation.
     */
    private CampViewController() {
    }

    /**
     * Retrieves an array of camps filtered by school.
     *
     * @param school The name of the school to filter camps by.
     * @return An array of camps that match the specified school.
     */
    public static Camp[] getCamp(String school) {
        return RepositoryCollection.getCampRepository()
                .filterBySchool(school).toArray();
    }

    /**
     * Retrieves an array of all available camps.
     *
     * @return An array containing all camps.
     */
    public static Camp[] getCamp() {
        return RepositoryCollection.getCampRepository().toArray();
    }

    /**
     * Retrieves an array of camps associated with a staff member.
     *
     * @param staff The staff member for whom camps are to be retrieved.
     * @return An array of camps associated with the specified staff member.
     */
    public static Camp[] getCamp(Staff staff) {
        return RepositoryCollection.getCampRepository().filterByStaff(staff).toArray();
    }

    /**
     * Retrieves an array of camps associated with a student.
     *
     * @param student The student for whom camps are to be retrieved.
     * @return An array of camps associated with the specified student.
     */
    public static Camp[] getCamp(Student student) {
        return RepositoryCollection.getCampRepository()
                .filterByStudent(student).toArray();
    }
}
