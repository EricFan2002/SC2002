package app.ui.camp.suggestionview;

import app.controller.camp.CampModificationController;
import app.controller.camp.CampSuggestionController;
import app.entity.RepositoryCollection;
import app.entity.camp.Camp;
import app.entity.suggestion.Suggestion;
import app.entity.suggestion.SuggestionList;
import app.entity.suggestion.SuggestionStatus;
import app.entity.user.Staff;
import app.ui.overlayactions.OverlayChooseBox;
import app.ui.widgets.WidgetButton;
import app.ui.widgets.WidgetPageSelection;
import app.ui.windows.ICallBack;
import app.ui.windows.Window;
import app.ui.windows.WindowOverlayClass;

import java.util.ArrayList;
/**
 * Represents an overlay view displaying camp suggestions for staff members.
 * Extends the WindowOverlayClass and implements the ICallBack interface.
 */
public class OverlayCampSuggestionStaffView extends WindowOverlayClass implements ICallBack {

    Camp camp;
    Staff staff;
    protected WidgetPageSelection allSuggestions;
    protected WidgetButton exitButton;
    protected Window mainWindow;
    protected ArrayList<Suggestion> suggestionArrayList;
    protected Suggestion selectedSuggestion;
    protected OverlayCampSuggestionView overlayCampSuggestionViewToBeAdded;

    /**
     * Constructs an OverlayCampSuggestionStaffView object.
     * @param x The x-coordinate position of the view.
     * @param y The y-coordinate position of the view.
     * @param offsetY The offset value for the y-coordinate.
     * @param offsetX The offset value for the x-coordinate.
     * @param windowName The name of the window.
     * @param camp The camp associated with the suggestions.
     * @param staff The staff member interacting with the suggestions.
     * @param mainWindow The main window where this overlay view is displayed.
     */
    public OverlayCampSuggestionStaffView(int x, int y, int offsetY, int offsetX, String windowName, Camp camp,
            Staff staff, Window mainWindow) {
        super(y, x, offsetY, offsetX, windowName);

        this.camp = camp;
        this.staff = staff;
        this.mainWindow = mainWindow;

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        allSuggestions = new WidgetPageSelection(3, 3, getX() - 10, getY() - 12, "Suggestions", enqList,
                OverlayCampSuggestionStaffView.this);

        addWidget(allSuggestions);

        exitButton = new WidgetButton(3, getY() - 6, getLenX() - 10, "Go Back");
        addWidget(exitButton);

        updateSuggestionList();

    }

