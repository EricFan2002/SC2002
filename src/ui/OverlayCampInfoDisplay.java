package ui;

import entity.user.Student;
import entity.user.User;
import ui.widgets.*;
import ui.windows.WindowOverlayClass;

import entity.camp.Camp;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OverlayCampInfoDisplay extends OverlayCampInfoDisplayRaw {


    public OverlayCampInfoDisplay(int x, int y, int offsetY, int offsetX, String windowName, Camp camp) {
        super(x , y, offsetY, offsetX, windowName, camp);

        ArrayList<ArrayList<String>> committees = new ArrayList<>();
        for(Student user : camp.getCommittees()){
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(user.getName() + " [" + user.getID() + ", " + user.getFaculty() + "]");
            committees.add(tmp);
        }

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

        WidgetLabel labelCommittee = new WidgetLabel(3, 17 + getLenY() / 4, 15, "Committees:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelCommittee);

        WidgetPageSelection committeesView = new WidgetPageSelection(3, 18 + getLenY() / 4, getLenX() - 8, getLenY() / 4, "Committees", committees, OverlayCampInfoDisplay.this);
        addWidget(committeesView);
    }

    public void messageLoop() {
        super.messageLoop();
    }
    public void onExit(){
        super.onExit();
    }
}
