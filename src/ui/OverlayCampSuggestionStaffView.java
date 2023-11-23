package ui;

import controller.camp.CampModificationController;
import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.suggestion.Suggestion;
import entity.suggestion.SuggestionList;
import entity.suggestion.SuggestionStatus;
import entity.user.Staff;
import entity.user.Student;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetPageSelection;
import ui.windows.ICallBack;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.util.ArrayList;

public class OverlayCampSuggestionStaffView extends WindowOverlayClass implements ICallBack {

    Camp camp;
    Staff staff;
    protected WidgetPageSelection allSuggestions;
    protected WidgetButton exitButton;
    protected Window mainWindow;
    protected ArrayList<Suggestion> suggestionArrayList;
    protected Suggestion selectedSuggestion;
    protected OverlaySuggestionInfoDisplayRaw overlaySuggestionInfoDisplayRawToBeAdded;

    public OverlayCampSuggestionStaffView(int x, int y, int offsetY, int offsetX, String windowName, Camp camp,
            Staff staff, Window mainWindow) {
        super(y, x, offsetY, offsetX, windowName);

        this.camp = camp;
        this.staff = staff;
        this.mainWindow = mainWindow;

        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        allSuggestions = new WidgetPageSelection(3, 3, getX() - 10, getY() - 12, "Suggestions", enqList,
                OverlayCampSuggestionStaffView.this);

        addWidget(allSuggestions);

        exitButton = new WidgetButton(3, getY() - 6, getLenX() - 10, "Go Back");
        addWidget(exitButton);

        updateSuggestionList();

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
                status = " ( PENDING )";
            } else if (suggestion.getStatus() == SuggestionStatus.APPROVED) {
                status = " ( APPROVED )";
            } else if (suggestion.getStatus() == SuggestionStatus.REJECTED) {
                status = " ( REJECTED )";
            }
            tmp.add("Suggestion: " + suggestion.getSuggestion().getID() + status);
            String changed = "";
            if (suggestion.getSuggestion().getLocation() != null) {
                changed += "Location, ";
            }
            if (suggestion.getSuggestion().getStartDate() != null || suggestion.getSuggestion().getEndDate() != null) {
                changed += "Date, ";
            }
            if (suggestion.getSuggestion().getDescription() != null) {
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
                changed = changed.substring(0, changed.length() - 2);
                tmp.add("    Changed " + changed);
            }
            enqList.add(tmp);
        }
        allSuggestions.updateList(enqList);
    }

    @Override
    public void messageLoop() {
        super.messageLoop();
        if (exitButton.getPressed()) {
            exitButton.clearPressed();
            setDestroy();
        }
        if (allSuggestions.getSelectedOption() != -1) {
            selectedSuggestion = suggestionArrayList.get(allSuggestions.getSelectedOption());
            if (selectedSuggestion != null) {
                ArrayList<String> options = new ArrayList<>();
                options.add("View");
                options.add("Approve");
                options.add("Reject");
                options.add("Cancel");
                WidgetButton buttonPosition = allSuggestions.getSelectionsButton()
                        .get(allSuggestions.getSelectedOption()).get(0);
                OverlayChooseBox overlayChooseBox = new OverlayChooseBox(30, buttonPosition.getY(),
                        getX() + buttonPosition.getX() + (getLenX() / 2 - 15), "Actions", options,
                        OverlayCampSuggestionStaffView.this);
                mainWindow.addOverlay(overlayChooseBox);
            }
            allSuggestions.clearSelectedOption();
        }
        if (overlaySuggestionInfoDisplayRawToBeAdded != null) {
            mainWindow.addOverlay(overlaySuggestionInfoDisplayRawToBeAdded);
            overlaySuggestionInfoDisplayRawToBeAdded = null;
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
            // mainWindow.addOverlay(overlaySuggestionInfoDisplayRaw);
        } else if (choseString.equals("Approve") && selectedSuggestion != null) {
            selectedSuggestion.setStatus(SuggestionStatus.APPROVED);
            CampModificationController.editCamp(selectedSuggestion.getSuggestion());
            updateSuggestionList();
        } else if (choseString.equals("Reject") && selectedSuggestion != null) {
            selectedSuggestion.setStatus(SuggestionStatus.REJECTED);
            updateSuggestionList();
        }
        ArrayList<ArrayList<String>> enqList = new ArrayList<>();
        SuggestionList suggestions = RepositoryCollection.getSuggestionRepository().filterByCamp(camp);
        for (Suggestion suggestion : suggestions) {
            suggestionArrayList.add(suggestion);
            ArrayList<String> tmp = new ArrayList<String>();
            String status = "";
            if (suggestion.getStatus() == SuggestionStatus.PENDING) {
                status = " ( PENDING )";
            } else if (suggestion.getStatus() == SuggestionStatus.APPROVED) {
                status = " ( APPROVED )";
            } else if (suggestion.getStatus() == SuggestionStatus.REJECTED) {
                status = " ( REJECTED )";
            }
            tmp.add("Suggestion: " + suggestion.getSuggestion().getID() + status);
            String changed = "";
            if (suggestion.getSuggestion().getLocation() != null) {
                changed += "Location, ";
            }
            if (suggestion.getSuggestion().getStartDate() != null || suggestion.getSuggestion().getEndDate() != null) {
                changed += "Date, ";
            }
            if (suggestion.getSuggestion().getDescription() != null) {
                changed += "Description, ";
            }
            if (suggestion.getSuggestion().getName() != null) {
                changed += "Name, ";
            }
            if (changed == "") {
                tmp.add("    Nothing Changed.");
            } else {
                changed = changed.substring(0, changed.length() - 2);
                tmp.add("    Changed " + changed);
            }
            enqList.add(tmp);
        }

        allSuggestions.updateList(enqList);
    }
}
