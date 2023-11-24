package ui;

import controller.user.UserController;
import entity.RepositoryCollection;
import entity.user.Student;
import ui.widgets.TEXT_ALIGNMENT;
import ui.widgets.Widget;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetLabel;
import ui.windows.Window;

public class StudentMainView extends Window {
    String userName = "NULL";
    WidgetLabel widgetLabel;
    WidgetLabel facultyLabel;
    WidgetLabel pointLabel;
    WidgetLabel committeeLabel;
    WidgetLabel greetingLabel;
    WidgetButton viewCampButton;
    WidgetButton registerForCampButton;
    WidgetButton viewEnquiryButton;
    WidgetButton changePasswordButton;
    WidgetButton logoutButton;
    private int campListViewIndex;
    private int loginViewIndex;
    private int changePasswordWindowIndex;
    public StudentMainView(int loginViewIndex, int campListViewIndex, int changePasswordWindowIndex){
        super(24, 60, "Landing Page");
        widgetLabel = new WidgetLabel(3, 3,50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        facultyLabel = new WidgetLabel(3, 5,50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(facultyLabel);
        pointLabel = new WidgetLabel(3, 7,50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(pointLabel);
        committeeLabel = new WidgetLabel(3, 9,50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(committeeLabel);
        greetingLabel = new WidgetLabel(3, 11,50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(greetingLabel);
        viewCampButton = new WidgetButton(4, 14, 50, "Camp Management");
        addWidget(viewCampButton);
        changePasswordButton = new WidgetButton(4, 17, 50, "Reset Password");
        addWidget(changePasswordButton);
        logoutButton = new WidgetButton(4, 20, 50, "Logout");
        addWidget(logoutButton);
        this.loginViewIndex = loginViewIndex;
        this.campListViewIndex = campListViewIndex;
        this.changePasswordWindowIndex = changePasswordWindowIndex;
    }

    public void setUserName(String userName){
        this.userName = userName;
        widgetLabel.setText("Welcome! " + userName);
    }

    public void messageLoop() {
        super.messageLoop();
        if(UserController.getCurrentUser() != null){
            userName = UserController.getCurrentUser().getName();
            widgetLabel.setText("Welcome, " + userName + "!");
            facultyLabel.setText("Faculty: " + UserController.getCurrentUser().getFaculty());
            pointLabel.setText(((Student) UserController.getCurrentUser()).getCommitteeCampList().isEmpty() ? "" : "You have " + (((Student) UserController.getCurrentUser()).getPoints()) + " points as a committee member of");
            committeeLabel.setText(((Student) UserController.getCurrentUser()).getCommitteeCampList().isEmpty() ? "" : ((Student) UserController.getCurrentUser()).getCommitteeCampList().get(0).getName());
            greetingLabel.setText("What would you like to do today?");
        }
        if(logoutButton.getPressed()){
            switchToWindow = loginViewIndex;
        }
        if(changePasswordButton.getPressed()){
            switchToWindow = changePasswordWindowIndex;
        }
        if(viewCampButton.getPressed()){
            switchToWindow = campListViewIndex;
        }
    }
    public void onExit(){
        for(Widget widget : widgets){
            if(widget instanceof WidgetButton){
                ((WidgetButton)widget).clearPressed();
            }
        }
    }
}
