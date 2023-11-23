package ui;

import controller.camp.CampRegistrationController;
import controller.camp.OperationResult;
import controller.user.UserAccountManagementController;
import controller.user.UserController;
import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Staff;
import entity.user.Student;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetToggle;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

public class CampListViewStaff extends CampListView {
    protected WidgetToggle toggleCreated = new WidgetToggle(1, 7, getLenX() / 4 - 1, "Created Camps");
    protected WidgetToggle toggleMySchool = new WidgetToggle(1 + getLenX() / 4, 7, getLenX() / 4 - 1,
            "My Faculty Camps");
    protected WidgetButton createNewCampButton = new WidgetButton(1, 8, getLenX() / 2 - 2, "Create New Camp");
    protected Camp selectedCamp;
    protected OverlayCampInfoDisplayEnquiriesStaff overlayCampInfoDisplayEnquiriesStaff;
    protected OverlayCampAllSuggestionView overlayCampAllSuggestionView;
    protected OverlayCampSuggestionStaffView overlayCampSuggestionStaffView;
    private int staffMainViewIndex;

    public CampListViewStaff(int staffMainViewIndex) {
        super();
        this.staffMainViewIndex = staffMainViewIndex;
        addWidgetAfter(toggleCreated, filter4Index);
        addWidgetAfter(toggleMySchool, filter4Index + 1);
        addWidgetAfter(createNewCampButton, filter4Index + 2);
    }

    private WidgetButton buttonPosition;

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
                list = list.filterBySchool(staff.getFaculty());
            }
        }
        return list;
    }

    private Camp toBeDestroyed = null;

    // options.add("View Details");
    // options.add("Edit Camp");
    // options.add("Delete Camp");
    // options.add("Reply Enquiry");
    // options.add("View Suggestions");
    @Override
    public void messageLoop() {
        super.messageLoop();
        if (createNewCampButton.getPressed()) {
            if (UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff) UserController.getCurrentUser();
                Date in = new Date();
                LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
                Date currentTime = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                createNewCampButton.clearPressed();
                Camp newCamp = new Camp(String.valueOf(currentTimeMillis()), "", "", true, currentTime, currentTime,
                        currentTime, staff.getFaculty(), "", staff, 0);
                toBeDestroyed = newCamp;
                RepositoryCollection.getCampRepository().add(newCamp);
                OverlayCampInfoDisplayEdit overlayCampInfoDisplayEdit = new OverlayCampInfoDisplayEdit(
                        getLenX() / 2 - 2, getY(), 1, getLenX() / 2 + 2, "Create New Camp", newCamp,
                        (Staff) UserController.getCurrentUser(), CampListViewStaff.this);
                addOverlay(overlayCampInfoDisplayEdit);
            }
        }
        if (widgetPageSelection.getSelectedOption() != -1) {
            selectedCamp = displayCamps.get(widgetPageSelection.getSelectedOption());
            buttonPosition = widgetPageSelection.getSelectionsButton().get(widgetPageSelection.getSelectedOption())
                    .get(0);
            buttonPosition.clearPressed();
            OverlayCampListViewStaffCampActions overlayCampListViewStaffCampActions = new OverlayCampListViewStaffCampActions(
                    50, buttonPosition.getY(), buttonPosition.getX() + (getLenX() / 4 - 25), "Actions",
                    CampListViewStaff.this);
            addOverlay(overlayCampListViewStaffCampActions);
            widgetPageSelection.clearSelectedOption();
        }
        if (chose == 0 && choseString.equals("View Details")) { // view details
            OverlayCampInfoDisplayStudentView overlayCampInfoDisplay = new OverlayCampInfoDisplayStudentView(
                    getLenX() / 2 - 2, getY(), 1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
            choseString = "";
        } else if (chose == 1 && choseString.equals("Edit Camp")) { // join
            if (UserController.getCurrentUser() instanceof Staff) {
                OverlayCampInfoDisplayEdit overlayCampInfoDisplayEdit = new OverlayCampInfoDisplayEdit(
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

    @Override
    public void onWindowFinished(int chose, String choseString) {
        this.chose = chose;
        this.choseString = choseString;
        if (overlayCampInfoDisplayEnquiriesStaff != null && overlayCampInfoDisplayEnquiriesStaff.getDestroy() != true) {
            overlayCampInfoDisplayEnquiriesStaff.onWindowFinished(chose, choseString);
        }
        if (overlayCampAllSuggestionView != null && overlayCampAllSuggestionView.getDestroy() != true) {
            overlayCampAllSuggestionView.onWindowFinished(chose, choseString);
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
    }

}
