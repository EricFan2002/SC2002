public class CampModificationService {
    private CampRepository campRepository;

    public CampModificationService(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public int createCamp(Camp camp) {
        campRepository.insert(camp);
        return camp.getId(); // Assuming the camp's ID is set prior to this call
    }

    public boolean deleteCamp(int id) {
        return campRepository.remove(id);
    }

    public boolean editCamp(int id, Camp updatedCamp) {
        return campRepository.update(updatedCamp, id);
    }
}