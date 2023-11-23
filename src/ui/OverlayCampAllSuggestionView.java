package ui;

import controller.camp.CampSuggestionController;
import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.suggestion.Suggestion;
import entity.suggestion.SuggestionList;
import entity.suggestion.SuggestionStatus;
import entity.user.Student;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OverlayCampAllSuggestionView extends WindowOverlayClass implements ICallBack {

    Camp camp;
    Student student;
    protected WidgetPageSelection allSuggestions;
    protected WidgetButton exitButton;
    protected WidgetButton createSuggestionButton;
    protected Window mainWindow;
    protected ArrayList<Suggestion> suggestionArrayList;

    public OverlayCampAllSuggestionView(int x, int y, int offsetY, int offsetX, String windowName, Camp camp, Student student, Window mainWindow) {
        super(y , x, offsetY, offsetX, windowName);

        this.camp = camp;
        this.student = student;
        this.mainWindow = mainWindow;

        suggestionArrayList = new ArrayList<>();

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        SuggestionList suggestions = RepositoryCollection.suggestionRepository.getAll().filterByCamp(camp).filterBySender(student);
        for(Suggestion suggestion : suggestions){
            suggestionArrayList.add(suggestion);
            ArrayList<String> tmp = new ArrayList<String>();
            String status = "";
            if(suggestion.getStatus() == SuggestionStatus.PENDING){
                status = " ( PENDING )";
            }
            else if(suggestion.getStatus() == SuggestionStatus.APPROVED){
                status = " ( APPROVED )";
            }
            else if(suggestion.getStatus() == SuggestionStatus.REJECTED){
                status = " ( REJECTED )";
            }
            tmp.add("Suggestion: " + suggestion.getSuggestion().getID() + status);
            String changed = "";
            if(suggestion.getSuggestion().getLocation() != null){
                changed += "Location, ";
            }
            if(suggestion.getSuggestion().getStartDate() != null || suggestion.getSuggestion().getEndDate() != null){
                changed += "Date, ";
            }
            if(suggestion.getSuggestion().getDescription() != null){
                changed += "Description, ";
            }
            if(suggestion.getSuggestion().getName() != null){
                changed += "Name, ";
            }
            if(suggestion.getSuggestion().getCloseRegistrationDate() != null){
                changed += "Registration Close, ";
            }
            if(suggestion.getSuggestion().getSchool() != null){
                changed += "Faculty, ";
            }
            if(suggestion.getSuggestion().getTotalSlots() != null){
                changed += "Slots, ";
            }
            if(changed.equals("")){
                tmp.add("    Nothing Changed.");
            }
            else{
                tmp.add("    Changed " + changed);
            }
            enqList.add(tmp);
        }

        allSuggestions = new WidgetPageSelection(3, 3, getX() - 10, getY() - 12, "Suggestions", enqList, OverlayCampAllSuggestionView.this);
        allSuggestions.updateList(enqList);

        createSuggestionButton = new WidgetButton(3, getY() - 8, getLenX() - 10, "Create Suggestion");
        addWidget(createSuggestionButton);

        exitButton = new WidgetButton(3, getY() - 6, getLenX() - 10, "Go Back");
        addWidget(exitButton);

    }

    public void messageLoop() {
        super.messageLoop();
        if(exitButton.getPressed()){
            exitButton.clearPressed();
            setDestroy();
        }
        if(createSuggestionButton.getPressed()) {
            createSuggestionButton.clearPressed();
            OverlayCampInfoDisplaySuggestion overlayCampInfoDisplaySuggestion = new OverlayCampInfoDisplaySuggestion(getX(), getY(), offsetY, offsetX, "Create Suggestion", camp, student, OverlayCampAllSuggestionView.this, null);
            mainWindow.addOverlay(overlayCampInfoDisplaySuggestion);
        }
    }
    public void onExit(){
        super.onExit();
    }

    @Override
    public void onWindowFinished(int chose, String choseString) {
        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        SuggestionList suggestions = RepositoryCollection.suggestionRepository.getAll().filterByCamp(camp).filterBySender(student);
        for(Suggestion suggestion : suggestions){
            suggestionArrayList.add(suggestion);
            ArrayList<String> tmp = new ArrayList<String>();
            String status = "";
            if(suggestion.getStatus() == SuggestionStatus.PENDING){
                status = " ( PENDING )";
            }
            else if(suggestion.getStatus() == SuggestionStatus.APPROVED){
                status = " ( APPROVED )";
            }
            else if(suggestion.getStatus() == SuggestionStatus.REJECTED){
                status = " ( REJECTED )";
            }
            tmp.add("Suggestion: " + suggestion.getSuggestion().getID() + status);
            String changed = "";
            if(suggestion.getSuggestion().getLocation() != null){
                changed += "Location, ";
            }
            if(suggestion.getSuggestion().getStartDate() != null || suggestion.getSuggestion().getEndDate() != null){
                changed += "Date, ";
            }
            if(suggestion.getSuggestion().getDescription() != null){
                changed += "Description, ";
            }
            if(suggestion.getSuggestion().getName() != null){
                changed += "Name, ";
            }
            if(changed == ""){
                tmp.add("    Nothing Changed.");
            }
            else{
                tmp.add("    Changed " + changed);
            }
            enqList.add(tmp);
        }

        allSuggestions.updateList(enqList);
    }
}
