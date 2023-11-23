package ui;

import controller.camp.CampRegistrationController;
import controller.camp.OperationResult;
import controller.user.UserController;
import entity.camp.Camp;
import entity.user.Staff;
import entity.user.Student;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetToggle;

import java.util.ArrayList;

public class CampListViewStudent extends CampListView{
    protected WidgetToggle toggleJoinedCamp = new WidgetToggle(1, 7, getLenX() / 4 - 1, "Joined Camps");
    protected WidgetToggle toggleCommittee = new WidgetToggle(getLenX() / 4 + 1, 7, getLenX() / 4 - 1, "As Committee");
    protected WidgetToggle toggleAvailable = new WidgetToggle(1, 8, getLenX() / 4 - 1, "Available As Participant");
    protected WidgetToggle toggleCommitteeAvailableC = new WidgetToggle(getLenX() / 4 + 1, 8, getLenX() / 4 - 1, "Available As Committee");
    protected Camp selectedCamp;
    protected OverlayCampInfoDisplayEnquiries overlayCampInfoDisplayEnquiries;
    protected OverlayCampInfoDisplayEnquiriesCommittee overlayCampInfoDisplayEnquiriesCommittee;

    protected OverlayCampAllSuggestionView overlayCampAllSuggestionView;
    private int studentMainViewIndex;
    public CampListViewStudent(int studentMainViewIndex) {
        super();
        this.studentMainViewIndex = studentMainViewIndex;
        addWidgetAfter(toggleCommitteeAvailableC, filter4Index);
        addWidgetAfter(toggleAvailable, filter4Index);
        addWidgetAfter(toggleCommittee, filter4Index);
        addWidgetAfter(toggleJoinedCamp, filter4Index);
    }

    private WidgetButton buttonPosition;

    @Override
    public void messageLoop() {
        super.messageLoop();
        if(widgetPageSelection.getSelectedOption() != -1){
            selectedCamp = displayCamps.get(widgetPageSelection.getSelectedOption());
            buttonPosition = widgetPageSelection.getSelectionsButton().get(widgetPageSelection.getSelectedOption()).get(0);
            buttonPosition.clearPressed();
            OverlayCampListViewStudentCampActions overlayCampListViewStudentCampActions = new OverlayCampListViewStudentCampActions(50,  buttonPosition.getY(), buttonPosition.getX() + (getLenX() / 4 - 25), "Actions", CampListViewStudent.this);
            addOverlay(overlayCampListViewStudentCampActions);
            widgetPageSelection.clearSelectedOption();
        }
        if(chose == 0 && choseString.equals("View Details")){ // view details
            OverlayCampInfoDisplayStudentView overlayCampInfoDisplay = new OverlayCampInfoDisplayStudentView(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
            choseString = "";
        }
        else if(chose == 0 && choseString.equals("CFM")){ // view details
            OverlayCampInfoDisplayStudentView overlayCampInfoDisplay = new OverlayCampInfoDisplayStudentView(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
            choseString = "";
        }
        else if(chose == 1 && choseString.equals("Join Camp")){ // join
            if(UserController.getCurrentUser() instanceof Student) {
                ArrayList<String> options = new ArrayList<>();
                options.add("Join As Participant");
                options.add("Join As Committee");
                options.add("Cancel");
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(), buttonPosition.getX() + (getLenX() / 4 - 15), "Join As?", options, CampListViewStudent.this);
                addOverlay(overlayChooseBox);
            }
            chose = -1;
            choseString = "";
        }
        else if(choseString.equals("Join As Participant")){ // join
            if(UserController.getCurrentUser() instanceof Student) {
                Student student = (Student)UserController.getCurrentUser();
                OperationResult result = CampRegistrationController.registerCamp(selectedCamp, student);
                OverlayNotification overlayNotification = new OverlayNotification(40,  getY()/2 - 8, getX()/2 - 20, "Info", result.getComment(), CampListViewStudent.this);
                addOverlay(overlayNotification);
            }
            chose = -1;
            choseString = "";
        }
        else if(choseString.equals("Join As Committee")){ // join
            if(UserController.getCurrentUser() instanceof Student) {
                Student student = (Student)UserController.getCurrentUser();
                OperationResult result = CampRegistrationController.registerCampAsCommittee(selectedCamp, student);
                OverlayNotification overlayNotification = new OverlayNotification(40,  getY()/2 - 8, getX()/2 - 20, "Info", result.getComment(), CampListViewStudent.this);
                addOverlay(overlayNotification);
            }
            chose = -1;
            choseString = "";
        }
        else if(chose == 3 && choseString.equals("Enquiry")){ // Enquiry
            if(UserController.getCurrentUser() instanceof Student) {
                Student student = (Student)UserController.getCurrentUser();
                overlayCampInfoDisplayEnquiries = new OverlayCampInfoDisplayEnquiries(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp, student, CampListViewStudent.this);
                addOverlay(overlayCampInfoDisplayEnquiries);
            }
            chose = -1;
            choseString = "";
        }
        else if(chose == 4 && choseString.equals("Reply Enquiry")){ // Enquiry
            if(UserController.getCurrentUser() instanceof Student) {
                Student student = (Student)UserController.getCurrentUser();
                overlayCampInfoDisplayEnquiriesCommittee = new OverlayCampInfoDisplayEnquiriesCommittee(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp, student, CampListViewStudent.this);
                addOverlay(overlayCampInfoDisplayEnquiriesCommittee);
            }
            chose = -1;
            choseString = "";
        }
        else if(chose == 5 && choseString.equals("Suggestions")){ // Create Suggestion
            if(UserController.getCurrentUser() instanceof Student) {
                Student student = (Student)UserController.getCurrentUser();
                overlayCampAllSuggestionView = new OverlayCampAllSuggestionView(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Create Suggestion To Camp", selectedCamp, student, CampListViewStudent.this);
                addOverlay(overlayCampAllSuggestionView);
            }
            chose = -1;
            choseString = "";
        }
        if(backButton.getPressed()){
            System.out.println("Back button pressed in CampListView");
            backButton.clearPressed();
            switchToWindow = studentMainViewIndex;
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
    }

}
