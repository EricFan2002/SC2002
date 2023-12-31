package app.ui.camp.listview;

import app.controller.user.UserController;
import app.entity.RepositoryCollection;
import app.entity.camp.Camp;
import app.entity.camp.CampList;
import app.entity.user.Staff;
import app.ui.camp.modificationview.OverlayCampStaffEditView;
import app.ui.camp.enquiriesview.OverlayCampInfoDisplayEnquiriesStaff;
import app.ui.camp.infomationview.OverlayCampInfoDisplayWithParticipantsViewStudentView;
import app.ui.camp.suggestionview.OverlayCampSuggestionCommitteeView;
import app.ui.camp.suggestionview.OverlayCampSuggestionStaffView;
import app.ui.overlayactions.OverlayCampListViewStaffCampActions;
import app.ui.overlayactions.OverlayChooseBox;
import app.ui.overlayactions.OverlayNotification;
import app.ui.widgets.WidgetButton;
import app.ui.widgets.WidgetToggle;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * Represents a view specifically designed for staff members to manage camps.
 * Extends the CampListView class, providing additional functionalities for staff users.
 */
public class CampListViewStaff extends CampListView {
    protected WidgetToggle toggleCreated = new WidgetToggle(1, 7, getLenX() / 4 - 1, "Created Camps");
    protected WidgetToggle toggleMySchool = new WidgetToggle(1 + getLenX() / 4, 7, getLenX() / 4 - 1,
            "My Faculty Camps");
    protected WidgetButton sortByButton = new WidgetButton(1 + getLenX() / 4 + getLenX() / 8, 8, getLenX() / 8,
            "Sort By");
    protected WidgetButton generateReportButton = new WidgetButton(getLenX() / 4 + 1, 8, getLenX() / 8,
            "Generate Report");
    protected WidgetButton createNewCampButton = new WidgetButton(1, 8, getLenX() / 4 - 1, "Create New Camp");
    protected Camp selectedCamp;
    protected OverlayCampInfoDisplayEnquiriesStaff overlayCampInfoDisplayEnquiriesStaff;
    protected OverlayCampSuggestionCommitteeView overlayCampSuggestionCommitteeView;
    protected OverlayCampSuggestionStaffView overlayCampSuggestionStaffView;
    private int staffMainViewIndex;
    private String sortMethod = "By";

    /**
     * Constructs a CampListViewStaff with specific staff view index.
     * @param staffMainViewIndex The index for the main staff view.
     */
    public CampListViewStaff(int staffMainViewIndex) {
        super();
        this.staffMainViewIndex = staffMainViewIndex;
        addWidgetAfter(toggleCreated, filter4Index);
        addWidgetAfter(toggleMySchool, filter4Index + 1);
        addWidgetAfter(createNewCampButton, filter4Index + 2);
        addWidgetAfter(generateReportButton, filter4Index + 3);
        addWidgetAfter(sortByButton, filter4Index + 4);
    }

    private WidgetButton buttonPosition;

