package ui.CampListView;

import controller.camp.CampRegistrationController;
import controller.camp.OperationResult;
import controller.user.UserController;
import entity.camp.Camp;
import entity.camp.CampList;
import entity.user.Student;
import entity.user.User;
import ui.CampEnquiriesView.OverlayCampInfoDisplayEnquiries;
import ui.CampEnquiriesView.OverlayCampInfoDisplayEnquiriesCommittee;
import ui.CampInfomationView.OverlayCampInfoDisplayWithParticipantsViewStudentView;
import ui.CampSuggestionView.OverlayCampSuggestionCommitteeView;
import ui.OverlayActions.OverlayCampListViewStudentCampActions;
import ui.OverlayActions.OverlayChooseBox;
import ui.OverlayActions.OverlayNotification;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetToggle;

import java.util.ArrayList;

/**
 * Represents a view tailored for students to interact with and manage camps.
 * Extends the CampListView class, providing functionalities specifically for student users.
 */
public class CampListViewStudent extends CampListView {
    protected WidgetToggle toggleJoinedCamp = new WidgetToggle(1, 8, getLenX() / 8 - 1, "Joined");
    protected WidgetToggle toggleCommittee = new WidgetToggle(getLenX() / 8, 8, getLenX() / 8 + 1, "As Committee");
    protected WidgetButton sortByButton = new WidgetButton(1 + getLenX() / 4 + getLenX() / 8, 8, getLenX() / 8,
            "Sort By");
    protected WidgetButton generateReportButton = new WidgetButton(1 + getLenX() / 4, 8, getLenX() / 8, "Gen Report");
    protected WidgetToggle toggleAvailable = new WidgetToggle(1, 7, getLenX() / 4 - 1, "Available As Participant");
    protected WidgetToggle toggleCommitteeAvailableC = new WidgetToggle(getLenX() / 4 + 1, 7, getLenX() / 4 - 1,
            "Available As Committee");
    protected Camp selectedCamp;
    protected OverlayCampInfoDisplayEnquiries overlayCampInfoDisplayEnquiries;
    protected OverlayCampInfoDisplayEnquiriesCommittee overlayCampInfoDisplayEnquiriesCommittee;
    private String sortMethod = "";

    protected OverlayCampSuggestionCommitteeView overlayCampSuggestionCommitteeView;
    private int studentMainViewIndex;

    /**
     * Constructs a CampListViewStudent with a specific student main view index.
     * @param studentMainViewIndex The index for the main student view.
     */
    public CampListViewStudent(int studentMainViewIndex) {
        super();
        this.studentMainViewIndex = studentMainViewIndex;
        addWidgetAfter(sortByButton, filter4Index);
        addWidgetAfter(generateReportButton, filter4Index);
        addWidgetAfter(toggleCommittee, filter4Index);
        addWidgetAfter(toggleJoinedCamp, filter4Index);
        addWidgetAfter(toggleCommitteeAvailableC, filter4Index);
        addWidgetAfter(toggleAvailable, filter4Index);
    }

    private WidgetButton buttonPosition;

