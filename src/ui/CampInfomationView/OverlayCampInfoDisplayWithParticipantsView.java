package ui.CampInfomationView;

import entity.user.Student;
import ui.widgets.*;

import entity.camp.Camp;

import java.util.ArrayList;

public class OverlayCampInfoDisplayWithParticipantsView extends OverlayCampInfoDisplayView {


    public OverlayCampInfoDisplayWithParticipantsView(int x, int y, int offsetY, int offsetX, String windowName, Camp camp) {
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

        WidgetLabel labelParticipants = new WidgetLabel(3, 15, getLenX() - 10, "Participants: " + (camp.getAttendees().size() + camp.getCommittees().size()) + " / " + camp.getTotalSlots() + " (" + camp.getCommittees().size() + " Committees)", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(labelParticipants);

        WidgetPageSelection participantsView = new WidgetPageSelection(3, 16, getLenX() - 8, getLenY() / 4, "Participants", participants, OverlayCampInfoDisplayWithParticipantsView.this);
        addWidget(participantsView);

        WidgetLabel labelCommittee = new WidgetLabel(3, 17 + getLenY() / 4, getLenX() - 10, "Committees: " + camp.getCommittees().size() + " / 10", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(labelCommittee);

        WidgetPageSelection committeesView = new WidgetPageSelection(3, 18 + getLenY() / 4, getLenX() - 8, getLenY() / 4, "Committees", committees, OverlayCampInfoDisplayWithParticipantsView.this);
        addWidget(committeesView);
    }

    public void messageLoop() {
        super.messageLoop();
    }
    public void onExit(){
        super.onExit();
    }
}