package ui.AccountView;

import controller.user.UserController;
import entity.user.Staff;
import entity.user.Student;
import ui.OverlayActions.OverlayChooseBox;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.util.ArrayList;
import java.util.List;

public class LoginView extends Window implements ICallBack { ;
    WidgetLabel widgetLabel;
    WidgetLabel widgetLabel1;
    WidgetTextBox widgetTextBox;
    WidgetLabel widgetLabel2;
    WidgetPasswordBox widgetTextBox1;
    WidgetButton loginButton;
    WidgetButton changePasswordButton;
    WidgetButton forgetPasswordButton;
    WidgetToggle toggle;
    private int studentMainViewIndex;
    private int staffMainViewIndex;
    private int changePasswordWindowIndex;
    private int forgotPasswordWindowIndex;
    public LoginView(int y, int x, int studentMainViewIndex, int staffMainViewIndex, int changePasswordWindowIndex, int forgotPasswordWindowIndex){
        super(y, x, "Please Login");
        WidgetLabel widgetLabel = new WidgetLabel(3, 4,40, "Please Login:", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        widgetLabel1 = new WidgetLabel(2, 6,9, "User:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel1);
        widgetTextBox = new WidgetTextBox(14, 6,30, "staff");
        addWidget(widgetTextBox);
        widgetLabel2 = new WidgetLabel(2, 8,9, "Password:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel2);
        widgetTextBox1 = new WidgetPasswordBox(14, 8,30, "password");
        addWidget(widgetTextBox1);
        loginButton = new WidgetButton(5, 11, 40, "Login");
        addWidget(loginButton);
        forgetPasswordButton = new WidgetButton(5 , 14, 40, "Forgot Password");
        addWidget(forgetPasswordButton);
        setPointer(loginButton);
        this.studentMainViewIndex = studentMainViewIndex;
        this.staffMainViewIndex = staffMainViewIndex;
        this.changePasswordWindowIndex = changePasswordWindowIndex;
        this.forgotPasswordWindowIndex = forgotPasswordWindowIndex;
    }

    @Override
    public void messageLoop() {
        if(loginButton.getPressed()){
            loginButton.clearPressed();
            String name = widgetTextBox.getText();
            String password = widgetTextBox1.getText();
            // check if name and password is correct
            if(UserController.login(name, password)){
                // if default password, switch to change password
                if (UserController.getCurrentUser().getPassword().equals("password")) {
                    switchToWindow = changePasswordWindowIndex;
                }
                // if correct, switch to main menu
                else if (UserController.getCurrentUser() instanceof Student) {
                    switchToWindow = studentMainViewIndex;
                } else if (UserController.getCurrentUser() instanceof Staff){
                    switchToWindow = staffMainViewIndex;
                }
            } else {
                // if incorrect, show error message
                List<String> options = new ArrayList<String>();
                options.add("OK");
                OverlayChooseBox overlayTestClass = new OverlayChooseBox(54,  getY()/2 - 3, 0, "Wrong username or password", options, LoginView.this);
                addOverlay(overlayTestClass);
            }
        }
        if(forgetPasswordButton.getPressed()){
            forgetPasswordButton.clearPressed();
            switchToWindow = staffMainViewIndex;
        }
    }
    public void onExit(){
        loginButton.clearPressed();
        forgetPasswordButton.clearPressed();
    }

    @Override
    public void onWindowFinished(int chose, String choseString) {}
}