    /**
     * Customizes the filter for camps based on student-specific criteria.
     * Overrides the method in the parent class.
     * @param list The original list of camps to be filtered.
     * @return The filtered list of camps based on student-specific criteria.
     */
    protected CampList CustomFilter(CampList list) {
        if (UserController.getCurrentUser() != null && UserController.getCurrentUser() instanceof Student) {
            CampList list1 = list.filterBySchool(((Student) UserController.getCurrentUser()).getSchool());
            list = list.filterBySchool("NTU");
            for (Camp c : list1) {
                list.add(c);
            }
        }
        list = list.filterByVisibility(true);
        if (toggleAvailable.getPressed()) {
            CampList newList = new CampList();
            for (Camp c : list) {
                if (c.getAttendees().size() + c.getCommittees().size() < c.getTotalSlots()) {
                    newList.add(c);
                }
            }
            list = newList;
        }
        if (toggleCommittee.getPressed()) {
            User user = UserController.getCurrentUser();
            if (user != null) {
                CampList newList = new CampList();
                for (Camp c : list) {
                    if (c.getCommittees().contains(user)) {
                        newList.add(c);
                    }
                }
                list = newList;
            }
        }
        if (toggleJoinedCamp.getPressed()) {
            User user = UserController.getCurrentUser();
            if (user != null) {
                CampList newList = new CampList();
                for (Camp c : list) {
                    if (c.getCommittees().contains(user) || c.getAttendees().contains(user)) {
                        newList.add(c);
                    }
                }
                list = newList;
            }
        }
        if (toggleCommitteeAvailableC.getPressed()) {
            CampList newList = new CampList();
            for (Camp c : list) {
                if (c.getCommittees().size() < 10) {
                    newList.add(c);
                }
            }
            list = newList;
        }
        if (sortMethod.equals("By Camp Name")) {
            list = list.sortByName();
        } else if (sortMethod.equals("By Starting Date")) {
            list = list.sortByStartingDate();
        } else if (sortMethod.equals("By End Date")) {
            list = list.sortByEndDate();
        } else if (sortMethod.equals("By Registration Date")) {
            list = list.sortByRegistrationCloseDate();
        } else if (sortMethod.equals("By Location")) {
            list = list.sortByLocation();
        } else{
            list = list.sortByName();
        }
        return list;
    }

