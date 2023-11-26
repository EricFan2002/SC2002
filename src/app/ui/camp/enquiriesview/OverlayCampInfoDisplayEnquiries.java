package app.ui.camp.enquiriesview;

import app.entity.RepositoryCollection;
import app.entity.camp.Camp;
import app.entity.enquiry.Enquiry;
import app.entity.enquiry.EnquiryList;
import app.entity.user.Student;
import app.ui.camp.infomationview.OverlayCampInfoDisplayView;
import app.ui.overlayactions.OverlayChooseBox;
import app.ui.overlayactions.OverlayNotification;
import app.ui.overlayactions.OverlayTextInputAction;
import app.ui.widgets.*;
import app.ui.windows.ICallBack;
import app.ui.windows.Window;

import java.util.ArrayList;


/**
 * OverlayCampInfoDisplayEnquiries extends OverlayCampInfoDisplayView and implements ICallBack.
 * This class manages the display and interactions related to camp enquiries within an overlay view.
 */
public class OverlayCampInfoDisplayEnquiries extends OverlayCampInfoDisplayView implements ICallBack {

    protected WidgetLabel labelNewEnq;
    protected WidgetTextBox textBoxEnq;
    protected WidgetButton sendButton;
    protected Camp camp;
    protected Student student;
    protected Window mainWindow;
    protected WidgetPageSelection participantsView;
    protected ArrayList<Enquiry> enquiryList;
    protected Enquiry selectedEnq;
    protected boolean editEnq = false;

