package ui;

import controller.user.UserController;
import entity.RepositoryCollection;
import ui.widgets.TEXT_ALIGNMENT;
import ui.widgets.Widget;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetLabel;
import ui.windows.Window;

public class StudentMainView extends Window {
    String userName = "NULL";
    WidgetLabel widgetLabel;
    WidgetButton viewCampButton;
    WidgetButton registerForCampButton;
    WidgetButton viewEnquiryButton;
    WidgetButton changePasswordButton;
    WidgetButton logoutButton;
    private int campListViewIndex;
    private int loginViewIndex;
    private int changePasswordWindowIndex;
    public StudentMainView(int loginViewIndex, int campListViewIndex, int changePasswordWindowIndex){
        super(24, 50, "Landing Page");
        String userName = "";
        if(UserController.getCurrentUser() != null){
            userName = UserController.getCurrentUser().getName();
        }
        widgetLabel = new WidgetLabel(3, 3,40, "Welcome! " + userName, TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        viewCampButton = new WidgetButton(4, 7, 40, "Camp Management");
        addWidget(viewCampButton);
        changePasswordButton = new WidgetButton(4, 15, 40, "Reset Password");
        addWidget(changePasswordButton);
        logoutButton = new WidgetButton(4, 17, 40, "Logout");
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
            widgetLabel.setText("Welcome! " + userName);
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
