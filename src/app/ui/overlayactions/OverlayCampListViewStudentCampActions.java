package app.ui.overlayactions;

import app.entity.camp.Camp;
import app.entity.user.Student;
import app.ui.widgets.WidgetButton;
import app.ui.windows.ICallBack;
import app.ui.windows.Window;
import app.ui.windows.WindowOverlayClass;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code OverlayCampListViewStudentCampActions} class represents an overlay window for displaying camp-related actions
 * available to a student. It extends the {@code WindowOverlayClass} and implements the {@code ICallBack} interface.
 */
public class OverlayCampListViewStudentCampActions extends WindowOverlayClass {
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
     * The student for whom the actions are presented.
     */
    Student student;

    /**
     * The camp associated with the actions.
     */
    Camp camp;

    /**
     * The total number of options/actions available.
     */
    public final static int optionCount = 6;

    /**
     * Constructs an instance of {@code OverlayCampListViewStudentCampActions}.
     *
     * @param x              The x-coordinate of the window.
     * @param offsetY        The y-coordinate offset of the window.
     * @param offsetX        The x-coordinate offset of the window.
     * @param windowName     The name of the window.
     * @param callbackWindow The callback window to notify upon completion.
     * @param student        The student for whom the actions are presented.
     * @param camp           The camp associated with the actions.
     */
    public OverlayCampListViewStudentCampActions(int x, int offsetY, int offsetX, String windowName, Window callbackWindow, Student student, Camp camp) {
        super(3 + optionCount, x, offsetY, offsetX, windowName);

        this.student = student;
        this.camp = camp;

        List<String> options = new ArrayList<>();

        options.add("View Details");
        if ((!camp.getAttendees().contains(student)) && (!camp.getCommittees().contains(student)))
            options.add("Join Camp");
        if (camp.getAttendees().contains(student))
            options.add("Quit Camp");
        if (!camp.getCommittees().contains(student))
            options.add("Enquiry");
        if (camp.getCommittees().contains(student))
            options.add("Reply Enquiry");
        if (camp.getCommittees().contains(student))
            options.add("Suggestions");
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
