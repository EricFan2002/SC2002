package ui;

import controller.user.UserAccountManagementController;
import controller.user.UserController;
import ui.widgets.*;
import ui.windows.Window;

import java.util.ArrayList;
import java.util.List;

public class ChangePasswordView extends Window {
    WidgetLabel widgetLabel1;
    WidgetTextBox widgetTextBox;
    WidgetLabel widgetLabel2;
    WidgetLabel widgetLabel3;
    WidgetTextBox widgetTextBox1;
    WidgetTextBox widgetTextBox2;
    WidgetButton button;
    WidgetToggle toggle;
    WidgetButton confirmButton;
    WidgetButton cancelButton;
    private int loginSwitchToWindowIndex;
    public ChangePasswordView(int loginSwitchToWindowIndex){
        super(20, 55, "Password Change");
        WidgetLabel widgetLabel = new WidgetLabel(3, 3,40, "Change Your Password", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        widgetLabel1 = new WidgetLabel(1, 5,19, "User:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel1);
        widgetTextBox = new WidgetTextBox(21, 5,29, "");
        addWidget(widgetTextBox);
        widgetLabel2 = new WidgetLabel(1, 7,19, "Current Password:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel2);
        widgetTextBox1 = new WidgetTextBox(21, 7,29, "");
        addWidget(widgetTextBox1);
        widgetLabel3 = new WidgetLabel(1, 9,19, "New Password:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel3);
        widgetTextBox2 = new WidgetTextBox(21, 9,29, "");
        addWidget(widgetTextBox2);
        confirmButton = new WidgetButton(2, 12, 49, "Confirm");
        addWidget(confirmButton);
        cancelButton = new WidgetButton(2, 14, 49, "Back");
        addWidget(cancelButton);
        setPointer(confirmButton);
        this.loginSwitchToWindowIndex = loginSwitchToWindowIndex;
    }

    public void messageLoop() {
        super.messageLoop();
        if(confirmButton.getPressed()){
            String name = widgetTextBox.getText();
            String password = widgetTextBox1.getText();
            String newPassword = widgetTextBox2.getText();
            String confirmPassword = widgetTextBox2.getText();
            // check if name and password is correct
            if(UserController.login(name, password)){
                // if correct, switch to main menu
                if(newPassword.equals(confirmPassword)){
                    if (UserAccountManagementController.changePassword(name, password, newPassword)) {
                        switchToWindow = loginSwitchToWindowIndex;
                    } else {
                        List<String> options = new ArrayList<String>();
                        options.add("OK");
                        OverlayChooseBox overlayChooseBox = new OverlayChooseBox(10, 10, 30, "Error changing the password", options, ChangePasswordView.this);
                        overlayChooseBox.messageLoop();
                    }
                } else {
                    List<String> options = new ArrayList<String>();
                    options.add("OK");
                    OverlayChooseBox overlayChooseBox = new OverlayChooseBox(10, 10, 30, "New password and confirm password do not match", options, ChangePasswordView.this);
                    overlayChooseBox.messageLoop();
                }
            } else {
                // if incorrect, show error message
                List<String> options = new ArrayList<String>();
                options.add("OK");
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(10, 10, 30, "Incorrect username or password", options, ChangePasswordView.this);
                overlayChooseBox.messageLoop();
            }
        }
        if(cancelButton.getPressed()){
            switchToWindow = loginSwitchToWindowIndex;
        }
    }
    public void onExit(){
        confirmButton.clearPressed();
        cancelButton.clearPressed();
    }
}