    /**
     * Handles interactions and actions within the student-specific camp view.
     * Overrides the messageLoop method in the parent class.
     */
    @Override
    public void messageLoop() {
        super.messageLoop();
        if (widgetPageSelection.getSelectedOption() != -1) {
            selectedCamp = displayCamps.get(widgetPageSelection.getSelectedOption());
            buttonPosition = widgetPageSelection.getSelectionsButton().get(widgetPageSelection.getSelectedOption())
                    .get(0);
            buttonPosition.clearPressed();
            if (UserController.getCurrentUser() instanceof Student) {
                OverlayCampListViewStudentCampActions overlayCampListViewStudentCampActions = new OverlayCampListViewStudentCampActions(
                        50, buttonPosition.getY(), buttonPosition.getX() + (getLenX() / 4 - 25), "Actions",
                        CampListViewStudent.this, (Student) UserController.getCurrentUser(), selectedCamp);
                addOverlay(overlayCampListViewStudentCampActions);
            }
            widgetPageSelection.clearSelectedOption();
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
                    sortByButton.getX(), "Sort By?", options, CampListViewStudent.this);
            addOverlay(overlayTestClass);
        }
        if (generateReportButton.getPressed()) {
            generateReportButton.clearPressed();
            ArrayList<String> options = new ArrayList<>();
            options.add("Camp Report");
            options.add("Enquiries Report");
            options.add("Cancel");
            OverlayChooseBox overlayTestClass = new OverlayChooseBox(generateReportButton.getLen(),  generateReportButton.getY(), generateReportButton.getX(), "Which Report?", options, CampListViewStudent.this);
            addOverlay(overlayTestClass);
        }
        if (choseString.equals("View Details")) { // view details
            OverlayCampInfoDisplayWithParticipantsViewStudentView overlayCampInfoDisplay = new OverlayCampInfoDisplayWithParticipantsViewStudentView(
                    getLenX() / 2 - 2, getY(), 1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
            choseString = "";
        } else if (chose == 0 && choseString.equals("CFM")) { // view details
            OverlayCampInfoDisplayWithParticipantsViewStudentView overlayCampInfoDisplay = new OverlayCampInfoDisplayWithParticipantsViewStudentView(
                    getLenX() / 2 - 2, getY(), 1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Join Camp")) { // join
            if (UserController.getCurrentUser() instanceof Student) {
                ArrayList<String> options = new ArrayList<>();
                options.add("Join As Participant");
                options.add("Join As Committee");
                options.add("Cancel");
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(50, buttonPosition.getY(),
                        buttonPosition.getX() + (getLenX() / 4 - 25), "Join As?", options, CampListViewStudent.this);
                addOverlay(overlayChooseBox);
            }
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Quit Camp")) {
            if (UserController.getCurrentUser() instanceof Student) {
                Student student = (Student) UserController.getCurrentUser();
                CampRegistrationController.deregisterCamp(selectedCamp, student);
            }
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Join As Participant")) { // join
            if (UserController.getCurrentUser() instanceof Student) {
                Student student = (Student) UserController.getCurrentUser();
                OperationResult result = CampRegistrationController.registerCamp(selectedCamp, student);
                OverlayNotification overlayNotification = new OverlayNotification(70, getY() / 2 - 8, getX() / 2 - 35,
                        "Info", result.getComment(), CampListViewStudent.this);
                addOverlay(overlayNotification);
            }
            refreshList(true);
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Join As Committee")) { // join
            if (UserController.getCurrentUser() instanceof Student) {
                Student student = (Student) UserController.getCurrentUser();
                OperationResult result = CampRegistrationController.registerCampAsCommittee(selectedCamp, student);
                OverlayNotification overlayNotification = new OverlayNotification(70, getY() / 2 - 8, getX() / 2 - 35,
                        "Info", result.getComment(), CampListViewStudent.this);
                addOverlay(overlayNotification);
            }
            refreshList(true);
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Enquiry")) { // Enquiry
            if (UserController.getCurrentUser() instanceof Student) {
                Student student = (Student) UserController.getCurrentUser();
                overlayCampInfoDisplayEnquiries = new OverlayCampInfoDisplayEnquiries(getLenX() / 2 - 2, getY(), 1,
                        getLenX() / 2 + 2, "Camp Details", selectedCamp, student, CampListViewStudent.this);
                addOverlay(overlayCampInfoDisplayEnquiries);
            }
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Reply Enquiry")) { // Reply Enquiry
            if (UserController.getCurrentUser() instanceof Student) {
                Student student = (Student) UserController.getCurrentUser();
                overlayCampInfoDisplayEnquiriesCommittee = new OverlayCampInfoDisplayEnquiriesCommittee(
                        getLenX() / 2 - 2, getY(), 1, getLenX() / 2 + 2, "Camp Details", selectedCamp, student,
                        CampListViewStudent.this);
                addOverlay(overlayCampInfoDisplayEnquiriesCommittee);
            }
            chose = -1;
            choseString = "";
        } else if (choseString.equals("Suggestions")) { // Create Suggestion
            if (UserController.getCurrentUser() instanceof Student) {
                Student student = (Student) UserController.getCurrentUser();
                overlayCampSuggestionCommitteeView = new OverlayCampSuggestionCommitteeView(getLenX() / 2 - 2, getY(), 1,
                        getLenX() / 2 + 2, "Create Suggestion To Camp", selectedCamp, student,
                        CampListViewStudent.this);
                addOverlay(overlayCampSuggestionCommitteeView);
            }
            chose = -1;
            choseString = "";
        }
        if (backButton.getPressed()) {
            System.out.println("Back button pressed in CampListView");
            backButton.clearPressed();
            switchToWindow = studentMainViewIndex;
        }
    }

    // options.add("View Details");
    // options.add("Join Camp");
    // options.add("Quit Camp");
    // options.add("Enquiry");
    // options.add("Reply Enquiry");
    // options.add("Create Suggestion");
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
        this.chose = chose;
        this.choseString = choseString;
        if (overlayCampInfoDisplayEnquiries != null && overlayCampInfoDisplayEnquiries.getDestroy() != true) {
            overlayCampInfoDisplayEnquiries.onWindowFinished(chose, choseString);
        }
        if (overlayCampInfoDisplayEnquiriesCommittee != null
                && overlayCampInfoDisplayEnquiriesCommittee.getDestroy() != true) {
            overlayCampInfoDisplayEnquiriesCommittee.onWindowFinished(chose, choseString);
        }
        if (overlayCampSuggestionCommitteeView != null && overlayCampSuggestionCommitteeView.getDestroy() != true) {
            overlayCampSuggestionCommitteeView.onWindowFinished(chose, choseString);
        }
        if (choseString.equals("By Camp Name")) {
            sortMethod = "By Camp Name";
        } else if (choseString.equals("By Starting Date")) {
            sortMethod = "By Starting Date";
        } else if (choseString.equals("By End Date")) {
            sortMethod = "By End Date";
        } else if (choseString.equals("By Reg Date")) {
            sortMethod = "By Registration Date";
        } else if (choseString.equals("By Location")) {
            sortMethod = "By Location";
        }
    }
}
