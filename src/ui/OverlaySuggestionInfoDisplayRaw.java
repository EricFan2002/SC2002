package ui;

import entity.camp.Camp;
import entity.suggestion.Suggestion;
import ui.widgets.*;
import ui.windows.WindowOverlayClass;

import java.text.SimpleDateFormat;

public class OverlaySuggestionInfoDisplayRaw extends WindowOverlayClass {

    Suggestion suggestion;
    protected WidgetButton exitButton;
    protected WidgetTextBox textBoxCName;
    protected WidgetTextBox textBoxDescription;
    protected WidgetTextBox textBoxDStart;
    protected WidgetTextBox textBoxDEnd;
    protected WidgetTextBox textBoxDClose;
    protected WidgetTextBox textBoxSchool;
    protected WidgetTextBox textBoxLocation;
    protected WidgetTextBox textBoxSlotsC;
    protected WidgetTextBox textBoxSlots;

    public OverlaySuggestionInfoDisplayRaw(int x, int y, int offsetY, int offsetX, String windowName, Suggestion suggestion) {
        super(y , x, offsetY, offsetX, windowName);

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Camp camp = suggestion.getOriginalCamp();

//        WidgetLabel widgetLabel = new WidgetLabel(3, 2, getLenX() - 10, "Camp Detail About: " + camp.getName(), TEXT_ALIGNMENT.ALIGN_MID);
//        addWidget(widgetLabel);

        // Name
        WidgetLabel labelName = new WidgetLabel(3, 3, 15, "Name:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelName);
        if(suggestion.getSuggestion().getName() == null)
            textBoxCName = new WidgetTextBox(19, 3, getLenX() - 24, camp.getName() + " (Unchanged)");
        else
            textBoxCName = new WidgetTextBox(19, 3, getLenX() - 24, camp.getName() + " → " + suggestion.getSuggestion().getName());
        addWidget(textBoxCName);
        textBoxCName.setSkipSelection(true);

// Description
        WidgetLabel labelDescription = new WidgetLabel(3, 4, 15, "Description:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDescription);
        if (suggestion.getSuggestion().getDescription() == null)
            textBoxDescription = new WidgetTextBox(19, 4, getLenX() - 24, camp.getDescription() + " (Unchanged)");
        else
            textBoxDescription = new WidgetTextBox(19, 4, getLenX() - 24, camp.getDescription() + " → " + suggestion.getSuggestion().getDescription());
        addWidget(textBoxDescription);
        textBoxDescription.setSkipSelection(true);

// Start Date
        WidgetLabel labelDStart = new WidgetLabel(3, 5, 15, "Start Date:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDStart);
        if (suggestion.getSuggestion().getStartDate() == null)
            textBoxDStart = new WidgetTextBox(19, 5, getLenX() - 24, ft.format(camp.getStartDate()) + " (Unchanged)");
        else
            textBoxDStart = new WidgetTextBox(19, 5, getLenX() - 24, ft.format(camp.getStartDate()) + " → " + ft.format(suggestion.getSuggestion().getStartDate()));
        addWidget(textBoxDStart);
        textBoxDStart.setSkipSelection(true);

// End Date
        WidgetLabel labelDEnd = new WidgetLabel(3, 6, 15, "End Date:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDEnd);
        if (suggestion.getSuggestion().getEndDate() == null)
            textBoxDEnd = new WidgetTextBox(19, 6, getLenX() - 24, ft.format(camp.getEndDate()) + " (Unchanged)");
        else
            textBoxDEnd = new WidgetTextBox(19, 6, getLenX() - 24, ft.format(camp.getEndDate()) + " → " + ft.format(suggestion.getSuggestion().getEndDate()));
        addWidget(textBoxDEnd);
        textBoxDEnd.setSkipSelection(true);

// Registration Close
        WidgetLabel labelDClose = new WidgetLabel(3, 7, 15, "Reg Close:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelDClose);
        if (suggestion.getSuggestion().getCloseRegistrationDate() == null)
            textBoxDClose = new WidgetTextBox(19, 7, getLenX() - 24, ft.format(camp.getCloseRegistrationDate()) + " (Unchanged)");
        else
            textBoxDClose = new WidgetTextBox(19, 7, getLenX() - 24, ft.format(camp.getCloseRegistrationDate()) + " → " + ft.format(suggestion.getSuggestion().getCloseRegistrationDate()));
        addWidget(textBoxDClose);
        textBoxDClose.setSkipSelection(true);

// Faculty
        WidgetLabel labelSchool = new WidgetLabel(3, 8, 15, "Faculty:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSchool);
        if (suggestion.getSuggestion().getSchool() == null)
            textBoxSchool = new WidgetTextBox(19, 8, getLenX() - 24, camp.getSchool() + " (Unchanged)");
        else
            textBoxSchool = new WidgetTextBox(19, 8, getLenX() - 24, camp.getSchool() + " → " + suggestion.getSuggestion().getSchool());
        addWidget(textBoxSchool);
        textBoxSchool.setSkipSelection(true);

// Location
        WidgetLabel labelLocation = new WidgetLabel(3, 9, 15, "Location:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelLocation);
        if (suggestion.getSuggestion().getLocation() == null)
            textBoxLocation = new WidgetTextBox(19, 9, getLenX() - 24, camp.getLocation() + " (Unchanged)");
        else
            textBoxLocation = new WidgetTextBox(19, 9, getLenX() - 24, camp.getLocation() + " → " + suggestion.getSuggestion().getLocation());
        addWidget(textBoxLocation);
        textBoxLocation.setSkipSelection(true);

// Slots
        WidgetLabel labelSlots = new WidgetLabel(3, 10, 15, "Slots:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSlots);
        if (suggestion.getSuggestion().getTotalSlots() == null)
            textBoxSlots = new WidgetTextBox(19, 10, getLenX() - 24, camp.getTotalSlots().toString() + " (Unchanged)");
        else
            textBoxSlots = new WidgetTextBox(19, 10, getLenX() - 24, camp.getTotalSlots().toString() + " → " + suggestion.getSuggestion().getTotalSlots().toString());
        addWidget(textBoxSlots);
        textBoxSlots.setSkipSelection(true);



// Slots Committee
        WidgetLabel labelSlotsC = new WidgetLabel(3, 11, 15, "Committee:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(labelSlotsC);
        textBoxSlotsC = new WidgetTextBox(19, 11, getLenX() - 24, "10"); // Assuming this value doesn't come from 'suggestion'
        addWidget(textBoxSlotsC);
        textBoxSlotsC.setSkipSelection(true);

        // Slots Committee
        WidgetLabel suggestedLabel = new WidgetLabel(3, 12, 15, "Suggested By:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(suggestedLabel);
        textBoxSlotsC = new WidgetTextBox(19, 12, getLenX() - 24, suggestion.getSender().getName()); // Assuming this value doesn't come from 'suggestion'
        addWidget(textBoxSlotsC);
        textBoxSlotsC.setSkipSelection(true);

        // Go Back Button
        exitButton = new WidgetButton(3, getY() - 6, getLenX() - 10, "Go Back");
        addWidget(exitButton);

    }

    public void messageLoop() {
        super.messageLoop();
        if(exitButton.getPressed()){
            exitButton.clearPressed();
            setDestroy();
        }
    }
    public void onExit(){
        super.onExit();
    }
}
