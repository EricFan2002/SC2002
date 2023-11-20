package ui;

import controller.camp.CampRegistrationController;
import controller.camp.OperationResult;
import controller.user.UserController;
import entity.camp.Camp;
import entity.user.Student;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetToggle;

public class CampListViewStudent extends CampListView{
    protected WidgetToggle toggleJoinedCamp = new WidgetToggle(1, 7, getLenX() / 4 - 1, "Joined Camps");
    protected WidgetToggle toggleCommittee = new WidgetToggle(getLenX() / 4 + 1, 7, getLenX() / 4 - 1, "As Committee");
    protected WidgetToggle toggleAvailable = new WidgetToggle(1, 8, getLenX() / 4 - 1, "Available As Participant");
    protected WidgetToggle toggleCommitteeAvailableC = new WidgetToggle(getLenX() / 4 + 1, 8, getLenX() / 4 - 1, "Available As Committee");
    protected Camp selectedCamp;
    public CampListViewStudent(int loginSwitchToWindowIndex, int changePasswordWindowIndex, int forgotPasswordWindowIndex) {
        super(loginSwitchToWindowIndex, changePasswordWindowIndex, forgotPasswordWindowIndex);
        addWidgetAfter(toggleCommitteeAvailableC, filter4Index);
        addWidgetAfter(toggleAvailable, filter4Index);
        addWidgetAfter(toggleCommittee, filter4Index);
        addWidgetAfter(toggleJoinedCamp, filter4Index);
    }

    @Override
    public void messageLoop() {
        super.messageLoop();
        if(widgetPageSelection.getSelectedOption() != -1){
            selectedCamp = displayCamps.get(widgetPageSelection.getSelectedOption());
            WidgetButton button = widgetPageSelection.getSelectionsButton().get(widgetPageSelection.getSelectedOption()).get(0);
            button.clearPressed();
            OverlayCampListViewStudentCampActions overlayCampListViewStudentCampActions = new OverlayCampListViewStudentCampActions(50,  button.getY(), button.getX() + (getLenX() / 4 - 25), "Actions", CampListViewStudent.this);
            addOverlay(overlayCampListViewStudentCampActions);
            widgetPageSelection.clearSelectedOption();
        }
        if(chose == 0 && choseString.equals("View Details")){ // view details
            OverlayCampInfoDisplay overlayCampInfoDisplay = new OverlayCampInfoDisplay(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
        }
        else if(chose == 0 && choseString.equals("CFM")){ // view details
            OverlayCampInfoDisplay overlayCampInfoDisplay = new OverlayCampInfoDisplay(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
        }
        else if(chose == 1 && choseString.equals("Join Camp")){ // join
            if(UserController.getCurrentUser() instanceof Student) {
                Student student = (Student)UserController.getCurrentUser();
                OperationResult result = CampRegistrationController.CampRegistrationService.registerCamp(selectedCamp, student);
                OverlayNotification overlayNotification = new OverlayNotification(40,  getY()/2 - 8, getX()/2 - 20, "Info", result.getComment(), CampListViewStudent.this);
                addOverlay(overlayNotification);
            }
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
    }

}
