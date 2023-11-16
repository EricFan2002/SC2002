package controller.camp;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import entity.camp.Camp;
import entity.user.User;
import entity.user.Student;

public class CampStatusMonitorController {

    public static ArrayList<User> getAttendingStudents(Camp camp) {
        ArrayList<User> attendingStudents = new ArrayList<>();
        if (camp != null) {
            // Assuming Camp has a method to get all attending students
            attendingStudents.addAll(camp.getAttendees());
        }
        return attendingStudents;
    }

    public static Map<String, Integer> generatePerformanceReport(Camp camp) {
        Map<String, Integer> performanceReport = new HashMap<>();
        Set<Student> students = camp.getCommittees();
        for (Student student : students) {
            performanceReport.put(student.getName(), student.getPoints());
        }
        return performanceReport;
    }
}