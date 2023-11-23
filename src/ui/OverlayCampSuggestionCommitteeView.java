package ui;

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

import java.util.ArrayList;

public class OverlayCampSuggestionCommitteeView extends WindowOverlayClass implements ICallBack {

    Camp camp;
    Student student;
    protected WidgetPageSelection allSuggestions;
    protected WidgetButton exitButton;
    protected WidgetButton createSuggestionButton;
    protected Window mainWindow;
    protected ArrayList<Suggestion> suggestionArrayList;
    protected Suggestion selectedSuggestion;
    protected OverlaySuggestionInfoDisplayRaw overlaySuggestionInfoDisplayRawToBeAdded;
    protected OverlayCampInfoDisplaySuggestion overlayCampInfoDisplaySuggestionPending;

    public OverlayCampSuggestionCommitteeView(int x, int y, int offsetY, int offsetX, String windowName, Camp camp,
                                              Student student, Window mainWindow) {
        super(y, x, offsetY, offsetX, windowName);

        this.camp = camp;
        this.student = student;
        this.mainWindow = mainWindow;

        suggestionArrayList = new ArrayList<>();

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        SuggestionList suggestions = RepositoryCollection.getSuggestionRepository().filterByCamp(camp)
                .filterBySender(student);
        for (Suggestion suggestion : suggestions) {
            suggestionArrayList.add(suggestion);
            ArrayList<String> tmp = new ArrayList<String>();
            String status = "";
            if (suggestion.getStatus() == SuggestionStatus.PENDING) {
                status = " [ PENDING ]";
            } else if (suggestion.getStatus() == SuggestionStatus.APPROVED) {
                status = " [ APPROVED ]";
            } else if (suggestion.getStatus() == SuggestionStatus.REJECTED) {
                status = " [ REJECTED ]";
            }
            tmp.add("Suggestion: " + suggestion.getSuggestion().getID() + status);
            String changed = "";
            if (suggestion.getSuggestion().getLocation() != null) {
                changed += "Location, ";
            }
            if (suggestion.getSuggestion().getStartDate() != null || suggestion.getSuggestion().getEndDate() != null) {
                changed += "Date, ";
            }
            if (suggestion.getSuggestion().getDescription() != null && !suggestion.getSuggestion().getDescription().equals(suggestion.getOriginalCamp().getDescription())) {
                changed += "Description, ";
            }
            if (suggestion.getSuggestion().getName() != null) {
                changed += "Name, ";
            }
            if (suggestion.getSuggestion().getCloseRegistrationDate() != null) {
                changed += "Registration Close, ";
            }
            if (suggestion.getSuggestion().getSchool() != null) {
                changed += "Faculty, ";
            }
            if (suggestion.getSuggestion().getTotalSlots() != null) {
                changed += "Slots, ";
            }
            if (changed.equals("")) {
                tmp.add("    Nothing Changed.");
            } else {
                changed = changed.substring(0, changed.length() - 3);
                tmp.add("    Changed " + changed);
            }
            enqList.add(tmp);
        }

        allSuggestions = new WidgetPageSelection(3, 3, getX() - 10, getY() - 12, "Suggestions", enqList,
                OverlayCampSuggestionCommitteeView.this);
        allSuggestions.updateList(enqList);

        addWidget(allSuggestions);

        createSuggestionButton = new WidgetButton(3, getY() - 8, getLenX() - 10, "Create Suggestion");
        addWidget(createSuggestionButton);

        exitButton = new WidgetButton(3, getY() - 6, getLenX() - 10, "Go Back");
        addWidget(exitButton);

    }

