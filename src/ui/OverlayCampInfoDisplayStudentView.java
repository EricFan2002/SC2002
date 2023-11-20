package ui;

import entity.camp.Camp;
import entity.user.Student;
import ui.widgets.*;
import ui.windows.WindowOverlayClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OverlayCampInfoDisplayStudentView extends OverlayCampInfoDisplay {

    Camp camp;

    public OverlayCampInfoDisplayStudentView(int x, int y, int offsetY, int offsetX, String windowName, Camp camp) {
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
