package controller.camp;

import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.camp.CampDetails;

/**
 * The {@code CampModificationController} class is responsible for handling the
 * creation, deletion, and editing
 * of camp records in the camp repository. This class serves as a controller to
 * manage camp data,
 * leveraging the {@code RepositoryCollection} for data storage and retrieval
 * operations.
 *
 * <p>
 * Key functionalities include:
 * <ul>
 * <li>Creating new camp records and adding them to the repository.
 * <li>Deleting existing camp records from the repository.
 * <li>Editing and updating details of existing camp records.
 * </ul>
 * 
 * <p>
 * This controller interacts with the {@code RepositoryCollection} to perform
 * CRUD (Create, Read, Update, Delete)
 * operations on camp data, ensuring data integrity and consistency across the
 * application.
 *
 * @see entity.camp.Camp
 * @see entity.RepositoryCollection
 */
public class CampModificationController {
    /**
     * Creates a new camp by inserting it into the camp repository.
     * This method adds the specified {@link Camp} object to the camp repository.
     *
     * @param camp The {@link Camp} object to be inserted.
     */
    public static void createCamp(Camp camp) {
        RepositoryCollection.campRepository.insert(camp);
    }

    /**
     * Deletes a camp from the camp repository.
     * This method removes the specified {@link Camp} object from the camp
     * repository.
     *
     * @param camp The {@link Camp} object to be removed.
     */
    public static void deleteCamp(Camp camp) {
        RepositoryCollection.campRepository.remove(camp);
    }

    /**
     * Edits an existing camp in the camp repository by updating its details.
     * This method fetches a camp by its ID and updates its properties based on the
     * provided {@link CampDetails} object.
     * Fields in the {@code CampDetails} object that are non-null are used to update
     * the corresponding fields
     * in the camp object. This includes the camp's description, start date, end
     * date, registration closing date,
     * name, school, and location.
     * 
     * <p>
     * Note: If the {@code CampDetails} object contains a new ID, it updates the ID
     * of the camp as well.
     * 
     * @param newCD The {@link CampDetails} object containing the new details for
     *              the camp.
     */
    public static void editCamp(CampDetails newCD) {

        Camp campObj = RepositoryCollection.campRepository.getAll().filterByID(newCD.getID()).get(0);
        if (newCD.getDescription() != null) {
            campObj.setDescription(newCD.getDescription());
        }
        if (newCD.getStartDate() != null) {
            campObj.setStartDate(newCD.getStartDate());
        }
        if (newCD.getEndDate() != null) {
            campObj.setEndDate(newCD.getEndDate());
        }
        if (newCD.getCloseRegistrationDate() != null) {
            campObj.setCloseRegistrationDate(newCD.getCloseRegistrationDate());
        }
        if (newCD.getName() != null) {
            campObj.setName(newCD.getName());
        }
        if (newCD.getSchool() != null) {
            campObj.setSchool(newCD.getSchool());
        }
        if (newCD.getLocation() != null) {
            campObj.setLocation(newCD.getLocation());
        }
        if (newCD.getID() != null) {
            campObj.setID(newCD.getID());
        }
        RepositoryCollection.campRepository.update(campObj);
    }
}
