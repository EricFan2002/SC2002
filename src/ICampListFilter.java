import java.util.Date;
import java.util.Set;

import entity.user.Staff;
import entity.user.Student;

public interface ICampListFilter {
    CampList filterForStudents(boolean isVisible, String group);

    CampList filterByGroup(String group);

    CampList filterByLocation(String location);

    CampList filterByStaff(Staff staff);

    CampList filterByDate(Date startDate, Date endDate);

    CampList filterByRegistrationClosingDate(Date registrationClosingDate);

    CampList filterByAttendees(Set<Student> attendees);

    CampList filterByCommittees(Set<Student> committees);
}
