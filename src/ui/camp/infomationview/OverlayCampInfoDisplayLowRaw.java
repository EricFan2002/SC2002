package ui.camp.infomationview;

import entity.camp.Camp;
import ui.widgets.*;
import ui.windows.WindowOverlayClass;

import java.text.SimpleDateFormat;


/**
 * OverlayCampInfoDisplayLowRaw extends WindowOverlayClass and manages the display of basic camp information
 * within an overlay, providing details such as camp name, description, dates, location, slots, and visibility.
 */
public class OverlayCampInfoDisplayLowRaw extends WindowOverlayClass {

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


    /**
     * Constructs an OverlayCampInfoDisplayLowRaw object.
     *
     * @param x          The x-coordinate position.
     * @param y          The y-coordinate position.
     * @param offsetY    The y-coordinate offset.
     * @param offsetX    The x-coordinate offset.
     * @param windowName The name of the overlay window.
     * @param camp       The camp entity containing information to be displayed.
     */
    public OverlayCampInfoDisplayLowRaw(int x, int y, int offsetY, int offsetX, String windowName, Camp camp) {
        super(y , x, offsetY, offsetX, windowName);

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        final int OFFSET = 20;

        // Description
        WidgetLabel labelName = new WidgetLabel(3, 3 + OFFSET, 15, "Name:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelName);
        textBoxCName = new WidgetTextBox(19, 3 + OFFSET, getLenX() - 24, camp.getName());
        addWidget(textBoxCName);

        // Description
        WidgetLabel labelDescription = new WidgetLabel(3, 4 + OFFSET, 15, "Description:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDescription);
        textBoxDescription = new WidgetTextBox(19, 4 + OFFSET, getLenX() - 24, camp.getDescription());
        addWidget(textBoxDescription);

        // Start Date
        WidgetLabel labelDStart = new WidgetLabel(3, 5 + OFFSET, 15, "Start Date:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDStart);
        textBoxDStart = new WidgetTextBox(19, 5 + OFFSET, getLenX() - 24, ft.format(camp.getStartDate()));
        addWidget(textBoxDStart);

        // End Date
        WidgetLabel labelDEnd = new WidgetLabel(3, 6 + OFFSET, 15, "End Date:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDEnd);
        textBoxDEnd = new WidgetTextBox(19, 6 + OFFSET, getLenX() - 24, ft.format(camp.getEndDate()));
        addWidget(textBoxDEnd);

        // Registration Close
        WidgetLabel labelDClose = new WidgetLabel(3, 7 + OFFSET, 15, "Reg Close:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDClose);
        textBoxDClose = new WidgetTextBox(19, 7 + OFFSET, getLenX() - 24, ft.format(camp.getCloseRegistrationDate()));
        addWidget(textBoxDClose);

        // Faculty
        WidgetLabel labelSchool = new WidgetLabel(3, 8 + OFFSET, 15, "Faculty:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSchool);
        textBoxSchool = new WidgetTextBox(19, 8 + OFFSET, getLenX() - 24, camp.getSchool() );
        addWidget(textBoxSchool);

        // Location
        WidgetLabel labelLocation = new WidgetLabel(3, 9 + OFFSET, 15, "Location:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelLocation);
        textBoxLocation = new WidgetTextBox(19, 9 + OFFSET, getLenX() - 24, camp.getLocation());
        addWidget(textBoxLocation);

        // Slots
        WidgetLabel labelSlots = new WidgetLabel(3, 10 + OFFSET, 15, "Slots:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSlots);
        textBoxSlots = new WidgetTextBox(19, 10 + OFFSET, getLenX() - 24, camp.getAttendees().size() + " / " + camp.getTotalSlots().toString());
        addWidget(textBoxSlots);

        // Slots Committee
        WidgetLabel labelSlotsC = new WidgetLabel(3, 11 + OFFSET, 15, "Committee Slots:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSlotsC);
        textBoxSlotsC = new WidgetTextBox(19, 11 + OFFSET, getLenX() - 24, camp.getCommittees().size() + " / " + camp.getTotalCommitteeSlots());
        addWidget(textBoxSlotsC);

        // Visibility
        WidgetLabel labelVis = new WidgetLabel(3, 12 + OFFSET, 15, "Visibility:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelVis);
        textBoxVis = new WidgetToggle(19, 12 + OFFSET, getLenX() - 24, "Visible");
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

    /**
     * The main message loop that manages interactions within the overlay.
     * Handles button presses and actions accordingly.
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