    public OverlayCampInfoDisplayEnquiries(int x, int y, int offsetY, int offsetX, String windowName, Camp camp,
            Student student, Window mainWindow) {
        super(x, y, offsetY, offsetX, windowName, camp);

        this.mainWindow = mainWindow;

        this.camp = camp;
        this.student = student;

        enquiryList = new ArrayList<>();

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.getEnquiryRepository().filterByCamp(camp);
        for (Enquiry enquiry : enquires) {
            if(camp.getCommittees().contains(student) || enquiry.getSender().equals(student)) {
                ArrayList<String> tmp = new ArrayList<String>();
                enquiryList.add(enquiry);
                if (enquiry.getSender().equals(student))
                    tmp.add("Question: " + enquiry.getQuestion() + " ( From You )");
                else
                    tmp.add("Question: " + enquiry.getQuestion() + " ( From " + enquiry.getSender().getName() + " )");
                if (enquiry.getAnswer() == null || enquiry.getAnswer().equals(""))
                    tmp.add("    Not Answer Yet. Our coordinators will answer soon.");
                else
                    tmp.add("    : " + enquiry.getAnswer() + " ( Answered By " + enquiry.getAnsweredBy().getName() + ")");
                enqList.add(tmp);
            }
        }

        WidgetLabel labelParticipants = new WidgetLabel(3, 15, 15, "Your Enquires:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelParticipants);

        participantsView = new WidgetPageSelection(3, 16, getLenX() - 8, getLenY() / 2, "Participants", enqList,
                OverlayCampInfoDisplayEnquiries.this);
        addWidget(participantsView);

        labelNewEnq = new WidgetLabel(3, 31 + getLenY() / 4, getLenX() - 8, "New Enquiry To Camp");
        addWidget(labelNewEnq);
        textBoxEnq = new WidgetTextBox(3, 32 + getLenY() / 4, getLenX() - 8, "");
        addWidget(textBoxEnq);
        sendButton = new WidgetButton(3, 33 + getLenY() / 4, getLenX() - 8, "Send Enquiry");
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

        removeWidget(exitButton);
        addWidget(exitButton);

    }


    /**
     * Updates the list of camp enquiries displayed in the overlay.
     */
    public void updateEnquiries() {
        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.getEnquiryRepository().filterByCamp(camp);
        enquiryList.clear();
        for (Enquiry enquiry : enquires) {
            if(camp.getCommittees().contains(student) || enquiry.getSender().equals(student)) {
                ArrayList<String> tmp = new ArrayList<String>();
                enquiryList.add(enquiry);
                if (enquiry.getSender().equals(student))
                    tmp.add("Question: " + enquiry.getQuestion() + " ( From You )");
                else
                    tmp.add("Question: " + enquiry.getQuestion() + " ( From " + enquiry.getSender().getName() + " )");
                if (enquiry.getAnswer() == null || enquiry.getAnswer().equals(""))
                    tmp.add("    Not Answer Yet. Our coordinators will answer soon.");
                else
                    tmp.add("    : " + enquiry.getAnswer() + " ( Answered By " + enquiry.getAnsweredBy().getName() + ")");
                enqList.add(tmp);
            }
        }
        participantsView.updateList(enqList);
    }

    /**
     * The main message loop handling user interactions within the camp enquiries overlay.
     * Manages actions based on button presses and user selections.
     */
    public void messageLoop() {
        super.messageLoop();
        if (participantsView.getSelectedOption() != -1) {
            selectedEnq = enquiryList.get(participantsView.getSelectedOption());
            if (selectedEnq != null) {
                if (selectedEnq.getAnswer() == null || selectedEnq.getAnswer().equals("")) {
                    ArrayList<String> options = new ArrayList<>();
                    if(selectedEnq.getSender().equals(student)) {
                        options.add("Delete Enquiry");
                        options.add("Edit Enquiry");
                    }
                    options.add("Cancel");
                    WidgetButton buttonPosition = participantsView.getSelectionsButton()
                            .get(participantsView.getSelectedOption()).get(0);
                    OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(),
                            getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options,
                            OverlayCampInfoDisplayEnquiries.this);
                    mainWindow.addOverlay(overlayChooseBox);
                    participantsView.clearSelectedOption();
                } else {
                    ArrayList<String> options = new ArrayList<>();
                    options.add("Answered Enquiry");
                    options.add("Cannot Edit Anymore.");
                    WidgetButton buttonPosition = participantsView.getSelectionsButton()
                            .get(participantsView.getSelectedOption()).get(0);
                    OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(),
                            getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options,
                            OverlayCampInfoDisplayEnquiries.this);
                    mainWindow.addOverlay(overlayChooseBox);
                    participantsView.clearSelectedOption();
                }
            }
        }
        if (sendButton.getPressed()) {
            sendButton.clearPressed();
            if (textBoxEnq.getText().equals("")) {
                OverlayNotification overlayNotification = new OverlayNotification(40, getY() / 2 - 8,
                        getX() + getX() / 2 - 20, "Info", "Empty Enquiry!", OverlayCampInfoDisplayEnquiries.this);
                mainWindow.addOverlay(overlayNotification);
            } else {
                Enquiry enquiry = new Enquiry(String.valueOf(System.currentTimeMillis()), student, textBoxEnq.getText(),
                        camp);
                RepositoryCollection.getEnquiryRepository().add(enquiry);
                OverlayNotification overlayNotification = new OverlayNotification(40, getY() / 2 - 8,
                        getX() + getX() / 2 - 20, "Info", "Enquiry Sent!", OverlayCampInfoDisplayEnquiries.this);
                mainWindow.addOverlay(overlayNotification);
                updateEnquiries();
            }
        }
        if (editEnq) {
            editEnq = false;
            OverlayTextInputAction overlayTextInputAction = new OverlayTextInputAction(60, getY() / 2 - 8, getX() + getX() / 2 - 30,
                    "Info", "Change Enquiry To:", OverlayCampInfoDisplayEnquiries.this, selectedEnq.getQuestion());
            mainWindow.addOverlay(overlayTextInputAction);
        }
        if (exitButton.getPressed()) {
            exitButton.clearPressed();
            setDestroy();
        }
    }

    /**
     * Performs necessary clean-up tasks upon exiting the overlay for camp enquiries.
     */
    public void onExit() {
        super.onExit();
    }



    /**
     * An override method from the ICallBack interface.
     * Handles specific actions based on the user's choice within the overlay.
     *
     * @param chose      The chosen index.
     * @param choseString The chosen string.
     */
    @Override
    public void onWindowFinished(int chose, String choseString) {
        if (choseString.equals("Delete Enquiry")) {
            if (selectedEnq != null) {
                RepositoryCollection.getEnquiryRepository().remove(selectedEnq);
                updateEnquiries();
            }
        } else if (choseString.equals("Edit Enquiry")) {
            editEnq = true;
        } else if (chose == 255) {
            selectedEnq.setQuestion(choseString);
            RepositoryCollection.getEnquiryRepository().update(selectedEnq);
            updateEnquiries();
        }
    }
}
