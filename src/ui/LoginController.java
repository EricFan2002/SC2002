package ui;

import ui.widgets.*;
import ui.windows.Window;

public class LoginController extends Window {
    WidgetLabel widgetLabel;
    WidgetLabel widgetLabel1;
    WidgetTextBox widgetTextBox;
    WidgetLabel widgetLabel2;
    WidgetTextBox widgetTextBox1;
    WidgetButton loginButton;
    WidgetButton changePasswordButton;
    WidgetButton forgetPasswordButton;
    WidgetToggle toggle;
    private int loginSwitchToWindowIndex;
    private int changePasswordWindowIndex;
    private int forgotPasswordWindowIndex;
    public LoginController(int y, int x, int loginSwitchToWindowIndex, int changePasswordWindowIndex, int forgotPasswordWindowIndex){
        super(y, x, "Please Login");
        WidgetLabel widgetLabel = new WidgetLabel(3, 3,40, "Please Login:", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        widgetLabel1 = new WidgetLabel(2, 5,9, "User:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel1);
        widgetTextBox = new WidgetTextBox(14, 5,30, "");
        addWidget(widgetTextBox);
        widgetLabel2 = new WidgetLabel(2, 7,9, "Password:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel2);
        widgetTextBox1 = new WidgetTextBox(14, 7,30, "");
        addWidget(widgetTextBox1);
        toggle = new WidgetToggle(5, 10, 40, "Save Password?");
        addWidget(toggle);
        loginButton = new WidgetButton(5, 12, 40, "Login");
        addWidget(loginButton);
        changePasswordButton = new WidgetButton(5, 14, 19, "Change Password");
        addWidget(changePasswordButton);
        forgetPasswordButton = new WidgetButton(5 + 20 , 14, 20, "Forgot Password");
        addWidget(forgetPasswordButton);
        setPointer(loginButton);
        this.loginSwitchToWindowIndex = loginSwitchToWindowIndex;
        this.changePasswordWindowIndex = changePasswordWindowIndex;
        this.forgotPasswordWindowIndex = forgotPasswordWindowIndex;
    }

    public void messageLoop() {
        super.messageLoop();
        if(loginButton.getPressed()){
            switchToWindow = loginSwitchToWindowIndex;
        }
        if(changePasswordButton.getPressed()){
            switchToWindow = changePasswordWindowIndex;
        }
        if(forgetPasswordButton.getPressed()){
//            switchToWindow = forgotPasswordWindowIndex;
            OverlayTestClass overlayTestClass = new OverlayTestClass(getY(), getX(), 10, 10, "OverlayTest");
            addOverlay(overlayTestClass);
            forgetPasswordButton.clearPressed();
        }
    }
    public void onExit(){
        loginButton.clearPressed();
        changePasswordButton.clearPressed();
        forgetPasswordButton.clearPressed();
    }
}