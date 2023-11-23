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
            suggestion.setDescription(textBoxDescription.getText());
            try {
                suggestion.setStartDate(ft.parse(textBoxDescription.getText()));
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
