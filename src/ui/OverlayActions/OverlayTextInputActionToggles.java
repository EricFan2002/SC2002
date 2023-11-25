package ui.OverlayActions;

import ui.widgets.WidgetButton;
import ui.widgets.WidgetToggle;
import ui.windows.ICallBack;
import ui.windows.Window;

public class OverlayTextInputActionToggles extends OverlayTextInputAction{
    protected WidgetToggle toggleGenAttendee;
    protected WidgetToggle toggleGenCommittee;
    private int returnCode;
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
