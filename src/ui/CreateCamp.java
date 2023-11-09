package ui;

import ui.widgets.*;
import ui.windows.Window;

public class CreateCamp extends Window {
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

    WidgetTextBox IDTextBox;

    WidgetTextBox nameTextBox;
    WidgetTextBox descriptionTextBox;

    WidgetTextBox startDateTextBox;
    WidgetTextBox endDateTextBox;
    WidgetTextBox closeRegistrationDateTextBox;
    WidgetTextBox locationTextBox;
    WidgetButton confirmButton;
    WidgetButton cancelButton;
    private int loginSwitchToWindowIndex;
    public CreateCamp(){
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
        confirmButton = new WidgetButton(4, 22, 49, "Confirm");
        addWidget(confirmButton);
        cancelButton = new WidgetButton(4, 24, 49, "Back");
        addWidget(cancelButton);
        setPointer(confirmButton);
    }

//    public void messageLoop() {
//        super.messageLoop();
//        if(confirmButton.getPressed()){
//            switchToWindow = loginSwitchToWindowIndex;
//        }
//        if(cancelButton.getPressed()){
//            switchToWindow = loginSwitchToWindowIndex;
//        }
//    }
//    public void onExit(){
//        confirmButton.clearPressed();
//        cancelButton.clearPressed();
//    }
}