    /**
     * Customizes the filter for camps based on staff-specific criteria.
     * Overrides the method in the parent class.
     * @param list The original list of camps to be filtered.
     * @return The filtered list of camps based on staff-specific criteria.
     */
    protected CampList CustomFilter(CampList list) {
        if (toggleCreated.getPressed()) {
            if (UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff) UserController.getCurrentUser();
                list = list.filterByStaff(staff);
            }
        }
        if (toggleMySchool.getPressed()) {
            if (UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff) UserController.getCurrentUser();
                list = list.filterBySchool(staff.getSchool());
            }
        }
        if (sortMethod.equals("By Camp Name")) {
            list = list.sortByName();
        } else if (sortMethod.equals("By Starting Date")) {
            list = list.sortByStartingDate();
        } else if (sortMethod.equals("By End Date")) {
            list = list.sortByEndDate();
        } else if (sortMethod.equals("By Reg Date")) {
            list = list.sortByRegistrationCloseDate();
        } else if (sortMethod.equals("By Location")) {
            list = list.sortByLocation();
        } else{
            list = list.sortByName();
        }
        return list;
    }

    private Camp toBeDestroyed = null;

    // options.add("View Details");
    // options.add("Edit Camp");
    // options.add("Delete Camp");
    // options.add("Reply Enquiry");
    // options.add("View Suggestions");

    /**
     * Handles interactions and actions within the staff-specific camp view.
     * Overrides the messageLoop method in the parent class.
     */
    @Override
    public void messageLoop() {
        super.messageLoop();
        sortByButton.setText("Sort " + sortMethod);
        if (createNewCampButton.getPressed()) {
            if (UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff) UserController.getCurrentUser();
                Date in = new Date();
                LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
                Date currentTime = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                createNewCampButton.clearPressed();
                Camp newCamp = new Camp(String.valueOf(currentTimeMillis()), "", "", true, currentTime, currentTime,
                        currentTime, staff.getSchool(), "", staff, 0, 0);
                toBeDestroyed = newCamp;
                RepositoryCollection.getCampRepository().add(newCamp);
                OverlayCampStaffEditView overlayCampInfoDisplayEdit = new OverlayCampStaffEditView(
                        getLenX() / 2 - 2, getY(), 1, getLenX() / 2 + 2, "Create New Camp", newCamp,
                        (Staff) UserController.getCurrentUser(), CampListViewStaff.this);
                addOverlay(overlayCampInfoDisplayEdit);
            }
        }
        if (sortByButton.getPressed()) {
            sortByButton.clearPressed();
            ArrayList<String> options = new ArrayList<>();
            options.add("By Camp Name");
            options.add("By Starting Date");
            options.add("By End Date");
            options.add("By Reg Date");
            options.add("By Location");
            OverlayChooseBox overlayTestClass = new OverlayChooseBox(sortByButton.getLen(), sortByButton.getY(),
                    sortByButton.getX(), "Sort By?", options, CampListViewStaff.this);
            addOverlay(overlayTestClass);
        }
        if (generateReportButton.getPressed()) {
            generateReportButton.clearPressed();
            ArrayList<String> options = new ArrayList<>();
            options.add("Camp Report");
            options.add("Performance Report");
            options.add("Enquiries Report");
            options.add("Cancel");
            OverlayChooseBox overlayTestClass = new OverlayChooseBox(generateReportButton.getLen(),  generateReportButton.getY(), generateReportButton.getX(), "Which Report?", options, CampListViewStaff.this);
            addOverlay(overlayTestClass);
        }
        if (widgetPageSelection.getSelectedOption() != -1) {
            selectedCamp = displayCamps.get(widgetPageSelection.getSelectedOption());
            buttonPosition = widgetPageSelection.getSelectionsButton().get(widgetPageSelection.getSelectedOption())
                    .get(0);
            buttonPosition.clearPressed();
            if (UserController.getCurrentUser() != null && UserController.getCurrentUser() instanceof Staff) {
                OverlayCampListViewStaffCampActions overlayCampListViewStaffCampActions = new OverlayCampListViewStaffCampActions(
                        50, buttonPosition.getY(), buttonPosition.getX() + (getLenX() / 4 - 25), "Actions",
                        CampListViewStaff.this, (Staff) UserController.getCurrentUser(), selectedCamp);
                addOverlay(overlayCampListViewStaffCampActions);
            }
            widgetPageSelection.clearSelectedOption();
        }
        if (chose == 0 && choseString.equals("View Details")) { // view details
            OverlayCampInfoDisplayWithParticipantsViewStudentView overlayCampInfoDisplay = new OverlayCampInfoDisplayWithParticipantsViewStudentView(
                    getLenX() / 2 - 2, getY(), 1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
            choseString = "";
        } else if (chose == 1 && choseString.equals("Edit Camp")) { // join
            if (UserController.getCurrentUser() instanceof Staff) {
                OverlayCampStaffEditView overlayCampInfoDisplayEdit = new OverlayCampStaffEditView(
                        getLenX() / 2 - 2, getY(), 1, getLenX() / 2 + 2, "Edit Camp " + selectedCamp.getName(),
                        selectedCamp, (Staff) UserController.getCurrentUser(), CampListViewStaff.this);
                addOverlay(overlayCampInfoDisplayEdit);
            }
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Delete Camp")) { // join
            if (UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff) UserController.getCurrentUser();
                if (selectedCamp.getCommittees().size() > 0 || selectedCamp.getAttendees().size() > 0) {
                    OverlayNotification overlayNotification = new OverlayNotification(70, getY() / 2 - 8,
                            getX() / 2 - 35, "Error", "Camp Still Have Participants", CampListViewStaff.this);
                    addOverlay(overlayNotification);
                } else {
                    RepositoryCollection.getCampRepository().remove(selectedCamp);
                }
            }
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Reply Enquiry")) { // Reply Enquiry
            if (UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff) UserController.getCurrentUser();
                overlayCampInfoDisplayEnquiriesStaff = new OverlayCampInfoDisplayEnquiriesStaff(getLenX() / 2 - 2,
                        getY(), 1, getLenX() / 2 + 2, "Camp Details", selectedCamp, staff, CampListViewStaff.this);
                addOverlay(overlayCampInfoDisplayEnquiriesStaff);
            }
            chose = -1;
            choseString = "";
        } else if (choseString.equals("View Suggestions")) { // View Suggestions
            if (UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff) UserController.getCurrentUser();
                overlayCampSuggestionStaffView = new OverlayCampSuggestionStaffView(getLenX() / 2 - 2, getY(), 1,
                        getLenX() / 2 + 2, "All Suggestions For Camp " + selectedCamp.getName(), selectedCamp, staff,
                        CampListViewStaff.this);
                addOverlay(overlayCampSuggestionStaffView);
            }
            chose = -1;
            choseString = "";
        }
        if (backButton.getPressed()) {
            backButton.clearPressed();
            switchToWindow = staffMainViewIndex;
        }
    }

    private int chose = -1;
    private String choseString = "";

    /**
     * Handles actions and decisions upon completion of specific windows or actions.
     * Overrides the onWindowFinished method in the parent class.
     * @param chose The chosen action identifier.
     * @param choseString The string representation of the chosen action.
     */
    @Override
    public void onWindowFinished(int chose, String choseString) {
        super.onWindowFinished(chose, choseString);
        if (choseString.equals("Approve") && overlayCampSuggestionStaffView != null) {
            refreshList(true);
        }
        this.chose = chose;
        this.choseString = choseString;
        if (overlayCampInfoDisplayEnquiriesStaff != null && overlayCampInfoDisplayEnquiriesStaff.getDestroy() != true) {
            overlayCampInfoDisplayEnquiriesStaff.onWindowFinished(chose, choseString);
        }
        if (overlayCampSuggestionCommitteeView != null && overlayCampSuggestionCommitteeView.getDestroy() != true) {
            overlayCampSuggestionCommitteeView.onWindowFinished(chose, choseString);
        }
        if (overlayCampSuggestionStaffView != null && overlayCampSuggestionStaffView.getDestroy() != true) {
            overlayCampSuggestionStaffView.onWindowFinished(chose, choseString);
        }
        if (chose == 254) {
            if (toBeDestroyed != null && choseString.equals("cancel")) {
                RepositoryCollection.getCampRepository().remove(toBeDestroyed);
            }
            toBeDestroyed = null;
            refreshList(true);
        }
        if (choseString.equals("By Camp Name")) {
            sortMethod = "By Camp Name";
        } else if (choseString.equals("By Starting Date")) {
            sortMethod = "By Starting Date";
        } else if (choseString.equals("By End Date")) {
            sortMethod = "By End Date";
        } else if (choseString.equals("By Reg Date")) {
            sortMethod = "By Reg Date";
        } else if (choseString.equals("By Location")) {
            sortMethod = "By Location";
        }

    }

}
