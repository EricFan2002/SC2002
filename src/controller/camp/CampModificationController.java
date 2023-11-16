package controller.camp;

import entity.CampRepository;
import entity.RepositoryCollection;
import entity.camp.Camp;
//import entity.camp.CampDetails;

public class CampModificationController {

    public void createCamp(Camp camp) {
        RepositoryCollection.getCampRepository().insert(camp);
    }

    public void deleteCamp(Camp camp) {

        RepositoryCollection.getCampRepository().remove(camp);
    }

    // I changed this to Camp instead of CampDetails
    public void editCamp(Camp oldCD, Camp newCD) {
        Camp updatedCamp = new Camp(oldCD.getID(), newCD.getName(), newCD.getDescription(), newCD.isVisible(),
                newCD.getStartDate(), newCD.getEndDate(), newCD.getCloseRegistrationDate(), newCD.getSchool(),
                newCD.getLocation(), newCD.getStaffInCharge());

        RepositoryCollection.getCampRepository().update(updatedCamp);
    }
}
