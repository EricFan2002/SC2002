package ui.account;

import controller.user.UserAccountManagementController;
import controller.user.UserController;
import entity.user.Staff;
import entity.user.Student;
import ui.overlayactions.OverlayChooseBox;
import ui.widgets.*;
import ui.windows.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * The ChangePasswordView class represents the UI window for changing a user's password.
 * It extends the Window class and provides functionality for password change.
 */
public class ChangePasswordView extends Window {
    WidgetLabel widgetLabel1;
    WidgetLabel widgetLabel2;
    WidgetLabel widgetLabel3;
    WidgetLabel widgetLabel4;
    WidgetTextBox widgetTextBox1;
    WidgetPasswordBox widgetTextBox2;
    WidgetPasswordBox widgetTextBox3;
    WidgetPasswordBox widgetTextBox4;
    WidgetButton button;
    WidgetToggle toggle;
    WidgetButton confirmButton;
    WidgetButton cancelButton;
    private int loginView;
    private int studentMainViewIndex;
    private int staffMainViewIndex;
    public ChangePasswordView(int loginView, int studentMainViewIndex, int staffMainViewIndex){
        super(24, 60, "Password Change");
        WidgetLabel widgetLabel = new WidgetLabel(3, 3,40, "Change Your Password", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        widgetLabel1 = new WidgetLabel(1, 5,19, "User:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel1);
        widgetTextBox1 = new WidgetTextBox(21, 5,29, "");
        addWidget(widgetTextBox1);
        widgetLabel2 = new WidgetLabel(1, 7,19, "Current Password:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel2);
        widgetTextBox2 = new WidgetPasswordBox(21, 7,29, "");
        addWidget(widgetTextBox2);
        widgetLabel3 = new WidgetLabel(1, 9,19, "New Password:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel3);
        widgetTextBox3 = new WidgetPasswordBox(21, 9,29, "");
        addWidget(widgetTextBox3);
        widgetLabel4 = new WidgetLabel(1, 11,19, "Confirm Password:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(widgetLabel4);
        widgetTextBox4 = new WidgetPasswordBox(21, 11,29, "");
        addWidget(widgetTextBox4);
        confirmButton = new WidgetButton(2, 15, 49, "Confirm");
        addWidget(confirmButton);
        cancelButton = new WidgetButton(2, 17, 49, "Back");
        addWidget(cancelButton);
        setPointer(confirmButton);
        this.loginView = loginView;
        this.studentMainViewIndex = studentMainViewIndex;
        this.staffMainViewIndex = staffMainViewIndex;
    }
    /**
     * Checks if a character is a special character.
     *
     * @param ch The character to be checked.
     * @return true if the character is a special character, otherwise false.
     */
    private boolean isSpecialCharacter(char ch) {
        String specialCharacters = "!@#$%^&*()_-+={}[]|:;\"'<>,.?/";
        return specialCharacters.indexOf(ch) != -1;
    }

    /**
     * The main message loop handling user interactions within the ChangePasswordView.
     * Checks for button presses and performs password change logic based on user input.
     */
    @Override
    public void messageLoop() {
        super.messageLoop();
        if(confirmButton.getPressed()){
            confirmButton.clearPressed();
            String name = widgetTextBox1.getText();
            String password = widgetTextBox2.getText();
            String newPassword = widgetTextBox3.getText();
            String confirmPassword = widgetTextBox4.getText(); // Assuming widgetTextBox3 is for confirmPassword

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
                            OverlayChooseBox overlayChooseBox = new OverlayChooseBox(60, 19 / 2 , 0, "Error changing the password", options, ChangePasswordView.this);
                            addOverlay(overlayChooseBox);
                        }
                    } else {
                        List<String> options = new ArrayList<String>();
                        options.add("OK");
                        OverlayChooseBox overlayChooseBox = new OverlayChooseBox(60, 19 / 2, 0, "Must contain upper, lower, digit, special char", options, ChangePasswordView.this);
                        addOverlay(overlayChooseBox);
                    }
                } else {
                    if (newPassword.length() < 8 || newPassword.length() > 20) {
                        List<String> options = new ArrayList<String>();
                        options.add("OK");
                        OverlayChooseBox overlayChooseBox = new OverlayChooseBox(60, 19 / 2, 0, "Password must be between 8 and 20 characters", options, ChangePasswordView.this);
                        addOverlay(overlayChooseBox);
                    } else {
                        List<String> options = new ArrayList<String>();
                        options.add("OK");
                        OverlayChooseBox overlayChooseBox = new OverlayChooseBox(60, 19 / 2, 0, "New password and confirm password do not match", options, ChangePasswordView.this);
                        addOverlay(overlayChooseBox);
                    }
                }
            } else {
                // if incorrect, show error message
                List<String> options = new ArrayList<String>();
                options.add("OK");
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(60, 19 / 2, 0, "Incorrect username or password", options, ChangePasswordView.this);
                addOverlay(overlayChooseBox);
            }
        }
        if(cancelButton.getPressed()){
            cancelButton.clearPressed();
            if (UserController.getCurrentUser().getPassword().equals("password")) {
                switchToWindow = loginView;
            } else if (UserController.getCurrentUser() instanceof Staff) {
                switchToWindow = staffMainViewIndex;
            } else if (UserController.getCurrentUser() instanceof Student){
                switchToWindow = studentMainViewIndex;
            } else {
                switchToWindow = loginView;
            }
        }
    }

    /**
     * Performs necessary clean-up tasks upon exiting the ChangePasswordView.
     * Clears button presses to avoid unintended behavior.
     */
    public void onExit(){
        confirmButton.clearPressed();
        cancelButton.clearPressed();
    }
}
