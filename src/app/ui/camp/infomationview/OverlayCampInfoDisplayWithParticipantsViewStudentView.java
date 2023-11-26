package app.ui.camp.infomationview;

import app.entity.camp.Camp;


/**
 * Extension of OverlayCampInfoDisplayWithParticipantsView customized for student view.
 */
public class OverlayCampInfoDisplayWithParticipantsViewStudentView extends OverlayCampInfoDisplayWithParticipantsView {

    Camp camp;

    /**
     * Constructs an OverlayCampInfoDisplayWithParticipantsViewStudentView object.
     *
     * @param x          The x-coordinate position.
     * @param y          The y-coordinate position.
     * @param offsetY    The y-coordinate offset.
     * @param offsetX    The x-coordinate offset.
     * @param windowName The name of the overlay window.
     * @param camp       The camp app.entity containing information to be displayed.
     */
    public OverlayCampInfoDisplayWithParticipantsViewStudentView(int x, int y, int offsetY, int offsetX, String windowName, Camp camp) {
        super(x, y, offsetY, offsetX, windowName, camp);
        textBoxDescription.setSkipSelection(true);
        textBoxDClose.setSkipSelection(true);
        textBoxDStart.setSkipSelection(true);
        textBoxDEnd.setSkipSelection(true);
        textBoxDClose.setSkipSelection(true);
        textBoxSchool.setSkipSelection(true);
        textBoxLocation.setSkipSelection(true);
        textBoxSlots.setSkipSelection(true);
        textBoxSlotsC.setSkipSelection(true);
        textBoxVis.setSkipSelection(true);
        textBoxCName.setSkipSelection(true);
    }

    /**
     * Manages interactions within the overlay, including exit functionality.
     */
    public void messageLoop() {
        super.messageLoop();
        if(exitButton.getPressed()){
            exitButton.clearPressed();
            setDestroy();
        }
    }

    /**
     * Performs necessary clean-up tasks upon exiting the overlay.
     */
    public void onExit(){
        super.onExit();
    }
}