    /**
     * Updates the list of suggestions displayed in the view.
     */
    public void updateSuggestionList() {
        suggestionArrayList = new ArrayList<>();

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        SuggestionList suggestions = RepositoryCollection.getSuggestionRepository().filterByCamp(camp);
        for (Suggestion suggestion : suggestions) {
            suggestionArrayList.add(suggestion);
            ArrayList<String> tmp = new ArrayList<String>();
            String status = "";
            if (suggestion.getStatus() == SuggestionStatus.PENDING) {
                status = " [ PENDING ]";
            } else if (suggestion.getStatus() == SuggestionStatus.APPROVED) {
                status = " [ APPROVED ]";
            } else if (suggestion.getStatus() == SuggestionStatus.REJECTED) {
                status = " [ REJECTED ]";
            }
            tmp.add("Suggestion: " + suggestion.getSuggestion().getID() + status);
            String changed = "";
            if (suggestion.getSuggestion().getLocation() != null && !suggestion.getSuggestion().getLocation().equals(camp.getLocation())) {
                changed += "Location, ";
            }
            if ((suggestion.getSuggestion().getStartDate() != null && suggestion.getSuggestion().getStartDate().compareTo(camp.getStartDate()) != 0) || (suggestion.getSuggestion().getEndDate() != null && suggestion.getSuggestion().getEndDate().compareTo(camp.getEndDate()) != 0)) {
                changed += "Date, ";
            }
            if (suggestion.getSuggestion().getDescription() != null && !suggestion.getSuggestion().getDescription().equals(suggestion.getOriginalCamp().getDescription())) {
                changed += "Description, ";
            }
            if (suggestion.getSuggestion().getName() != null && !suggestion.getSuggestion().getName().equals(suggestion.getOriginalCamp().getName())) {
                changed += "Name, ";
            }
            if (suggestion.getSuggestion().getCloseRegistrationDate() != null && suggestion.getSuggestion().getCloseRegistrationDate().compareTo(suggestion.getOriginalCamp().getCloseRegistrationDate()) != 0) {
                changed += "Reg Date, ";
            }
            if (suggestion.getSuggestion().getSchool() != null && !suggestion.getSuggestion().getSchool().equals(suggestion.getOriginalCamp().getSchool())) {
                changed += "Faculty, ";
            }
            if (suggestion.getSuggestion().getTotalSlots() != null && !suggestion.getSuggestion().getTotalSlots().equals(suggestion.getOriginalCamp().getTotalSlots())) {
                changed += "Slots, ";
            }
            if (suggestion.getSuggestion().getTotalCommitteeSlots() != null && !suggestion.getSuggestion().getTotalCommitteeSlots().equals(suggestion.getOriginalCamp().getTotalCommitteeSlots())) {
                changed += "Comm Slot, ";
            }
            if (changed.equals("")) {
                if (suggestion.getStatus() == SuggestionStatus.APPROVED) {
                    tmp.add("    APPROVED.");
                } else if (suggestion.getStatus() == SuggestionStatus.REJECTED) {
                    tmp.add("    REJECTED.");
                } else {
                    tmp.add("    Nothing Changed.");
                }
            } else {
                changed = changed.substring(0, changed.length() - 2);
                tmp.add("    Changed " + changed);
            }
            enqList.add(tmp);
        }
        allSuggestions.updateList(enqList);
    }

