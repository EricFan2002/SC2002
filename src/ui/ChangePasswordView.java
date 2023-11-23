package ui;

import controller.user.UserAccountManagementController;
import controller.user.UserController;
import entity.user.Staff;
import entity.user.Student;
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
    private int loginView;
    private int studentMainViewIndex;
    private int staffMainViewIndex;
    public ChangePasswordView(int loginView, int studentMainViewIndex, int staffMainViewIndex){
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
        this.loginView = loginView;
        this.studentMainViewIndex = studentMainViewIndex;
        this.staffMainViewIndex = staffMainViewIndex;
    }

    private boolean isSpecialCharacter(char ch) {
        String specialCharacters = "!@#$%^&*()_-+={}[]|:;\"'<>,.?/";
        return specialCharacters.indexOf(ch) != -1;
    }

    public void messageLoop() {
        super.messageLoop();
        if(confirmButton.getPressed()){
            confirmButton.clearPressed();
            String name = widgetTextBox.getText();
            String password = widgetTextBox1.getText();
            String newPassword = widgetTextBox2.getText();
            String confirmPassword = widgetTextBox2.getText(); // Assuming widgetTextBox3 is for confirmPassword

            // check if name and password is correct
            if(UserController.login(name, password)){
                // if correct, perform password change logic
                if(newPassword.equals(confirmPassword) && newPassword.length() >= 8 && newPassword.length() <= 20){
                    boolean hasUpper = false;
                    boolean hasLower = false;
                    boolean hasDigit = false;
                    boolean hasSpecial = false;

                    for (int i = 0; i < newPassword.length(); i++) {
                        char ch = newPassword.charAt(i);

                        if (Character.isUpperCase(ch)) hasUpper = true;
                        else if (Character.isLowerCase(ch)) hasLower = true;
                        else if (Character.isDigit(ch)) hasDigit = true;
                        else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;

                        if (hasUpper && hasLower && hasDigit && hasSpecial) break;
                    }

                    if (hasUpper && hasLower && hasDigit && hasSpecial) {
                        if (UserAccountManagementController.changePassword(name, password, newPassword)) {
                            switchToWindow = loginView;
                        } else {
                            List<String> options = new ArrayList<String>();
                            options.add("OK");
                            OverlayChooseBox overlayChooseBox = new OverlayChooseBox(10, 10, 30, "Error changing the password", options, ChangePasswordView.this);
                            addOverlay(overlayChooseBox);
                        }
                    } else {
                        List<String> options = new ArrayList<String>();
                        options.add("OK");
                        OverlayChooseBox overlayChooseBox = new OverlayChooseBox(10, 10, 30, "Password must contain upper, lower, digit, and special char", options, ChangePasswordView.this);
                        addOverlay(overlayChooseBox);
                    }
                } else {
                    List<String> options = new ArrayList<String>();
                    options.add("OK");
                    OverlayChooseBox overlayChooseBox = new OverlayChooseBox(10, 10, 30, "New password and confirm password do not match or are not within the length limits", options, ChangePasswordView.this);
                    addOverlay(overlayChooseBox);
                }
            } else {
                // if incorrect, show error message
                List<String> options = new ArrayList<String>();
                options.add("OK");
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(10, 10, 30, "Incorrect username or password", options, ChangePasswordView.this);
                addOverlay(overlayChooseBox);
            }
        }
        if(cancelButton.getPressed()){
            cancelButton.clearPressed();
            if (UserController.getCurrentUser() instanceof Staff) {
                switchToWindow = staffMainViewIndex;
            } else if (UserController.getCurrentUser() instanceof Student){
                switchToWindow = studentMainViewIndex;
            } else {
                switchToWindow = loginView;
            }
        }
    }
    public void onExit(){
        confirmButton.clearPressed();
        cancelButton.clearPressed();
    }
}
