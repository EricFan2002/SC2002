package controller.camp;

import entity.CampRepository;
import entity.camp.Camp;
import entity.user.Student;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class CampPerformanceController {
    private CampRepository campRepository;

    public CampPerformanceController(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public Map<String, Integer> generatePerformanceReport(Camp camp) {
        Set<Student> attendees = camp.getAttendees();
        Map<String, Integer> performanceReports = new HashMap();
        for (Student attendee : attendees) {
            int points = attendee.getPoints();
            String name = attendee.getName();
            performanceReports.put(name, points);
        }
        return performanceReports;
    }
}
