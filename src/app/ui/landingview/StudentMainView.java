package app.ui.landingview;

import app.controller.user.UserController;
import app.entity.user.Student;
import app.ui.widgets.TEXT_ALIGNMENT;
import app.ui.widgets.Widget;
import app.ui.widgets.WidgetButton;
import app.ui.widgets.WidgetLabel;
import app.ui.windows.Window;

/**
 * The {@code StudentMainView} class represents the main landing page for a student user. It extends the {@code Window}
 * class and provides functionalities such as viewing camp details, registering for camps, viewing enquiries, changing
 * password, and logging out.
 */
public class StudentMainView extends Window {
    /**
     * The user name displayed on the main view.
     */
    String userName = "NULL";

    /**
     * The label widget for displaying the user name.
     */
    WidgetLabel widgetLabel;

    /**
     * The label widget for displaying the faculty information.
     */
    WidgetLabel facultyLabel;

    /**
     * The label widget for displaying the points information.
     */
    WidgetLabel pointLabel;
    WidgetLabel campLabel;

    /**
     * The label widget for displaying a greeting message.
     */
    WidgetLabel greetingLabel;

    /**
     * The button widget for viewing camp details.
     */
    WidgetButton viewCampButton;

    /**
     * The button widget for registering for camps.
     */
    WidgetButton registerForCampButton;

    /**
     * The button widget for viewing enquiries.
     */
    WidgetButton viewEnquiryButton;

    /**
     * The button widget for changing the password.
     */
    WidgetButton changePasswordButton;

    /**
     * The button widget for logging out.
     */
    WidgetButton logoutButton;

    /**
     * The index of the camp list view window.
     */
    private int campListViewIndex;

    /**
     * The index of the login view window.
     */
    private int loginViewIndex;

    /**
     * The index of the change password window.
     */
    private int changePasswordWindowIndex;

    /**
     * Constructs an instance of {@code StudentMainView}.
     *
     * @param loginViewIndex        The index of the login view window.
     * @param campListViewIndex     The index of the camp list view window.
     * @param changePasswordWindowIndex The index of the change password window.
     */
    public StudentMainView(int loginViewIndex, int campListViewIndex, int changePasswordWindowIndex) {
        super(24, 60, "Landing Page");
        widgetLabel = new WidgetLabel(3, 3, 50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        facultyLabel = new WidgetLabel(3, 5, 50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(facultyLabel);
        pointLabel = new WidgetLabel(3, 7, 50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(pointLabel);
        campLabel = new WidgetLabel(3, 9, 50, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(campLabel);
        greetingLabel = new WidgetLabel(3, 11, 50, "", TEXT_ALIGNMENT.ALIGN_MID);
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

    /**
     * Sets the user name for display on the main view.
     *
     * @param userName The user name to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
        widgetLabel.setText("Welcome! " + userName);
    }

    /**
     * Handles the message loop for the main view.
     */
    public void messageLoop() {
        super.messageLoop();
        if (UserController.getCurrentUser() != null) {
            userName = UserController.getCurrentUser().getName();
            widgetLabel.setText("Welcome, " + userName + "!");
            facultyLabel.setText("Faculty: " + UserController.getCurrentUser().getSchool());
            pointLabel.setText(((Student) UserController.getCurrentUser()).getCommitteeCampList().isEmpty() ? "" :
                    "You have " + (((Student) UserController.getCurrentUser()).getPoints()) + " points as a committee member in");
            campLabel.setText(((Student) UserController.getCurrentUser()).getCommitteeCampList().isEmpty() ? "" :
                    ((Student) UserController.getCurrentUser()).getCommitteeCampList().get(0).getName());
            greetingLabel.setText("What would you like to do today?");
        }
        if (logoutButton.getPressed()) {
            switchToWindow = loginViewIndex;
        }
        if (changePasswordButton.getPressed()) {
            switchToWindow = changePasswordWindowIndex;
        }
        if (viewCampButton.getPressed()) {
            switchToWindow = campListViewIndex;
        }
    }

    /**
     * Performs actions upon exiting the main view.
     */
    public void onExit() {
        for (Widget widget : widgets) {
            if (widget instanceof WidgetButton) {
                ((WidgetButton) widget).clearPressed();
            }
        }
    }
}

