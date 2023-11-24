package ui.CampModificationView;

import controller.camp.CampModificationController;
import controller.user.UserController;
import ui.OverlayActions.OverlayChooseBox;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;

import entity.camp.Camp;
import entity.user.Staff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents the view for creating a new camp.
 * Extends the Window class and implements the ICallBack interface.
 */
public class CreateCampView extends Window implements ICallBack {
    WidgetLabel IDLabel;
    WidgetLabel nameLabel;
    WidgetLabel descriptionLabel;
    WidgetToggle visibilityToggle;
    WidgetLabel startDateLabel;
    WidgetLabel endDateLabel;
    WidgetLabel closeRegistrationDateLabel;
    WidgetLabel schoolLabel;
    WidgetButton schoolButton;
    WidgetLabel locationLabel;
    WidgetLabel totalSlotsLabel;

    WidgetTextBox IDTextBox;

    WidgetTextBox nameTextBox;
    WidgetTextBox descriptionTextBox;

    WidgetTextBox startDateTextBox;
    WidgetTextBox endDateTextBox;
    WidgetTextBox closeRegistrationDateTextBox;
    WidgetTextBox locationTextBox;
    WidgetTextBox totalSlotsTextBox;
    WidgetButton confirmButton;
    WidgetButton cancelButton;
    private int campListViewIndex;

    /**
     * Constructs a CreateCampView with a specific camp list view index.
     * @param campListViewIndex The index for the camp list view.
     */
    public CreateCampView(int campListViewIndex){
        super(30, 80, "Create Camp");
        WidgetLabel widgetLabel = new WidgetLabel(3, 3,40, "Create Camp", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        IDLabel = new WidgetLabel(1, 5,19, "ID:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(IDLabel);
        IDTextBox = new WidgetTextBox(29, 5,29, "", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(IDTextBox);
        nameLabel = new WidgetLabel(1, 7,19, "name:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(nameLabel);
        nameTextBox = new WidgetTextBox(29, 7,29, "", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(nameTextBox);
        descriptionLabel = new WidgetLabel(1, 9,19, "description:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(descriptionLabel);
        descriptionTextBox = new WidgetTextBox(29, 9,29, "", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(descriptionTextBox);
        startDateLabel = new WidgetLabel(1, 11,19, "startDate:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(startDateLabel);
        startDateTextBox = new WidgetTextBox(29, 11,29, "", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(startDateTextBox);
        endDateLabel = new WidgetLabel(1, 13,19, "endDate:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(endDateLabel);
        endDateTextBox = new WidgetTextBox(29, 13,29, "", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(endDateTextBox);
        closeRegistrationDateLabel = new WidgetLabel(1, 15,19, "RegcloseDate:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(closeRegistrationDateLabel);
        closeRegistrationDateTextBox = new WidgetTextBox(29, 15,29, "", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(closeRegistrationDateTextBox);
        schoolLabel = new WidgetLabel(1, 17, 19, "Select School", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(schoolLabel);
        schoolButton = new WidgetButton(29, 17, 29, "Click to Select", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(schoolButton);
        locationLabel = new WidgetLabel(1, 19,19, "location:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(locationLabel);
        locationTextBox = new WidgetTextBox(29, 19,29, "", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(locationTextBox);
        totalSlotsLabel = new WidgetLabel(1, 21,19, "totalSlots:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(totalSlotsLabel);
        totalSlotsTextBox = new WidgetTextBox(29, 21,29, "", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(totalSlotsTextBox);
        confirmButton = new WidgetButton(4, 22, 49, "Confirm");
        addWidget(confirmButton);
        cancelButton = new WidgetButton(4, 24, 49, "Back");
        addWidget(cancelButton);
        setPointer(confirmButton);
        this.campListViewIndex = campListViewIndex;
    }

    /**
     * Handles the message loop for the creation of a new camp.
     * Overrides the messageLoop method in the parent class.
     */
    public void messageLoop() {
        super.messageLoop();
        if (schoolButton.getPressed()) {
            List<String> options = new ArrayList<String>();
            options.add("SCSE");
            options.add("NBS");
            options.add("MAE");
            options.add("SSS");
            options.add("MSE");
            options.add("SBS");
            options.add("CEE");
            OverlayChooseBox overlayChooseBox = new OverlayChooseBox(26, schoolButton.getY(), schoolButton.getX(),  "Choose School", options, this);
            addOverlay(overlayChooseBox);
            schoolButton.clearPressed();
        }
        if (confirmButton.getPressed()) {
            String ID = IDTextBox.getText();
            String name = nameTextBox.getText();
            String description = descriptionTextBox.getText();
            Date startDate = new Date(startDateTextBox.getText());
            Date endDate = new Date(endDateTextBox.getText());
            Date closeRegistrationDate = new Date(closeRegistrationDateTextBox.getText());
            String location = locationTextBox.getText();
            String school = schoolButton.getText();
            int totalSlots = Integer.parseInt(totalSlotsTextBox.getText());
            Camp camp = new Camp(ID, name, description, true, startDate, endDate, closeRegistrationDate, school, location, (Staff)UserController.getCurrentUser(), totalSlots);
            CampModificationController.createCamp(camp);
            switchToWindow = campListViewIndex;
        }
        if (cancelButton.getPressed()) {
            switchToWindow = campListViewIndex;
        }
    }

    /**
     * Empty method as part of the ICallBack interface.
     * Not implemented in this class.
     */
    public void onExit(){

    }

    /**
     * Handles actions and updates after a window is finished.
     * Overrides the onWindowFinished method in the ICallBack interface.
     * @param chose The chosen action identifier.
     * @param choseString The string representation of the chosen action.
     */
    @Override
    public void onWindowFinished(int chose, String choseString) {
        schoolButton.setText(choseString);
    }
}

