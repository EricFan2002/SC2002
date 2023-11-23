package ui;

import entity.camp.Camp;
import entity.user.Student;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.WindowOverlayClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OverlayCampInfoDisplayRaw extends WindowOverlayClass {

    Camp camp;
    protected WidgetButton exitButton;
    protected WidgetTextBox textBoxCName;
    protected WidgetTextBox textBoxDescription;
    protected WidgetTextBox textBoxDStart;
    protected WidgetTextBox textBoxDEnd;
    protected WidgetTextBox textBoxDClose;
    protected WidgetTextBox textBoxSchool;
    protected WidgetTextBox textBoxLocation;
    protected WidgetTextBox textBoxSlotsC;
    protected WidgetToggle textBoxVis;
    protected WidgetTextBox textBoxSlots;

    public OverlayCampInfoDisplayRaw(int x, int y, int offsetY, int offsetX, String windowName, Camp camp) {
        super(y , x, offsetY, offsetX, windowName);

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");

//        WidgetLabel widgetLabel = new WidgetLabel(3, 2, getLenX() - 10, "Camp Detail About: " + camp.getName(), TEXT_ALIGNMENT.ALIGN_MID);
//        addWidget(widgetLabel);

        // Description
        WidgetLabel labelName = new WidgetLabel(3, 3, 15, "Name:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelName);
        textBoxCName = new WidgetTextBox(19, 3, getLenX() - 24, camp.getName());
        addWidget(textBoxCName);

        // Description
        WidgetLabel labelDescription = new WidgetLabel(3, 4, 15, "Description:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDescription);
        textBoxDescription = new WidgetTextBox(19, 4, getLenX() - 24, camp.getDescription());
        addWidget(textBoxDescription);

        // Start Date
        WidgetLabel labelDStart = new WidgetLabel(3, 5, 15, "Start Date:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDStart);
        textBoxDStart = new WidgetTextBox(19, 5, getLenX() - 24, ft.format(camp.getStartDate()));
        addWidget(textBoxDStart);

        // End Date
        WidgetLabel labelDEnd = new WidgetLabel(3, 6, 15, "End Date:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDEnd);
        textBoxDEnd = new WidgetTextBox(19, 6, getLenX() - 24, ft.format(camp.getEndDate()));
        addWidget(textBoxDEnd);

        // Registration Close
        WidgetLabel labelDClose = new WidgetLabel(3, 7, 15, "Reg Close:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDClose);
        textBoxDClose = new WidgetTextBox(19, 7, getLenX() - 24, ft.format(camp.getCloseRegistrationDate()));
        addWidget(textBoxDClose);

        // Faculty
        WidgetLabel labelSchool = new WidgetLabel(3, 8, 15, "Faculty:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSchool);
        textBoxSchool = new WidgetTextBox(19, 8, getLenX() - 24, camp.getSchool() );
        addWidget(textBoxSchool);

        // Location
        WidgetLabel labelLocation = new WidgetLabel(3, 9, 15, "Location:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelLocation);
        textBoxLocation = new WidgetTextBox(19, 9, getLenX() - 24, camp.getLocation());
        addWidget(textBoxLocation);

        // Slots
        WidgetLabel labelSlots = new WidgetLabel(3, 10, 15, "Slots:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSlots);
        textBoxSlots = new WidgetTextBox(19, 10, getLenX() - 24, camp.getAttendees().size() + " / " + camp.getTotalSlots().toString());
        addWidget(textBoxSlots);

        // Slots Committee
        WidgetLabel labelSlotsC = new WidgetLabel(3, 11, 15, "Committee Slots:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSlotsC);
        textBoxSlotsC = new WidgetTextBox(19, 11, getLenX() - 24, camp.getCommittees().size() + " / " + "10");
        addWidget(textBoxSlotsC);

        // Visibility
        WidgetLabel labelVis = new WidgetLabel(3, 12, 15, "Visibility:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelVis);
        textBoxVis = new WidgetToggle(19, 12, getLenX() - 24, "Visible");
        if(camp.isVisible()){
            textBoxVis.setPressed();
        }
        else{
            textBoxVis.clearPressed();
        }
        addWidget(textBoxVis);

        // Go Back Button
        exitButton = new WidgetButton(3, getY() - 6, getLenX() - 10, "Go Back");
        addWidget(exitButton);

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
