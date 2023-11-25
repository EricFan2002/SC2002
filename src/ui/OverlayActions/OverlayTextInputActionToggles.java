package ui.OverlayActions;

import ui.widgets.WidgetButton;
import ui.widgets.WidgetToggle;
import ui.windows.ICallBack;
import ui.windows.Window;

/**
 * Class OverlayTextInputActionToggles provides an overlay text input with two toggle options.
 * This class extends OverlayTextInputAction and adds the functionality of two toggle switches,
 * specifically for 'Attendee' and 'Committee'. It is designed to be used within a GUI environment
 * where such toggles are necessary.
 */
public class OverlayTextInputActionToggles extends OverlayTextInputAction {

    /**
     * Toggle for generating attendee.
     */
    protected WidgetToggle toggleGenAttendee;

    /**
     * Toggle for generating committee.
     */
    protected WidgetToggle toggleGenCommittee;

    /**
     * Return code that is used to determine the action taken after exiting the overlay.
     */
    private int returnCode;

    /**
     * Constructs a new OverlayTextInputActionToggles instance with specified parameters.
     *
     * @param x The x-coordinate of the overlay.
     * @param offsetY The y-offset of the overlay.
     * @param offsetX The x-offset of the overlay.
     * @param windowName The name of the window.
     * @param prompt The prompt text to display.
     * @param callbackWindow The window that will be called back on action completion.
     * @param returnCode The initial return code.
     */
    public OverlayTextInputActionToggles(int x, int offsetY, int offsetX, String windowName, String prompt, Window callbackWindow, int returnCode) {
        super(x, offsetY, offsetX, windowName, prompt, callbackWindow, returnCode);
        toggleGenAttendee = new WidgetToggle(3, 6, getLenX() / 2 - 3, "Attendee");
        toggleGenCommittee = new WidgetToggle(3 + getLenX() / 2 - 2, 6, getLenX() / 2 - 2, "Committee");
        addWidget(toggleGenAttendee);
        addWidget(toggleGenCommittee);
        this.returnCode = returnCode;
    }

    /**
     * Performs actions upon exiting the overlay window.
     * This method updates the return code based on the state of the toggle buttons
     * and invokes the callback method on the callbackWindow if it implements ICallBack.
     */
    public void onExit() {
        if(toggleGenAttendee.getPressed()){
            returnCode += 1;
        }
        if(toggleGenCommittee.getPressed()) {
            returnCode += 2;
        }
        if (callbackWindow instanceof ICallBack) {
            ((ICallBack) callbackWindow).onWindowFinished(returnCode, textInput.getText());
        }
        if (abord) {
            ((ICallBack) callbackWindow).onWindowFinished(-1, textInput.getText());
        }
    }
}
