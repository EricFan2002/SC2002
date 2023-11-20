package ui;

import entity.camp.Camp;
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
        if(chose == 0){ // view details
            OverlayCampInfoDisplay overlayCampInfoDisplay = new OverlayCampInfoDisplay(getLenX() / 2 - 2, getY(),1, getLenX() / 2 + 2, "Camp Details", selectedCamp);
            addOverlay(overlayCampInfoDisplay);
            chose = -1;
        }
    }
//        options.add("View Details");
//        options.add("Join Camp");
//        options.add("Quit Camp");
//        options.add("Enquiry");
//        options.add("Reply Enquiry");
//        options.add("Create Suggestion");
    private int chose = -1;
    @Override
    public void onWindowFinished(int chose, String choseString) {
        this.chose = chose;
    }

}