    public void updateSuggestionList() {
        suggestionArrayList = new ArrayList<>();

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        SuggestionList suggestions = RepositoryCollection.getSuggestionRepository().filterByCamp(camp);
        for (Suggestion suggestion : suggestions) {
            suggestionArrayList.add(suggestion);
            ArrayList<String> tmp = new ArrayList<String>();
            String status = "";
            if (suggestion.getStatus() == SuggestionStatus.PENDING) {
                status = " [ PENDING ]";
            } else if (suggestion.getStatus() == SuggestionStatus.APPROVED) {
                status = " [ APPROVED ]";
            } else if (suggestion.getStatus() == SuggestionStatus.REJECTED) {
                status = " [ REJECTED ]";
            }
            tmp.add("Suggestion: " + suggestion.getSuggestion().getID() + status);
            String changed = "";
            if (suggestion.getSuggestion().getLocation() != null) {
                changed += "Location, ";
            }
            if (suggestion.getSuggestion().getStartDate() != null || suggestion.getSuggestion().getEndDate() != null) {
                changed += "Date, ";
            }
            if (suggestion.getSuggestion().getDescription() != null && !suggestion.getSuggestion().getDescription().equals(suggestion.getOriginalCamp().getDescription())) {
                changed += "Description, ";
            }
            if (suggestion.getSuggestion().getName() != null) {
                changed += "Name, ";
            }
            if (suggestion.getSuggestion().getCloseRegistrationDate() != null) {
                changed += "Registration Close, ";
            }
            if (suggestion.getSuggestion().getSchool() != null) {
                changed += "Faculty, ";
            }
            if (suggestion.getSuggestion().getTotalSlots() != null) {
                changed += "Slots, ";
            }
            if (changed.equals("")) {
                tmp.add("    Nothing Changed.");
            } else {
                changed = changed.substring(0, changed.length() - 3);
                tmp.add("    Changed " + changed);
            }
            enqList.add(tmp);
        }
        allSuggestions.updateList(enqList);

    }

    public void messageLoop() {
        super.messageLoop();
        if (exitButton.getPressed()) {
            exitButton.clearPressed();
            setDestroy();
        }
        if (createSuggestionButton.getPressed()) {
            createSuggestionButton.clearPressed();
            OverlayCampInfoDisplaySuggestion overlayCampInfoDisplaySuggestion = new OverlayCampInfoDisplaySuggestion(
                    getX(), getY(), offsetY, offsetX, "Create Suggestion", camp, student,
                    OverlayCampSuggestionCommitteeView.this, null);
            mainWindow.addOverlay(overlayCampInfoDisplaySuggestion);
        }
        if (allSuggestions.getSelectedOption() != -1) {
            selectedSuggestion = suggestionArrayList.get(allSuggestions.getSelectedOption());
            if (selectedSuggestion != null) {
                ArrayList<String> options = new ArrayList<>();
                options.add("View");
                if(selectedSuggestion.getStatus() == SuggestionStatus.PENDING) {
                    options.add("Edit");
                    options.add("Delete");
                }
                options.add("Cancel");
                WidgetButton buttonPosition = allSuggestions.getSelectionsButton()
                        .get(allSuggestions.getSelectedOption()).get(0);
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(),
                        getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options,
                        OverlayCampSuggestionCommitteeView.this);
                mainWindow.addOverlay(overlayChooseBox);
            }
            allSuggestions.clearSelectedOption();
        }
        if (overlaySuggestionInfoDisplayRawToBeAdded != null) {
            mainWindow.addOverlay(overlaySuggestionInfoDisplayRawToBeAdded);
            overlaySuggestionInfoDisplayRawToBeAdded = null;
        }
        if (overlayCampInfoDisplaySuggestionPending != null) {
            mainWindow.addOverlay(overlayCampInfoDisplaySuggestionPending);
            overlayCampInfoDisplaySuggestionPending = null;
        }
    }

    public void onExit() {
        super.onExit();
    }

    @Override
    public void onWindowFinished(int chose, String choseString) {
        if (choseString.equals("View") && selectedSuggestion != null) {
            overlaySuggestionInfoDisplayRawToBeAdded = new OverlaySuggestionInfoDisplayRaw(getX(), getY(), offsetY,
                    offsetX, "Suggestion For Camp " + selectedSuggestion.getSuggestion().getName(), selectedSuggestion);
        } else if (choseString.equals("Delete") && selectedSuggestion != null) {
            RepositoryCollection.getSuggestionRepository().remove(selectedSuggestion);
            updateSuggestionList();
        } else if (choseString.equals("Edit") && selectedSuggestion != null) {
            overlayCampInfoDisplaySuggestionPending = new OverlayCampInfoDisplaySuggestion(getX(), getY(), offsetY,
                    offsetX, "Edit Suggestion", camp, student, OverlayCampSuggestionCommitteeView.this, selectedSuggestion);
        }
        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        SuggestionList suggestions = RepositoryCollection.getSuggestionRepository().filterByCamp(camp)
                .filterBySender(student);
        allSuggestions.updateList(enqList);
        updateSuggestionList();
    }
}
