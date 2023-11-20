package controller.camp;

import entity.camp.Camp;
import entity.user.Student;

/**
 * The {@code CampRegistrationController} class is responsible for handling the
 * registration and deregistration
 * of students for camps. This class acts as a controller for camp registration
 * operations, leveraging its
 * inner class {@code CampRegistrationService} for specific actions.
 * 
 * <p>
 * It provides an interface to manage the addition and removal of students as
 * attendees in various camp events.
 */
public class CampRegistrationController {
    /**
     * The {@code CampRegistrationService} class provides services for registering
     * and deregistering students
     * in camps. It contains methods to add or remove a student from the list of
     * attendees in a given camp.
     * 
     * <p>
     * Key functionalities include:
     * <ul>
     * <li>Registering a student for a camp.
     * <li>Deregistering a student from a camp.
     * </ul>
     * 
     * @see entity.camp.Camp
     * @see entity.user.Student
     */
    public class CampRegistrationService {
        /**
         * Registers a student to a camp by adding them to the camp's list of attendees.
         * This method is used to add a {@link Student} to the attendee list of a
         * specified {@link Camp}.
         *
         * @param camp    The {@link Camp} to which the student is to be registered.
         * @param student The {@link Student} to be registered for the camp.
         */
        public static void registerCamp(Camp camp, Student student) {
            camp.addAttendee(student);
        }

        /**
         * Deregisters a student from a camp by removing them from the camp's list of
         * attendees.
         * This method is used to remove a {@link Student} from the attendee list of a
         * specified {@link Camp}.
         *
         * @param camp    The {@link Camp} from which the student is to be deregistered.
         * @param student The {@link Student} to be deregistered from the camp.
         */
        public static void deregisterCamp(Camp camp, Student student) {
            camp.removeAttendee(student);
        }

    }
}