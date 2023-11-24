package ui.CampInfomationView;

import entity.user.Student;
import ui.widgets.*;

import entity.camp.Camp;

import java.util.ArrayList;

/**
 * Extension of OverlayCampInfoDisplayView that includes participant and committee views
 * in addition to displaying camp information.
 */
public class OverlayCampInfoDisplayWithParticipantsView extends OverlayCampInfoDisplayView {

    /**
     * Constructs an OverlayCampInfoDisplayWithParticipantsView object.
     *
     * @param x          The x-coordinate position.
     * @param y          The y-coordinate position.
     * @param offsetY    The y-coordinate offset.
     * @param offsetX    The x-coordinate offset.
     * @param windowName The name of the overlay window.
     * @param camp       The camp entity containing information to be displayed.
     */
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

    /**
     * The main message loop that manages interactions within the overlay.
     */
    public void messageLoop() {
        super.messageLoop();
    }

    /**
     * Performs necessary clean-up tasks upon exiting the overlay.
     */
    public void onExit(){
        super.onExit();
    }
}
