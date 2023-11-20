package controller.camp;

import controller.user.ActionResult;
import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;

import javax.swing.*;

public class CampRegistrationController {
    public class CampRegistrationService {
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
            else if(camp.getAttendees().size() == camp.getTotalSlots()){
                return new OperationResult(false, "No more slots");
            }
            else if(camp.getAttendees().contains(student)){
                return new OperationResult(false, "Already Joined");
            }
            camp.addAttendee(student);
            return new OperationResult(true, "Camp Joined!");
        }

        public static OperationResult deregisterCamp(Camp camp, Student student) {
            boolean result = camp.removeAttendee(student);
            return new OperationResult(result, "Quited");
        }

    }
}

// code below from previous version, not sure if needed
/*
 * public class CampRegistrationService {
 * private CampRepository campRepository;
 * 
 * public CampRegistrationService(CampRepository campRepository) {
 * this.campRepository = campRepository;
 * }
 * 
 * public boolean addStudent(Student student, String campId) {
 * Camp camp = campRepository.getAll().filterByID(campId).get(0);
 * if (camp != null) {
 * return camp.addAttendee(student);
 * } else {
 * // Handle the situation where the camp is full or not found
 * return false;
 * }
 * }
 * 
 * public boolean addCommitteeStudent(Student student, String campId) {
 * Camp camp = campRepository.getAll().filterByID(campId).get(0);
 * if (camp != null) {
 * return camp.addCommittee(student);
 * } else {
 * // Handle the situation where the camp is full or not found
 * return false;
 * }
 * }
 * 
 * public boolean withdrawStudent(Student student, String campId) {
 * Camp camp = campRepository.getAll().filterByID(campId).get(0);
 * if (camp != null) {
 * return camp.removeAttendee(student);
 * } else {
 * // Handle the situation where the camp is not found
 * return false;
 * }
 * }
 * 
 * public ArrayList<Camp> getCommitteeSlots() {
 * ArrayList<Camp> camps = new ArrayList<>();
 * for (Camp camp: campRepository.getAll()) {
 * if (camp.getCommittees().size() < Camp.MAX_COMMITTEE) {
 * camps.add(camp);
 * }
 * }
 * return camps;
 * }
 * 
 * public ArrayList<Camp> getJoinedCamps(User user) {
 * ArrayList<Camp> joinedCamps = new ArrayList<>();
 * for (Camp camp: campRepository.getAll()) {
 * Set<Student> attendees = camp.getAttendees();
 * Set<Student> committees = camp.getCommittees();
 * if (attendees.contains(user) || committees.contains(user)) {
 * joinedCamps.add(camp);
 * }
 * }
 * return joinedCamps;
 * }
 * }
 */