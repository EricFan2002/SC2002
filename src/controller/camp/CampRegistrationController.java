package controller.camp;

import controller.user.ActionResult;
import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;

import javax.swing.*;

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

    /**
     * Registers a student to a camp by adding them to the camp's list of attendees.
     * This method is used to add a {@link Student} to the attendee list of a
     * specified {@link Camp}.
     *
     * @param camp    The {@link Camp} to which the student is to be registered.
     * @param student The {@link Student} to be registered for the camp.
     */
    public static boolean checkConflict(Camp camp, Student student){
        CampList camps = RepositoryCollection.campRepository.getAll().filterByStudent(student);
        for(Camp oneCamp : camps){
            if(oneCamp.getStartDate().getTime() <= camp.getEndDate().getTime() || camp.getStartDate().getTime() <= oneCamp.getEndDate().getTime()){
                return false;
            }
        }
        return true;
    }
    public static OperationResult registerCamp(Camp camp, Student student) {
        if(checkConflict(camp, student)){
            return new OperationResult(false, "Time Conflict!");
        }
        else if(camp.getAttendees().size() >= camp.getTotalSlots()){
            return new OperationResult(false, "No More Slots");
        }
        else if(camp.getAttendees().contains(student)){
            return new OperationResult(false, "Already Joined");
        }
        else if(camp.getCommittees().contains(student)){
            return new OperationResult(false, "Already Joined As Committee");
        }
        camp.addAttendee(student);
        return new OperationResult(true, "Camp Joined");
    }

    public static boolean checkJoinedAsCommittee(Student student){
        CampList camps = RepositoryCollection.campRepository.getAll();
        for(Camp camp : camps){
            if(camp.getCommittees().contains(student)){
                return true;
            }
        }
        return false;
    }

    public static OperationResult registerCampAsCommittee(Camp camp, Student student) {
        if(checkConflict(camp, student)){
            return new OperationResult(false, "Time Conflict!");
        }
        else if(camp.getCommittees().size() >= 10){
            return new OperationResult(false, "No More Committee Slots");
        }
        else if(camp.getAttendees().contains(student)){
            return new OperationResult(false, "Already Joined As Participant");
        }
        else if(camp.getCommittees().contains(student)){
            return new OperationResult(false, "Already Joined As Committee");
        }
        else if(checkJoinedAsCommittee(student)){
            return new OperationResult(false, "Committee In Another Camp");
        }
        camp.addCommittee(student);
        return new OperationResult(true, "Joined As Committee");
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
    public static OperationResult deregisterCamp(Camp camp, Student student) {
        boolean result = camp.removeAttendee(student);
        return new OperationResult(result, "Quited");
    }
}