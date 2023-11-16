package controller.camp;

import entity.RepositoryCollection;
import entity.camp.Camp;
//import entity.camp.CampDetails;

public class CampModificationController {
    public static void createCamp(Camp camp) {
        RepositoryCollection.getCampRepository().insert(camp);
    }

    public static void deleteCamp(Camp camp) {
        RepositoryCollection.getCampRepository().remove(camp);
    }

    // I changed this to Camp instead of CampDetails
    public static void editCamp(Camp newCD) {
        Camp updatedCamp = new Camp(newCD.getID(), newCD.getName(), newCD.getDescription(), newCD.isVisible(),
                newCD.getStartDate(), newCD.getEndDate(), newCD.getCloseRegistrationDate(), newCD.getSchool(),
                newCD.getLocation(), newCD.getStaffInCharge());

        RepositoryCollection.getCampRepository().update(updatedCamp);
        RepositoryCollection.getCampRepository().update(updatedCamp);
    }
}
