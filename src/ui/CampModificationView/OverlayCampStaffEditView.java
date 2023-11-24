package ui.CampModificationView;

import controller.camp.CampModificationController;
import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.user.Staff;
import ui.CampInfomationView.OverlayCampInfoDisplayWithParticipantsView;
import ui.OverlayActions.OverlayNotification;
import ui.widgets.WidgetButton;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OverlayCampStaffEditView extends OverlayCampInfoDisplayWithParticipantsView {

    protected Camp camp;
    protected Staff staff;
    protected Window mainWindow;
    protected WidgetButton submitButton;

    public OverlayCampStaffEditView(int x, int y, int offsetY, int offsetX, String windowName, Camp camp, Staff staff, Window mainWindow) {
        super(x, y, offsetY, offsetX, windowName, camp);
        this.staff = staff;
        this.mainWindow = mainWindow;
        this.camp = camp;
        submitButton = new WidgetButton(3, 20 + getLenY() / 2, getLenX() - 10, "Submit Change");
        addWidget(submitButton);
        removeWidget(exitButton);
        addWidget(exitButton);
    }

    public void messageLoop() {
        if(exitButton.getPressed()){
            exitButton.clearPressed();
            setDestroy();
            if(mainWindow instanceof ICallBack){
                ((ICallBack)mainWindow).onWindowFinished(254, "cancel");
            }
        }
        super.messageLoop();
        if(!textBoxVis.getPressed()){
            if(camp.getAttendees().size() != 0 || camp.getCommittees().size() != 0){
                OverlayNotification overlayNotification = new OverlayNotification(40, getY() / 2 - 8,
                        getX() + getX() / 2 - 20, "Error", "Party Still Have Participants!", OverlayCampStaffEditView.this);
                mainWindow.addOverlay(overlayNotification);
                textBoxVis.setPressed();
            }
        }
        if(submitButton.getPressed()){
            submitButton.clearPressed();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            CampDetails suggestion = camp.createSuggestionPlan();
            suggestion.setDescription(textBoxDescription.getText());
            try {
                suggestion.setStartDate(ft.parse(textBoxDStart.getText()));
                suggestion.setEndDate(ft.parse(textBoxDEnd.getText()));
                suggestion.setCloseRegistrationDate(ft.parse(textBoxDClose.getText()));
            } catch (ParseException e) {
            }
            if(!textBoxSlots.getText().equals(camp.getTotalSlots().toString())){
                try{
                    suggestion.setTotalSlots(Integer.parseInt(textBoxSlots.getText()));
                }
                catch (Exception e){
                }
            }
            if(!textBoxCName.getText().equals(camp.getName()))
                suggestion.setName(textBoxCName.getText());
            if(!textBoxSchool.getText().equals(camp.getSchool()))
                suggestion.setSchool(textBoxSchool.getText());
            if(!textBoxLocation.getText().equals(camp.getLocation()))
                suggestion.setLocation(textBoxLocation.getText());
            if(!textBoxVis.getPressed() == (camp.isVisible()))
                suggestion.setVisibility(textBoxVis.getPressed());
            if(!textBoxDescription.getText().equals(camp.getDescription()))
                suggestion.setDescription(textBoxDescription.getText());
            if(textBoxVis.getPressed() != camp.isVisible()){
                suggestion.setVisibility(textBoxVis.getPressed());
            }
            CampModificationController.editCamp(suggestion);
            if(mainWindow instanceof ICallBack){
                ((ICallBack)mainWindow).onWindowFinished(254, "EDITED");
            }
            setDestroy();
        }
    }
    public void onExit(){
        super.onExit();
    }
}