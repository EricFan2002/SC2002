package ui;

import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.util.ArrayList;
import java.util.List;

public class LoginController extends Window implements ICallBack {
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

    @Override
    public void messageLoop() {
        if(loginButton.getPressed()){
            switchToWindow = loginSwitchToWindowIndex;
        }
        if(changePasswordButton.getPressed()){
            switchToWindow = changePasswordWindowIndex;
        }
        if(forgetPasswordButton.getPressed()){
            List<String> options = new ArrayList<String>();
            options.add("SCSE");
            options.add("NBS");
            options.add("MAE");
            options.add("SSS");
            options.add("MSE");
            options.add("SBS");
            options.add("CEE");
            OverlayChooseBox overlayTestClass = new OverlayChooseBox(26,  forgetPasswordButton.getY(), forgetPasswordButton.getX(), "Choose School", options, LoginController.this);
            addOverlay(overlayTestClass);
            forgetPasswordButton.clearPressed();
        }
    }
    public void onExit(){
        loginButton.clearPressed();
        changePasswordButton.clearPressed();
        forgetPasswordButton.clearPressed();
    }

    @Override
    public void onWindowFinished(int chose, String choseString) {
        forgetPasswordButton.setText(choseString);
    }
}