    /**
     * Handles the message loop for interactions with suggestions and buttons in the view.
     */
    @Override
    public void messageLoop() {
        super.messageLoop();
        if (exitButton.getPressed()) {
            exitButton.clearPressed();
            setDestroy();
        }
        if (allSuggestions.getSelectedOption() != -1) {
            selectedSuggestion = suggestionArrayList.get(allSuggestions.getSelectedOption());
            if (selectedSuggestion != null) {
                ArrayList<String> options = new ArrayList<>();
                options.add("View");
                if(selectedSuggestion.getStatus() == SuggestionStatus.PENDING) {
                    options.add("Approve");
                    options.add("Reject");
                }
                options.add("Cancel");
                WidgetButton buttonPosition = allSuggestions.getSelectionsButton()
                        .get(allSuggestions.getSelectedOption()).get(0);
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(),
                        getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options,
                        OverlayCampSuggestionStaffView.this);
                mainWindow.addOverlay(overlayChooseBox);
            }
            allSuggestions.clearSelectedOption();
        }
        if (overlayCampSuggestionViewToBeAdded != null) {
            mainWindow.addOverlay(overlayCampSuggestionViewToBeAdded);
            overlayCampSuggestionViewToBeAdded = null;
        }
    }

    /**
     * Overrides the onExit method from the parent class.
     * Performs additional actions when the overlay is exited.
     */
    public void onExit() {
        super.onExit();
    }


    /**
     * Handles actions triggered upon completion of certain operations in the overlay view.
     * @param chose The chosen action identifier.
     * @param choseString The string representation of the chosen action.
     */
    @Override
    public void onWindowFinished(int chose, String choseString) {
        if (choseString.equals("View") && selectedSuggestion != null) {
            overlayCampSuggestionViewToBeAdded = new OverlayCampSuggestionView(getX(), getY(), offsetY,
                    offsetX, "Suggestion For Camp " + selectedSuggestion.getSuggestion().getName(), selectedSuggestion);
            // mainWindow.addOverlay(overlaySuggestionInfoDisplayRaw);
        } else if (choseString.equals("Approve") && selectedSuggestion != null) {
//            CampModificationController.editCamp(selectedSuggestion.getSuggestion());
            CampSuggestionController.approveSuggestion(selectedSuggestion, staff, true);
            CampModificationController.editCamp(selectedSuggestion.getSuggestion());
            updateSuggestionList();
        } else if (choseString.equals("Reject") && selectedSuggestion != null) {
            CampSuggestionController.approveSuggestion(selectedSuggestion, staff, false);
//            selectedSuggestion.setStatus(SuggestionStatus.REJECTED);
            updateSuggestionList();
        }
        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        SuggestionList suggestions = RepositoryCollection.getSuggestionRepository().filterByCamp(camp);
        for (Suggestion suggestion : suggestions) {
            suggestionArrayList.add(suggestion);
            ArrayList<String> tmp = new ArrayList<String>();
            String status = "";
            if (suggestion.getStatus() == SuggestionStatus.PENDING) {
                status = " [ PENDING ]";
            } else if (suggestion.getStatus() == SuggestionStatus.APPROVED) {
                status = " [ APPROVED ]";
            } else if (suggestion.getStatus() == SuggestionStatus.REJECTED) {
                status = " [ REJECTED ]";
            }
            tmp.add("Suggestion: " + suggestion.getSuggestion().getID() + status);
            String changed = "";
            if (suggestion.getSuggestion().getLocation() != null && !suggestion.getSuggestion().getLocation().equals(camp.getLocation())) {
                changed += "Location, ";
            }
            if ((suggestion.getSuggestion().getStartDate() != null && suggestion.getSuggestion().getStartDate().compareTo(camp.getStartDate()) != 0) || (suggestion.getSuggestion().getEndDate() != null && suggestion.getSuggestion().getEndDate().compareTo(camp.getEndDate()) != 0)) {
                changed += "Date, ";
            }
            if (suggestion.getSuggestion().getDescription() != null && !suggestion.getSuggestion().getDescription().equals(suggestion.getOriginalCamp().getDescription())) {
                changed += "Description, ";
            }
            if (suggestion.getSuggestion().getName() != null && !suggestion.getSuggestion().getName().equals(suggestion.getOriginalCamp().getName())) {
                changed += "Name, ";
            }
            if (suggestion.getSuggestion().getCloseRegistrationDate() != null && suggestion.getSuggestion().getCloseRegistrationDate().compareTo(suggestion.getOriginalCamp().getCloseRegistrationDate()) != 0) {
                changed += "Reg Date, ";
            }
            if (suggestion.getSuggestion().getSchool() != null && !suggestion.getSuggestion().getSchool().equals(suggestion.getOriginalCamp().getSchool())) {
                changed += "Faculty, ";
            }
            if (suggestion.getSuggestion().getTotalSlots() != null && !suggestion.getSuggestion().getTotalSlots().equals(suggestion.getOriginalCamp().getTotalSlots())) {
                changed += "Slots, ";
            }
            if (suggestion.getSuggestion().getTotalCommitteeSlots() != null && !suggestion.getSuggestion().getTotalCommitteeSlots().equals(suggestion.getOriginalCamp().getTotalCommitteeSlots())) {
                changed += "Comm Slot, ";
            }
            if (changed.equals("")) {
                if (suggestion.getStatus() == SuggestionStatus.APPROVED) {
                    tmp.add("    APPROVED.");
                } else if (suggestion.getStatus() == SuggestionStatus.REJECTED) {
                    tmp.add("    REJECTED.");
                } else {
                    tmp.add("    Nothing Changed.");
                }
            } else {
                changed = changed.substring(0, changed.length() - 2);
                tmp.add("    Changed " + changed);
            }
            enqList.add(tmp);
        }

        allSuggestions.updateList(enqList);
    }
}
