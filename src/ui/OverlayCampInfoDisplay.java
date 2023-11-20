package ui;

import entity.user.Student;
import entity.user.User;
import ui.widgets.*;
import ui.windows.WindowOverlayClass;

import entity.camp.Camp;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OverlayCampInfoDisplay extends WindowOverlayClass {

    Camp camp;
    WidgetButton exitButton;
    WidgetTextBox textBoxDescription;
    WidgetTextBox textBoxDStart;
    WidgetTextBox textBoxDEnd;
    WidgetTextBox textBoxDClose;
    WidgetTextBox textBoxSchool;
    WidgetTextBox textBoxLocation;

    public OverlayCampInfoDisplay(int x, int y, int offsetY, int offsetX, String windowName, Camp camp) {
        super(y , x, offsetY, offsetX, windowName);

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        WidgetLabel widgetLabel = new WidgetLabel(3, 2, getLenX() - 10, "Camp Detail About: " + camp.getName(), TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);

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
        WidgetTextBox textBoxLocation = new WidgetTextBox(19, 9, getLenX() - 24, camp.getLocation());
        addWidget(textBoxLocation);

        // Slots
        WidgetLabel labelSlots = new WidgetLabel(3, 10, 15, "Slots:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSlots);
        WidgetTextBox textBoxSlots = new WidgetTextBox(19, 10, getLenX() - 24, camp.getAttendees().size() + " / " + camp.getTotalSlots().toString());
        addWidget(textBoxSlots);

        // Slots Committee
        WidgetLabel labelSlotsC = new WidgetLabel(3, 11, 15, "Committee Slots:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSlotsC);
        WidgetTextBox textBoxSlotsC = new WidgetTextBox(19, 11, getLenX() - 24, camp.getCommittees().size() + " / " + "10");
        addWidget(textBoxSlotsC);

        // Visibility
        WidgetLabel labelVis = new WidgetLabel(3, 12, 15, "Visibility:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelVis);
        WidgetToggle textBoxVis = new WidgetToggle(19, 12, getLenX() - 24, "Visible");
        if(camp.isVisible()){
            textBoxVis.setPressed();
        }
        addWidget(textBoxVis);

        ArrayList<ArrayList<String>> participants = new ArrayList<>();
        for(Student user : camp.getAttendees()){
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(user.getName() + " [" + user.getID() + ", " + user.getFaculty() + "]");
            participants.add(tmp);
        }

        WidgetLabel labelParticipants = new WidgetLabel(3, 15, 15, "Participants:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelParticipants);

        WidgetPageSelection participantsView = new WidgetPageSelection(3, 16, getLenX() - 8, getLenY() / 4, "Participants", participants, OverlayCampInfoDisplay.this);
        addWidget(participantsView);

        ArrayList<ArrayList<String>> committees = new ArrayList<>();
        for(Student user : camp.getCommittees()){
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(user.getName() + " [" + user.getID() + ", " + user.getFaculty() + "]");
            committees.add(tmp);
        }

        WidgetLabel labelCommittee = new WidgetLabel(3, 17 + getLenY() / 4, 15, "Committees:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelCommittee);

        WidgetPageSelection committeesView = new WidgetPageSelection(3, 18 + getLenY() / 4, getLenX() - 8, getLenY() / 4, "Committees", committees, OverlayCampInfoDisplay.this);
        addWidget(committeesView);

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
