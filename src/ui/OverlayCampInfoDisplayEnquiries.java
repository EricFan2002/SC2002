package ui;

import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.enquiry.Enquiry;
import entity.enquiry.EnquiryList;
import entity.user.Student;
import ui.widgets.*;

import java.util.ArrayList;

public class OverlayCampInfoDisplayEnquiries extends OverlayCampInfoDisplayRaw {

    WidgetLabel labelNewEnq;
    WidgetTextBox textBoxEnq;
    WidgetButton sendButton;


    public OverlayCampInfoDisplayEnquiries(int x, int y, int offsetY, int offsetX, String windowName, Camp camp, Student student) {
        super(x , y, offsetY, offsetX, windowName, camp);

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.enquiryRepository.getAll().filterByCamp(camp).filterBySender(student);
        for(Enquiry enquiry : enquires){
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add("Question: " + enquiry.getQuestion());
            if(enquiry.getAnswer() == null || enquiry.getAnswer().equals(""))
                tmp.add("    Not Answer Yet. Our coordinators will answer soon.");
            else
                tmp.add("    : " + enquiry.getAnswer() + " ( Answered By " + enquiry.getAnsweredBy().getName() + ")");
            enqList.add(tmp);
        }

        WidgetLabel labelParticipants = new WidgetLabel(3, 15, 15, "Your Enquires:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelParticipants);

        WidgetPageSelection participantsView = new WidgetPageSelection(3, 16, getLenX() - 8, getLenY() / 4, "Participants", enqList, OverlayCampInfoDisplayEnquiries.this);
        addWidget(participantsView);

        labelNewEnq = new WidgetLabel(3, 18 + getLenY() / 4, getLenX() - 8, "New Enquiry To Camp");
        addWidget(labelNewEnq);
        textBoxEnq = new WidgetTextBox(3, 20 + getLenY() / 4, getLenX() - 8, "");
        addWidget(textBoxEnq);
        sendButton = new WidgetButton(3, 22 + getLenY() / 4, getLenX() - 8, "Send Enquiry");
        addWidget(sendButton);

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

    }

    public void messageLoop() {
        super.messageLoop();
    }
    public void onExit(){
        super.onExit();
    }
}
