/**
 * The {@code CampStatusMonitorController} class is responsible for monitoring and reporting on camp statuses.
 * It provides functionalities for retrieving the list of students attending a specific camp and generating 
 * performance reports based on student participation and achievements in camp activities.
 * 
 * <p> Key functionalities include:
 * <ul>
 * <li> Retrieving a list of students attending a camp.
 * <li> Generating performance reports for students based on their participation in camp committees.
 * </ul>
 *
 * @see app.entity.camp.Camp
 * @see app.entity.user.User
 * @see app.entity.user.Student
 */
package app.controller.camp;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import app.entity.camp.Camp;
import app.entity.user.User;
import app.entity.user.Student;

public class CampStatusMonitorController {
    /**
     * Priate constructor to prevent instantiation.
     */
    private CampStatusMonitorController() {
    }

    /**
     * Retrieves a list of all students attending a specific camp.
     * This method returns an {@link ArrayList} of {@link User} objects representing
     * students who are attendees
     * of the given camp. If the camp is null, it returns an empty list.
     *
     * @param camp The {@link Camp} for which the list of attending students is
     *             requested.
     * @return An {@link ArrayList} of {@link User} objects representing the
     *         students attending the camp.
     */
    ;

    public static ArrayList<User> getAttendingStudents(Camp camp) {
        ArrayList<User> attendingStudents = new ArrayList<>();
        if (camp != null) {
            // Assuming Camp has a method to get all attending students
            attendingStudents.addAll(camp.getAttendees());
        }
        return attendingStudents;
    }

    /**
     * Generates a performance report for a camp by evaluating the points of
     * students in camp committees.
     * This method returns a {@link Map} with student names as keys and their earned
     * points as values. It
     * assesses the performance of students based on their involvement and
     * achievements in the camp's committees.
     *
     * @param camp The {@link Camp} for which the performance report is to be
     *             generated.
     * @return A {@link Map} containing the names and points of students in the camp
     *         committees.
     */
    public static Map<String, Integer> generatePerformanceReport(Camp camp) {
        Map<String, Integer> performanceReport = new HashMap<>();
        Set<Student> students = camp.getCommittees();
        for (Student student : students) {
            performanceReport.put(student.getName(), student.getPoints());
        }
        return performanceReport;
    }
}