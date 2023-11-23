package ui;

import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.enquiry.Enquiry;
import entity.enquiry.EnquiryList;
import entity.user.Student;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.util.ArrayList;

public class OverlayCampInfoDisplayEnquiriesCommittee extends OverlayCampInfoDisplayRaw implements ICallBack {

    protected Camp camp;
    protected Student student;
    protected Window mainWindow;
    protected WidgetPageSelection participantsView;
    protected ArrayList<Enquiry> enquiryList;
    protected Enquiry selectedEnq;
    protected boolean replyEnq = false;

    public OverlayCampInfoDisplayEnquiriesCommittee(int x, int y, int offsetY, int offsetX, String windowName,
            Camp camp, Student student, Window mainWindow) {
        super(x, y, offsetY, offsetX, windowName, camp);

        this.mainWindow = mainWindow;

        this.camp = camp;
        this.student = student;

        enquiryList = new ArrayList<>();

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.getEnquiryRepository().filterByCamp(camp); // .filterBySender(student);
        for (Enquiry enquiry : enquires) {
            ArrayList<String> tmp = new ArrayList<String>();
            enquiryList.add(enquiry);
            tmp.add("Question: " + enquiry.getQuestion());
            if (enquiry.getAnswer() == null || enquiry.getAnswer().equals(""))
                tmp.add("    Not Answer Yet. Our coordinators will answer soon.");
            else
                tmp.add("    : " + enquiry.getAnswer() + " ( Answered By " + enquiry.getAnsweredBy().getName() + ")");
            enqList.add(tmp);
        }

        WidgetLabel labelParticipants = new WidgetLabel(3, 15, 34, "Reply Participant Enquires:",
                TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelParticipants);

        participantsView = new WidgetPageSelection(3, 16, getLenX() - 6, getLenY() / 2 + 4, "Participants", enqList,
                OverlayCampInfoDisplayEnquiriesCommittee.this);

        addWidget(participantsView);

        textBoxCName.setSkipSelection(true);
        textBoxCName.setSkipSelection(true);
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

        removeWidget(exitButton);
        addWidget(exitButton);

    }

    public void updateEnquiries() {
        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.getEnquiryRepository().filterByCamp(camp);
        enquiryList.clear();
        for (Enquiry enquiry : enquires) {
            enquiryList.add(enquiry);
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add("Question: " + enquiry.getQuestion());
            if (enquiry.getAnswer() == null || enquiry.getAnswer().equals(""))
                tmp.add("    Not Answer Yet.");
            else
                tmp.add("    : " + enquiry.getAnswer() + " ( Answered By " + enquiry.getAnsweredBy().getName() + " )");
            enqList.add(tmp);
        }
        participantsView.updateList(enqList);
    }

    public void messageLoop() {
        super.messageLoop();
        if (participantsView.getSelectedOption() != -1) {
            selectedEnq = enquiryList.get(participantsView.getSelectedOption());
            if (selectedEnq != null) {
                ArrayList<String> options = new ArrayList<>();
                options.add("Reply Enquiry");
                options.add("Cancel");
                WidgetButton buttonPosition = participantsView.getSelectionsButton()
                        .get(participantsView.getSelectedOption()).get(0);
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(),
                        getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options,
                        OverlayCampInfoDisplayEnquiriesCommittee.this);
                mainWindow.addOverlay(overlayChooseBox);
                participantsView.clearSelectedOption();
            }
        }
        if (replyEnq) {
            replyEnq = false;
            OverlayTextInput overlayTextInput = new OverlayTextInput(60, getY() / 2 - 8, getX() + getX() / 2 - 30,
                    "Info", "Reply Enquiry:", OverlayCampInfoDisplayEnquiriesCommittee.this);
            mainWindow.addOverlay(overlayTextInput);
        }
        if (exitButton.getPressed()) {
            exitButton.clearPressed();
            setDestroy();
        }
    }

    public void onExit() {
        super.onExit();
    }

    @Override
    public void onWindowFinished(int chose, String choseString) {
        if (choseString.equals("Reply Enquiry")) {
            replyEnq = true;
        } else if (chose == 255) {
            selectedEnq.setAnswer(choseString, student);
            RepositoryCollection.getEnquiryRepository().update(selectedEnq);
            updateEnquiries();
        }
    }
}
