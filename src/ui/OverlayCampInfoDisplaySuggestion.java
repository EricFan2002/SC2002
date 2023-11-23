package ui;

import controller.camp.CampSuggestionController;
import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.camp.CampDetails;
import entity.suggestion.Suggestion;
import entity.suggestion.SuggestionList;
import entity.user.Student;
import ui.widgets.WidgetButton;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OverlayCampInfoDisplaySuggestion extends OverlayCampInfoDisplay {

    protected Camp camp;
    protected Student student;
    protected Window mainWindow;
    protected WidgetButton submitButton;
    protected Suggestion editSuggestion;

    public OverlayCampInfoDisplaySuggestion(int x, int y, int offsetY, int offsetX, String windowName, Camp camp,
            Student student, Window mainWindow, Suggestion suggestion) {
        super(x, y, offsetY, offsetX, windowName, camp);
        this.student = student;
        this.mainWindow = mainWindow;
        this.camp = camp;
        this.editSuggestion = suggestion;
        submitButton = new WidgetButton(3, 20 + getLenY() / 2, getLenX() - 10, "Submit Suggestion");
        addWidget(submitButton);
        removeWidget(exitButton);
        addWidget(exitButton);
        if (suggestion != null && suggestion.getSuggestion() != null) {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            // Name
            if (suggestion.getSuggestion().getName() != null) {
                textBoxCName.setText(suggestion.getSuggestion().getName());
            }

            // Description
            if (suggestion.getSuggestion().getDescription() != null) {
                textBoxDescription.setText(suggestion.getSuggestion().getDescription());
            }

            // Start Date
            if (suggestion.getSuggestion().getStartDate() != null) {
                textBoxDStart.setText(ft.format(suggestion.getSuggestion().getStartDate()));
            }

            // End Date
            if (suggestion.getSuggestion().getEndDate() != null) {
                textBoxDEnd.setText(ft.format(suggestion.getSuggestion().getEndDate()));
            }

            // Registration Close
            if (suggestion.getSuggestion().getCloseRegistrationDate() != null) {
                textBoxDClose.setText(ft.format(suggestion.getSuggestion().getCloseRegistrationDate()));
            }

            // Faculty (School)
            if (suggestion.getSuggestion().getSchool() != null) {
                textBoxSchool.setText(suggestion.getSuggestion().getSchool());
            }

            // Location
            if (suggestion.getSuggestion().getLocation() != null) {
                textBoxLocation.setText(suggestion.getSuggestion().getLocation());
            }

            // Slots
            if (suggestion.getSuggestion().getTotalSlots() != null) {
                textBoxSlots.setText(suggestion.getSuggestion().getTotalSlots().toString());
            }

        }

    }

    public void messageLoop() {
        super.messageLoop();
        if (exitButton.getPressed()) {
            exitButton.clearPressed();
            setDestroy();
        }
        if (submitButton.getPressed()) {
            submitButton.clearPressed();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            CampDetails suggestion = camp.createSuggestionPlan();
            if(editSuggestion != null) {
                suggestion = editSuggestion.getSuggestion();
            }
            suggestion.setDescription(textBoxDescription.getText());
            try {
                suggestion.setStartDate(ft.parse(textBoxDStart.getText()));
                suggestion.setEndDate(ft.parse(textBoxDEnd.getText()));
                suggestion.setCloseRegistrationDate(ft.parse(textBoxDClose.getText()));
            } catch (ParseException e) {
            }
            if (!textBoxCName.getText().equals(camp.getName()))
                suggestion.setName(textBoxCName.getText());
            if (!textBoxSchool.getText().equals(camp.getSchool()))
                suggestion.setSchool(textBoxSchool.getText());
            if (!textBoxLocation.getText().equals(camp.getLocation()))
                suggestion.setLocation(textBoxLocation.getText());
            if (!textBoxVis.getPressed() == (camp.isVisible()))
                suggestion.setVisibility(textBoxVis.getPressed());
            if (!textBoxDescription.getText().equals(camp.getDescription()))
                suggestion.setDescription(textBoxDescription.getText());
            if (editSuggestion != null) {
                editSuggestion.setSuggestion(suggestion);
                CampSuggestionController.updateSuggestion(editSuggestion);
            } else {
                editSuggestion = new Suggestion(student, suggestion, camp);
                RepositoryCollection.getSuggestionRepository().add(editSuggestion);
            }
            if (mainWindow instanceof ICallBack) {
                ((ICallBack) mainWindow).onWindowFinished(0, "");
            }
            setDestroy();
        }
    }

    public void onExit() {
        super.onExit();
    }
}
