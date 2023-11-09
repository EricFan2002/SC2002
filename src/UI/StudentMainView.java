package UI;

public class StudentMainView extends Window {
    String userName = "NULL";
    WidgetLabel widgetLabel;
    WidgetButton viewCampButton;
    WidgetButton registerForCampButton;
    WidgetButton viewEnquiresButton;
    WidgetButton changePassword;
    WidgetButton logoutButton;
    private int loginSwitchToWindowIndex;
    public StudentMainView(int loginSwitchToWindowIndex){
        super(24, 50, "Landing Page");
        widgetLabel = new WidgetLabel(3, 3,40, "Welcome! " + userName, TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        this.loginSwitchToWindowIndex = loginSwitchToWindowIndex;
        viewCampButton = new WidgetButton(4, 7, 40, "Camp Management");
        addWidget(viewCampButton);
        registerForCampButton = new WidgetButton(4, 11, 40, "Register For Camp");
        addWidget(registerForCampButton);
        viewEnquiresButton = new WidgetButton(4, 13, 40, "View My Enquires");
        addWidget(viewEnquiresButton);
        changePassword = new WidgetButton(4, 15, 40, "Reset Password");
        addWidget(changePassword);
        logoutButton = new WidgetButton(4, 17, 40, "Logout");
        addWidget(logoutButton);
    }

    public void setUserName(String userName){
        this.userName = userName;
        widgetLabel.setText("Welcome! " + userName);
    }

    public void messageLoop() {
        super.messageLoop();
        if(logoutButton.getPressed()){
            switchToWindow = loginSwitchToWindowIndex;
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
