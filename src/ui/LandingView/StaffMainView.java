package ui.LandingView;

import controller.user.UserController;
import ui.widgets.TEXT_ALIGNMENT;
import ui.widgets.Widget;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetLabel;
import ui.windows.Window;

public class StaffMainView extends Window {
    String userName = "NULL";
    WidgetLabel widgetLabel;
    WidgetLabel facultyLabel;
    WidgetLabel greetingLabel;
    WidgetButton viewCampButton;
    WidgetButton registerForCampButton;
    WidgetButton viewEnquiryButton;
    WidgetButton changePasswordButton;
    WidgetButton logoutButton;
    private int campListViewIndex;
    private int loginViewIndex;
    private int changePasswordWindowIndex;
    public StaffMainView(int loginViewIndex, int campListViewIndex, int changePasswordWindowIndex){
        super(24, 50, "Landing Page");
        widgetLabel = new WidgetLabel(3, 3,40, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        facultyLabel = new WidgetLabel(3, 5,40, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(facultyLabel);
        greetingLabel = new WidgetLabel(3, 9,40, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(greetingLabel);
        viewCampButton = new WidgetButton(4, 14, 40, "Camp Management");
        addWidget(viewCampButton);
//        registerForCampButton = new WidgetButton(4, 11, 40, "Register For Camp");
//        addWidget(registerForCampButton);
//        viewEnquiryButton = new WidgetButton(4, 13, 40, "View My Enquires");
//        addWidget(viewEnquiryButton);
        changePasswordButton = new WidgetButton(4, 17, 40, "Reset Password");
        addWidget(changePasswordButton);
        logoutButton = new WidgetButton(4, 20, 40, "Logout");
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
