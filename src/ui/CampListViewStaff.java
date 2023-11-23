package ui;

import controller.camp.CampRegistrationController;
import controller.camp.OperationResult;
import controller.user.UserAccountManagementController;
import controller.user.UserController;
import entity.camp.Camp;
import entity.user.Staff;
import entity.user.Student;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetToggle;

import java.util.ArrayList;

public class CampListViewStaff extends CampListView{
    protected WidgetToggle toggleCreated = new WidgetToggle(1, 7, getLenX() / 4 - 2, "Created Camps");
    protected Camp selectedCamp;
    protected OverlayCampInfoDisplayEnquiries overlayCampInfoDisplayEnquiries;
    protected OverlayCampInfoDisplayEnquiriesCommittee overlayCampInfoDisplayEnquiriesCommittee;
    protected OverlayCampAllSuggestionView overlayCampAllSuggestionView;

    public CampListViewStaff(int studentMainViewIndex, int staffMainViewIndex) {
        super(studentMainViewIndex, staffMainViewIndex);
        addWidgetAfter(toggleCreated, filter4Index);
    }

    private WidgetButton buttonPosition;

//        options.add("View Details");
//        options.add("Edit Camp");
//        options.add("Delete Camp");
//        options.add("Reply Enquiry");
//        options.add("View Suggestions");
    @Override
    public void messageLoop() {
        super.messageLoop();
        if(widgetPageSelection.getSelectedOption() != -1){
            selectedCamp = displayCamps.get(widgetPageSelection.getSelectedOption());
            buttonPosition = widgetPageSelection.getSelectionsButton().get(widgetPageSelection.getSelectedOption()).get(0);
            buttonPosition.clearPressed();
            OverlayCampListViewStaffCampActions overlayCampListViewStaffCampActions = new OverlayCampListViewStaffCampActions(50,  buttonPosition.getY(), buttonPosition.getX() + (getLenX() / 4 - 25), "Actions", CampListViewStaff.this);
            addOverlay(overlayCampListViewStaffCampActions);
            widgetPageSelection.clearSelectedOption();
        }
        if(chose == 0 && choseString.equals("View Details")){ // view details
            OverlayCampInfoDisplayStudentView overlayCampInfoDisplay = new OverlayCampInfoDisplayStudentView(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
            choseString = "";
        }
        else if(chose == 1 && choseString.equals("Edit Camp")){ // join
            if(UserController.getCurrentUser() instanceof Staff) {
                OverlayCampInfoDisplayEdit overlayCampInfoDisplayEdit = new OverlayCampInfoDisplayEdit(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Edit Camp " + selectedCamp.getName() , selectedCamp, (Staff)UserController.getCurrentUser(), CampListViewStaff.this);
                addOverlay(overlayCampInfoDisplayEdit);
            }
            chose = -1;
            choseString = "";
        }
        else if(choseString.equals("Join As Participant")){ // join
            if(UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff)UserController.getCurrentUser();
//                OperationResult result = CampRegistrationController.registerCamp(selectedCamp, staff);
//                OverlayNotification overlayNotification = new OverlayNotification(40,  getY()/2 - 8, getX()/2 - 20, "Info", result.getComment(), CampListViewStaff.this);
//                addOverlay(overlayNotification);
            }
            chose = -1;
            choseString = "";
        }
        else if(choseString.equals("Join As Committee")){ // join
            if(UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff)UserController.getCurrentUser();
//                OperationResult result = CampRegistrationController.registerCampAsCommittee(selectedCamp, student);
//                OverlayNotification overlayNotification = new OverlayNotification(40,  getY()/2 - 8, getX()/2 - 20, "Info", result.getComment(), CampListViewStaff.this);
//                addOverlay(overlayNotification);
            }
            chose = -1;
            choseString = "";
        }
        else if(chose == 3 && choseString.equals("Enquiry")){ // Enquiry
            if(UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff)UserController.getCurrentUser();
//                overlayCampInfoDisplayEnquiries = new OverlayCampInfoDisplayEnquiries(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp, staff, CampListViewStaff.this);
//                addOverlay(overlayCampInfoDisplayEnquiries);
            }
            chose = -1;
            choseString = "";
        }
        else if(chose == 4 && choseString.equals("Reply Enquiry")){ // Enquiry
            if(UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff)UserController.getCurrentUser();
//                overlayCampInfoDisplayEnquiriesCommittee = new OverlayCampInfoDisplayEnquiriesCommittee(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp, staff, CampListViewStaff.this);
//                addOverlay(overlayCampInfoDisplayEnquiriesCommittee);
            }
            chose = -1;
            choseString = "";
        }
        else if(chose == 5 && choseString.equals("Suggestions")){ // Create Suggestion
            if(UserController.getCurrentUser() instanceof Staff) {
                Staff staff = (Staff)UserController.getCurrentUser();
//                overlayCampAllSuggestionView = new OverlayCampAllSuggestionView(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Create Suggestion To Camp", selectedCamp, staff, CampListViewStaff.this);
                addOverlay(overlayCampAllSuggestionView);
            }
            chose = -1;
            choseString = "";
        }
    }
//        options.add("View Details");
//        options.add("Join Camp");
//        options.add("Quit Camp");
//        options.add("Enquiry");
//        options.add("Reply Enquiry");
//        options.add("Create Suggestion");
    private int chose = -1;
    private String choseString = "";
    @Override
    public void onWindowFinished(int chose, String choseString) {
        this.chose = chose;
        this.choseString = choseString;
        if(overlayCampInfoDisplayEnquiries != null && overlayCampInfoDisplayEnquiries.getDestroy() != true){
            overlayCampInfoDisplayEnquiries.onWindowFinished(chose, choseString);
        }
        if(overlayCampInfoDisplayEnquiriesCommittee != null && overlayCampInfoDisplayEnquiriesCommittee.getDestroy() != true){
            overlayCampInfoDisplayEnquiriesCommittee.onWindowFinished(chose, choseString);
        }
        if(overlayCampAllSuggestionView != null && overlayCampAllSuggestionView.getDestroy() != true){
            overlayCampAllSuggestionView.onWindowFinished(chose, choseString);
        }
        if(chose == 254){

        }
    }

}
