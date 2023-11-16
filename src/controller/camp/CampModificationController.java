package controller.camp;

import entity.RepositoryCollection;
import entity.camp.Camp;
//import entity.camp.CampDetails;

public class CampModificationController {
    public static void createCamp(Camp camp) {
        RepositoryCollection.campRepository.insert(camp);
    }

    public static void deleteCamp(Camp camp) {
        RepositoryCollection.campRepository.remove(camp);
    }

    // I changed this to Camp instead of CampDetails
    public static void editCamp(Camp newCD) {
        Camp updatedCamp = new Camp(newCD.getID(), newCD.getName(), newCD.getDescription(), newCD.isVisible(),
                newCD.getStartDate(), newCD.getEndDate(), newCD.getCloseRegistrationDate(), newCD.getSchool(),
                newCD.getLocation(), newCD.getStaffInCharge(), newCD.getTotalSlots());

        RepositoryCollection.campRepository.update(updatedCamp);
        RepositoryCollection.campRepository.update(updatedCamp);
    }
}
