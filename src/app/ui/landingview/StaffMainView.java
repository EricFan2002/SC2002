package app.ui.landingview;

import app.controller.user.UserController;
import app.ui.widgets.TEXT_ALIGNMENT;
import app.ui.widgets.Widget;
import app.ui.widgets.WidgetButton;
import app.ui.widgets.WidgetLabel;
import app.ui.windows.Window;

/**
 * The {@code StaffMainView} class represents the main landing page for a staff user. It extends the {@code Window}
 * class and provides functionalities such as viewing camp details, changing password, and logging out.
 */
public class StaffMainView extends Window {
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
     * Constructs an instance of {@code StaffMainView}.
     *
     * @param loginViewIndex        The index of the login view window.
     * @param campListViewIndex     The index of the camp list view window.
     * @param changePasswordWindowIndex The index of the change password window.
     */
    public StaffMainView(int loginViewIndex, int campListViewIndex, int changePasswordWindowIndex) {
        super(24, 50, "Landing Page");
        widgetLabel = new WidgetLabel(3, 3, 40, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        facultyLabel = new WidgetLabel(3, 5, 40, "", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(facultyLabel);
        greetingLabel = new WidgetLabel(3, 9, 40, "", TEXT_ALIGNMENT.ALIGN_MID);
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

