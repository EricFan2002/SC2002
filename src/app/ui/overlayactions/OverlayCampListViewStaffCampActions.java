package app.ui.overlayactions;

import app.entity.camp.Camp;
import app.entity.user.Staff;
import app.ui.widgets.WidgetButton;
import app.ui.windows.ICallBack;
import app.ui.windows.Window;
import app.ui.windows.WindowOverlayClass;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code OverlayCampListViewStaffCampActions} class represents an overlay window for displaying camp-related actions
 * available to a staff member. It extends the {@code WindowOverlayClass} and implements the {@code ICallBack} interface.
 */
public class OverlayCampListViewStaffCampActions extends WindowOverlayClass {
    /**
     * The button to cancel the action.
     */
    WidgetButton cancelButton;

    /**
     * The selected action index.
     */
    private int chose;

    /**
     * The selected action string.
     */
    private String choseString;

    /**
     * The list of action buttons presented to the user.
     */
    List<WidgetButton> choices;

    /**
     * The callback window to notify upon completion.
     */
    Window callbackWindow;

    /**
     * The staff member for whom the actions are presented.
     */
    protected Staff staff;

    /**
     * The camp associated with the actions.
     */
    protected Camp camp;

    /**
     * The total number of options/actions available.
     */
    public final static int optionCount = 5;

    /**
     * Constructs an instance of {@code OverlayCampListViewStaffCampActions}.
     *
     * @param x              The x-coordinate of the window.
     * @param offsetY        The y-coordinate offset of the window.
     * @param offsetX        The x-coordinate offset of the window.
     * @param windowName     The name of the window.
     * @param callbackWindow The callback window to notify upon completion.
     * @param staff          The staff member for whom the actions are presented.
     * @param camp           The camp associated with the actions.
     */
    public OverlayCampListViewStaffCampActions(int x, int offsetY, int offsetX, String windowName, Window callbackWindow, Staff staff, Camp camp) {
        super(3 + optionCount, x, offsetY, offsetX, windowName);
        this.staff = staff;
        this.camp = camp;

        List<String> options = new ArrayList<>();
        options.add("View Details");
        if (camp.getStaffInCharge().equals(staff)) {
            options.add("Edit Camp");
            options.add("Delete Camp");
            options.add("Reply Enquiry");
            options.add("View Suggestions");
        }
        options.add("Cancel");

        setY(options.size() + 3);

        choices = new ArrayList<>();
        this.callbackWindow = callbackWindow;
        for (int i = 0; i < options.size(); i++) {
            WidgetButton choice = new WidgetButton(1, 2 + i, x - 2, options.get(i));
            addWidget(choice);
            choices.add(choice);
        }
    }

    /**
     * Handles the message loop for the overlay window.
     */
    public void messageLoop() {
        for (int i = 0; i < choices.size(); i++) {
            if (choices.get(i).getPressed()) {
                System.out.println(i);
                chose = i;
                choseString = widgets.get(i).getText();
                setDestroy();
            }
        }
    }

    /**
     * Performs actions upon exiting the overlay window.
     */
    public void onExit() {
        super.onExit();
        if (callbackWindow instanceof ICallBack) {
            ((ICallBack) callbackWindow).onWindowFinished(chose, choseString);
        }
    }
}
