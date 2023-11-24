package ui.CampInfomationView;

import entity.camp.Camp;

public class OverlayCampInfoDisplayWithParticipantsViewStudentView extends OverlayCampInfoDisplayWithParticipantsView {

    Camp camp;

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

    public void messageLoop() {
        super.messageLoop();
        if(exitButton.getPressed()){
            exitButton.clearPressed();
            setDestroy();
        }
    }
    public void onExit(){
        super.onExit();
    }
}
