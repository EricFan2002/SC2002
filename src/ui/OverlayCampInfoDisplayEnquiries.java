package ui;

import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.enquiry.Enquiry;
import entity.enquiry.EnquiryList;
import entity.user.Student;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.time.LocalDate;
import java.util.ArrayList;

public class OverlayCampInfoDisplayEnquiries extends OverlayCampInfoDisplayRaw implements ICallBack {

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


    public OverlayCampInfoDisplayEnquiries(int x, int y, int offsetY, int offsetX, String windowName, Camp camp, Student student, Window mainWindow) {
        super(x , y, offsetY, offsetX, windowName, camp);

        this.mainWindow = mainWindow;

        this.camp = camp;
        this.student = student;

        enquiryList = new ArrayList<>();

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.enquiryRepository.getAll().filterByCamp(camp).filterBySender(student);
        for(Enquiry enquiry : enquires){
            ArrayList<String> tmp = new ArrayList<String>();
            enquiryList.add(enquiry);
            tmp.add("Question: " + enquiry.getQuestion());
            if(enquiry.getAnswer() == null || enquiry.getAnswer().equals(""))
                tmp.add("    Not Answer Yet. Our coordinators will answer soon.");
            else
                tmp.add("    : " + enquiry.getAnswer() + " ( Answered By " + enquiry.getAnsweredBy().getName() + ")");
            enqList.add(tmp);
        }

        WidgetLabel labelParticipants = new WidgetLabel(3, 15, 15, "Your Enquires:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelParticipants);

        participantsView = new WidgetPageSelection(3, 16, getLenX() - 8, getLenY() / 2, "Participants", enqList, OverlayCampInfoDisplayEnquiries.this);
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

    public void updateEnquiries(){
        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        EnquiryList enquires = RepositoryCollection.enquiryRepository.getAll().filterByCamp(camp).filterBySender(student);
        enquiryList.clear();
        for(Enquiry enquiry : enquires){
            enquiryList.add(enquiry);
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add("Question: " + enquiry.getQuestion());
            if(enquiry.getAnswer() == null || enquiry.getAnswer().equals(""))
                tmp.add("    Not Answer Yet.");
            else
                tmp.add("    : " + enquiry.getAnswer() + " ( Answered By " + enquiry.getAnsweredBy().getName() + " )");
            enqList.add(tmp);
        }
        participantsView.updateList(enqList);
    }

    public void messageLoop() {
        super.messageLoop();
        if(participantsView.getSelectedOption() != -1){
            selectedEnq = enquiryList.get(participantsView.getSelectedOption());
            if(selectedEnq != null) {
                if (selectedEnq.getAnswer() == null || selectedEnq.getAnswer().equals("")) {
                    ArrayList<String> options = new ArrayList<>();
                    options.add("Delete Enquiry");
                    options.add("Edit Enquiry");
                    WidgetButton buttonPosition = participantsView.getSelectionsButton().get(participantsView.getSelectedOption()).get(0);
                    OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(), getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options, OverlayCampInfoDisplayEnquiries.this);
                    mainWindow.addOverlay(overlayChooseBox);
                    participantsView.clearSelectedOption();
                } else {
                    ArrayList<String> options = new ArrayList<>();
                    options.add("Answered Enquiry");
                    options.add("Cannot Edit Anymore.");
                    WidgetButton buttonPosition = participantsView.getSelectionsButton().get(participantsView.getSelectedOption()).get(0);
                    OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(), getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options, OverlayCampInfoDisplayEnquiries.this);
                    mainWindow.addOverlay(overlayChooseBox);
                    participantsView.clearSelectedOption();
                }
            }
        }
        if(sendButton.getPressed()){
            sendButton.clearPressed();
            if(textBoxEnq.getText().equals("")){
                OverlayNotification overlayNotification = new OverlayNotification(40,  getY()/2 - 8, getX()  + getX() / 2- 20, "Info", "Empty Enquiry!", OverlayCampInfoDisplayEnquiries.this);
                mainWindow.addOverlay(overlayNotification);
            }
            else {
                Enquiry enquiry = new Enquiry(String.valueOf(System.currentTimeMillis()), student, textBoxEnq.getText(), camp);
                RepositoryCollection.enquiryRepository.insert(enquiry);
                OverlayNotification overlayNotification = new OverlayNotification(40,  getY()/2 - 8, getX()  + getX() / 2- 20, "Info", "Enquiry Sent!", OverlayCampInfoDisplayEnquiries.this);
                mainWindow.addOverlay(overlayNotification);
                updateEnquiries();
            }
        }
        if(editEnq){
            editEnq = false;
            OverlayTextInput overlayTextInput = new OverlayTextInput(60,  getY()/2 - 8, getX()  + getX() / 2- 30, "Info", "Change Enquiry To:", OverlayCampInfoDisplayEnquiries.this);
            mainWindow.addOverlay(overlayTextInput);
        }
        if(exitButton.getPressed()){
            exitButton.clearPressed();
            setDestroy();
        }
    }
    public void onExit(){
        super.onExit();
    }

    @Override
    public void onWindowFinished(int chose, String choseString) {
        if(choseString.equals("Delete Enquiry")){
            if(selectedEnq != null) {
                RepositoryCollection.enquiryRepository.remove(selectedEnq);
                updateEnquiries();
            }
        }
        else if(choseString.equals("Edit Enquiry")){
            editEnq = true;
        }
        else if(chose == 255){
            selectedEnq.setQuestion(choseString);
            RepositoryCollection.enquiryRepository.update(selectedEnq);
            updateEnquiries();
        }
    }
}
