package app.ui.camp.enquiriesview;

import app.controller.camp.CampEnquiryController;
import app.entity.RepositoryCollection;
import app.entity.camp.Camp;
import app.entity.enquiry.Enquiry;
import app.entity.enquiry.EnquiryList;
import app.entity.user.Staff;
import app.ui.camp.infomationview.OverlayCampInfoDisplayView;
import app.ui.overlayactions.OverlayChooseBox;
import app.ui.overlayactions.OverlayTextInputAction;
import app.ui.widgets.TEXT_ALIGNMENT;
import app.ui.widgets.WidgetButton;
import app.ui.widgets.WidgetLabel;
import app.ui.widgets.WidgetPageSelection;
import app.ui.windows.ICallBack;
import app.ui.windows.Window;

import java.util.ArrayList;

/**
 * OverlayCampInfoDisplayEnquiriesStaff extends OverlayCampInfoDisplayView and implements ICallBack.
 * Manages the display and interaction with camp enquiries within an overlay view for staff members.
 */
public class OverlayCampInfoDisplayEnquiriesStaff extends OverlayCampInfoDisplayView implements ICallBack {

    protected Staff staff;
    protected Window mainWindow;
    protected WidgetPageSelection participantsView;
    protected ArrayList<Enquiry> enquiryList;
    protected Enquiry selectedEnq;
    protected boolean replyEnq = false;

    public OverlayCampInfoDisplayEnquiriesStaff(int x, int y, int offsetY, int offsetX, String windowName, Camp camp,
            Staff staff, Window mainWindow) {
        super(x, y, offsetY, offsetX, windowName, camp);

        this.mainWindow = mainWindow;

        this.camp = camp;
        this.staff = staff;

        enquiryList = new ArrayList<>();

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.getEnquiryRepository().filterByCamp(camp);
        for (Enquiry enquiry : enquires) {
            ArrayList<String> tmp = new ArrayList<String>();
            enquiryList.add(enquiry);
            if(enquiry.getSender().equals(staff))
                tmp.add("Question: " + enquiry.getQuestion() + " ( From You )");
            else
                tmp.add("Question: " + enquiry.getQuestion() + " ( From " + enquiry.getSender().getName() + " )");
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
                OverlayCampInfoDisplayEnquiriesStaff.this);

        addWidget(participantsView);

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


    /**
     * Updates the list of camp enquiries displayed in the overlay.
     */
    public void updateEnquiries() {
        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.getEnquiryRepository().filterByCamp(camp);
        enquiryList.clear();
        for (Enquiry enquiry : enquires) {
            ArrayList<String> tmp = new ArrayList<String>();
            enquiryList.add(enquiry);
            if(enquiry.getSender().equals(staff))
                tmp.add("Question: " + enquiry.getQuestion() + " ( From You )");
            else
                tmp.add("Question: " + enquiry.getQuestion() + " ( From " + enquiry.getSender().getName() + " )");
            if (enquiry.getAnswer() == null || enquiry.getAnswer().equals(""))
                tmp.add("    Not Answer Yet. Our coordinators will answer soon.");
            else
                tmp.add("    : " + enquiry.getAnswer() + " ( Answered By " + enquiry.getAnsweredBy().getName() + ")");
            enqList.add(tmp);
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
                ArrayList<String> options = new ArrayList<>();
                if (selectedEnq.getAnswer() == null || selectedEnq.getAnswer().equals(""))
                    options.add("Reply Enquiry");
                options.add("Cancel");
                WidgetButton buttonPosition = participantsView.getSelectionsButton()
                        .get(participantsView.getSelectedOption()).get(0);
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(),
                        getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options,
                        OverlayCampInfoDisplayEnquiriesStaff.this);
                mainWindow.addOverlay(overlayChooseBox);
                participantsView.clearSelectedOption();
            }
        }
        if (replyEnq) {
            replyEnq = false;
            OverlayTextInputAction overlayTextInputAction = new OverlayTextInputAction(60, getY() / 2 - 8, getX() + getX() / 2 - 30,
                    "Info", "Reply Enquiry:", OverlayCampInfoDisplayEnquiriesStaff.this);
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
        if (choseString.equals("Reply Enquiry")) {
            replyEnq = true;
        } else if (chose == 255) {
            CampEnquiryController.answerEnquiry(selectedEnq, staff, choseString);
//            RepositoryCollection.getEnquiryRepository().update(selectedEnq);
            updateEnquiries();
        }
    }
}
