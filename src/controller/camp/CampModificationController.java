package controller.camp;

import entity.CampRepository;
import entity.camp.Camp;
//import entity.camp.CampDetails;

public class CampModificationController {
    private CampRepository campRepository;

    public CampModificationController(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public void createCamp(Camp camp) {
        campRepository.insert(camp);
    }

    public void deleteCamp(Camp camp) {
        campRepository.remove(camp);
    }

    // I changed this to Camp instead of CampDetails
    public void editCamp(Camp oldCD, Camp newCD) {
        Camp updatedCamp = new Camp(oldCD.getID(), newCD.getName(), newCD.getDescription(), newCD.isVisible(),
                newCD.getStartDate(), newCD.getEndDate(), newCD.getCloseRegistrationDate(), newCD.getSchool(),
                newCD.getLocation(), newCD.getStaffInCharge());

        campRepository.update(updatedCamp);
    }
}
