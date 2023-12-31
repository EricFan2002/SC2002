package app.ui.camp.listview;

import app.controller.user.UserController;
import app.entity.RepositoryCollection;
import app.entity.camp.Camp;
import app.entity.camp.CampList;
import app.entity.enquiry.Enquiry;
import app.entity.report.CampReport;
import app.entity.report.EnquiryReport;
import app.entity.report.PerformanceReport;
import app.entity.report.Report;
import app.entity.suggestion.Suggestion;
import app.entity.suggestion.SuggestionStatus;
import app.entity.user.Staff;
import app.entity.user.Student;
import app.ui.overlayactions.OverlayTextInputAction;
import app.ui.overlayactions.OverlayTextInputActionToggles;
import app.ui.widgets.*;
import app.ui.windows.ICallBack;
import app.ui.windows.Window;
import app.utils.CSV;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a view displaying a list of camps with filtering options and functionalities.
 * It extends the Window class and implements the ICallBack interface.
 */
public class CampListView extends Window implements ICallBack {

    /**
     * Formats a string to create a line with a specific length and alignment.
     * @param part1 The first part of the line.
     * @param part2 The second part of the line.
     * @param length The total length of the line.
     * @return The formatted line.
     */
    private String formLine(String part1, String part2, int length) {
        int padding = length / 2 - part1.length();
        String res = part1;
        for (int i = 0; i < padding; i++) {
            res += " ";
        }
        res += "│ ";
        res += part2;
        return res;
    }

    protected WidgetLabel filterLabel1 = new WidgetLabel(1, 2, 19, "Date Filter:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter1Start = new WidgetTextBox(20, 2, getLenX() / 4 - 18, "");
    protected WidgetLabel filter1Comment = new WidgetLabel(20 + getLenX() / 4 - 19, 2, 3 , "-");
    protected WidgetTextBox filter1End = new WidgetTextBox(20 + getLenX() / 4 - 17, 2, getLenX() / 4 - 17, "");
    protected WidgetToggle filter1Enable = new WidgetToggle(getLenX() / 2 - 14, 2, 14, "Enable");
    protected WidgetLabel filterLabel2 = new WidgetLabel(1, 3, 19, "Name Filter:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter2 = new WidgetTextBox(20, 3, getLenX() / 2 - 35, "");
    protected WidgetToggle filter2Enable = new WidgetToggle(getLenX() / 2 - 14, 3, 14, "Enable");
    protected WidgetLabel filterLabel3 = new WidgetLabel(1, 4, 19, "Faculty Filter:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter3 = new WidgetTextBox(20, 4, getLenX() / 2 - 35, "");
    protected WidgetToggle filter3Enable = new WidgetToggle(getLenX() / 2 - 14, 4, 14, "Enable");
    protected WidgetLabel filterLabel4 = new WidgetLabel(1, 5, 19, "Staff Filter:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter4 = new WidgetTextBox(20, 5, getLenX() / 2 - 35, "");
    protected WidgetToggle filter4Enable = new WidgetToggle(getLenX() / 2 - 14, 5, 14, "Enable");
    protected WidgetPageSelection widgetPageSelection;
    protected int filter4Index = 0;
    protected ArrayList<Camp> displayCamps;
    protected WidgetButton backButton = new WidgetButton(1, getY() - 2, 10, "Back");
    private OverlayTextInputAction overlayTextInputAction;
    protected CampList campListForExport;


    /**
     * Constructs a CampListView with default dimensions and initializes various UI elements.
     */
    public CampListView() {
        super(55, 190, "Camp View");
        campListForExport = new CampList();
        displayCamps = new ArrayList<>();
        WidgetLabel widgetLabel = new WidgetLabel(1, 1, 40, "Filters:", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(widgetLabel);
        for (int i = 1; i < getY(); i++) {
            WidgetLabel widgetTmp = new WidgetLabel(getX() / 2, i, 2, "┃");
            addWidget(widgetTmp);
        }

        addWidget(filterLabel1);
        addWidget(filter1Start);
        addWidget(filter1Comment);
        addWidget(filter1End);
        addWidget(filter1Enable);
        addWidget(filterLabel2);
        addWidget(filter2);
        addWidget(filter2Enable);
        addWidget(filterLabel3);
        addWidget(filter3);
        addWidget(filter3Enable);
        addWidget(filterLabel4);
        addWidget(filter4);
        addWidget(filter4Enable);

        backButton.setSkipSelection(false);
        filter4Index = addWidget(filter4Enable);
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        CampList camps = RepositoryCollection.getCampRepository();
        for (int i = 0; i < camps.size(); i++) {
            Camp camp = camps.get(i);
            displayCamps.add(camp);
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(camp.getName() + " Camp");
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            tmp.add("    " + formLine("Creator: " + camp.getStaffInCharge().getName(), "School: " + camp.getSchool(),
                    getX() / 2 - 14));
            tmp.add("    " + formLine(ft.format(camp.getStartDate()) + " ─ " + ft.format(camp.getEndDate()),
                    "Registration Close at: " + ft.format(camp.getCloseRegistrationDate()), getX() / 2 - 14));
            tmp.add("    " + formLine(
                    "Participants: " + (camp.getAttendeesAndCommittees().size()) + " / "
                            + camp.getTotalSlots(),
                    "Committee: " + camp.getCommittees().size() + " / " + camp.getTotalCommitteeSlots(), getX() / 2 - 14));
            String line = "";
            for (int j = 0; j < getX() / 2 - 3; j++) {
                line += "─";
            }
            tmp.add(line);
            options.add(tmp);
        }
        widgetPageSelection = new WidgetPageSelection(1, 10, getX() / 2 - 2, getY() - 14, "PartyList", options,
                CampListView.this);
        addWidget(widgetPageSelection);
        addWidget(backButton);
    }

    protected String lastFilter = "";
    protected int lastSize = -1;

    /**
     * Customizes the filter for camps based on certain criteria.
     * @param list The original list of camps.
     * @return The filtered list of camps.
     */
    protected CampList CustomFilter(CampList list) {
        return list;
    }


    /**
     * Refreshes the list of camps based on applied filters.
     * @param forceRefresh Boolean indicating whether to force a refresh.
     */
    public void refreshList(boolean forceRefresh) {
        String newFilter = "";
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        CampList camps = RepositoryCollection.getCampRepository();
        if (filter1Enable.getPressed() && !filter1Start.getText().equals("") && !filter1End.getText().equals("")) {
            newFilter += filter1Start.getText() + filter1End.getText();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                Date dateStart = ft.parse(filter1Start.getText());
                Date dateEnd = ft.parse(filter1End.getText());
                camps = camps.filterByDateRange(dateStart, dateEnd);
            } catch (ParseException e) {
            }
        }
        if (filter2Enable.getPressed() && !filter2.getText().equals("")) {
            newFilter += filter2.getText();
            CampList res = new CampList();
            for (Camp camp : camps) {
                if (camp.getName().toLowerCase().contains(filter2.getText().toLowerCase())) {
                    res.add(camp);
                }
            }
            camps = res;
        }
        if (filter3Enable.getPressed() && !filter3.getText().equals("")) {
            newFilter += filter3.getText();
            CampList res = new CampList();
            for (Camp camp : camps) {
                if (camp.getSchool().toLowerCase().contains(filter3.getText().toLowerCase())) {
                    res.add(camp);
                }
            }
            camps = res;
        }
        if (filter4Enable.getPressed() && !filter4.getText().equals("")) {
            newFilter += filter4.getText();
            CampList res = new CampList();
            for (Camp camp : camps) {
                if (camp.getStaffInCharge().getName().toLowerCase().contains(filter4.getText().toLowerCase())) {
                    res.add(camp);
                }
            }
            camps = res;
        }
        camps = camps.sortByName();
        camps = CustomFilter(camps);
        for (Camp camp : camps) {
            String campTitle = "";
            if (UserController.getCurrentUser() != null) {
                if (UserController.getCurrentUser() instanceof Student) {
                    Student student = (Student) UserController.getCurrentUser();
                    if (camp.getCommittees().contains(student)) {
                        campTitle += " [Committee]";
                    } else if (camp.getAttendees().contains(student)) {
                        campTitle += " [Joined]";
                    }
                    if (camp.getCommittees().contains(student)) {
                        boolean newEnquiries = false;
                        for (Enquiry s : camp.getEnquiryList()) {
                            if (s.getAnswer() == null || s.getAnswer().equals("")) {
                                newEnquiries = true;
                                break;
                            }
                        }
                        if (newEnquiries) {
                            campTitle += " [Enquiries]";
                        }
                    }
                }
                if (UserController.getCurrentUser() instanceof Staff) {
                    Staff staff = (Staff) UserController.getCurrentUser();
                    if (camp.getStaffInCharge().equals(staff)) {
                        campTitle += " [In Charge]";
                    }
                    if (!camp.isVisible()) {
                        campTitle += " [Hidden]";
                    }
                    boolean newSuggestion = false;
                    for (Suggestion s : camp.getSuggestionList()) {
                        if (s.getStatus() == SuggestionStatus.PENDING) {
                            newSuggestion = true;
                            break;
                        }
                    }
                    if (newSuggestion) {
                        campTitle += " [Suggestions]";
                    }
                    boolean newEnquiries = false;
                    for (Enquiry s : camp.getEnquiryList()) {
                        if (s.getAnswer() == null || s.getAnswer().equals("")) {
                            newEnquiries = true;
                            break;
                        }
                    }
                    if (newEnquiries) {
                        campTitle += " [Enquiries]";
                    }
                }
            }
            newFilter += camp.getDescription() + camp.getName() + camp.getAttendees().toString() + camp.getStartDate().toString() + campTitle;
        }
        displayCamps.clear();
        campListForExport.clear();
        for(int i = 0 ; i < camps.size() ; i ++){
            Camp camp = camps.get(i);
            campListForExport.add(camp);
            displayCamps.add(camp);
            ArrayList<String> tmp = new ArrayList<String>();
            String campTitle = camp.getName() + " Camp";
            if (UserController.getCurrentUser() != null) {
                if (UserController.getCurrentUser() instanceof Student) {
                    Student student = (Student) UserController.getCurrentUser();
                    if (camp.getCommittees().contains(student)) {
                        campTitle += " [Committee]";
                    } else if (camp.getAttendees().contains(student)) {
                        campTitle += " [Joined]";
                    }
                    if(camp.getCommittees().contains(student)){
                        boolean newEnquiries = false;
                        for(Enquiry s : camp.getEnquiryList()){
                            if(s.getAnswer() == null || s.getAnswer().equals("")){
                                newEnquiries = true;
                                break;
                            }
                        }
                        if(newEnquiries){
                            campTitle += " [Enquiries]";
                        }
                    }
                }
                if (UserController.getCurrentUser() instanceof Staff) {
                    Staff staff = (Staff) UserController.getCurrentUser();
                    if (camp.getStaffInCharge().equals(staff)) {
                        campTitle += " [In Charge]";
                    }
                    if (!camp.isVisible()) {
                        campTitle += " [Hidden]";
                    }
                    boolean newSuggestion = false;
                    for(Suggestion s : camp.getSuggestionList()){
                        if(s.getStatus() == SuggestionStatus.PENDING){
                            newSuggestion = true;
                            break;
                        }
                    }
                    if(newSuggestion){
                        campTitle += " [Suggestions]";
                    }
                    boolean newEnquiries = false;
                    for(Enquiry s : camp.getEnquiryList()){
                        if(s.getAnswer() == null || s.getAnswer().equals("")){
                            newEnquiries = true;
                            break;
                        }
                    }
                    if(newEnquiries){
                        campTitle += " [Enquiries]";
                    }
                }
            }
            tmp.add(campTitle);
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            tmp.add("    " + formLine("Creator: " + camp.getStaffInCharge().getName(), "School: " + camp.getSchool(),
                    getX() / 2 - 14));
            tmp.add("    " + formLine(ft.format(camp.getStartDate()) + " ─ " + ft.format(camp.getEndDate()),
                    "Registration Close at: " + ft.format(camp.getCloseRegistrationDate()), getX() / 2 - 14));
            tmp.add("    " + formLine(
                    "Participants: " + (camp.getAttendeesAndCommittees().size()) + " / "
                            + camp.getTotalSlots(),
                    "Committee: " + camp.getCommittees().size() + " / " + camp.getTotalCommitteeSlots(), getX() / 2 - 14));
            String line = "";
            for (int j = 0; j < getX() / 2 - 3; j++) {
                line += "─";
            }
            tmp.add(line);
            options.add(tmp);
        }
        if (!newFilter.equals(lastFilter) || forceRefresh) {
            widgetPageSelection.updateList(options);
            lastFilter = newFilter;
            lastSize = camps.size();
        }
    }


    /**
     * Message loop to update and manage the UI elements and interactions.
     */
    @Override
    public void messageLoop() {
        super.messageLoop();
        refreshList(false);
        if(overlayTextInputAction != null){
            addOverlay(overlayTextInputAction);
            overlayTextInputAction = null;
        }
        // System.out.println("Message loop");

    }

    /**
     * Performs actions when the window is closed or exited.
     */
    public void onExit() {
    }

    /**
     * Handles window-specific actions upon certain selections.
     * @param chose The chosen action identifier.
     * @param choseString The string representation of the chosen action.
     */
    @Override
    public void onWindowFinished(int chose, String choseString) {
        if (choseString.equals("Camp Report")) {
            overlayTextInputAction = new OverlayTextInputActionToggles(60, getY() / 2 - 8, getX() / 2 - 30, "File Name Prompt",
                    "Save [Camps Participant Report] to File Name:", CampListView.this, 220);
        } else if (choseString.equals("Performance Report")) {
            overlayTextInputAction = new OverlayTextInputAction(60, getY() / 2 - 8, getX() / 2 - 30, "File Name Prompt",
                    "Save [Performance Report] to File Name:", CampListView.this, 231);
        } else if (choseString.equals("Enquiries Report")) {
            overlayTextInputAction = new OverlayTextInputAction(60, getY() / 2 - 8, getX() / 2 - 30, "File Name Prompt",
                    "Save [Enquiries Report] to File Name:", CampListView.this, 232);
        }
        if(campListForExport != null) {
            if (chose == 220) {
                // Camp Report
                if (UserController.getCurrentUser() != null) {
                    if (UserController.getCurrentUser() instanceof Student) {
                        Student student = (Student) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByCampCommittee(student);
                        Report report = new CampReport(forExport, false, false);
                        CSV.exportToCSV(choseString, report);
                    }
                    if (UserController.getCurrentUser() instanceof Staff) {
                        Staff staff = (Staff) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByStaff(staff);
                        Report report = new CampReport(forExport, false, false);
                        CSV.exportToCSV(choseString, report);
                    }
                }
            }
            else if (chose == 221) {
                // Camp Report
                if (UserController.getCurrentUser() != null) {
                    if (UserController.getCurrentUser() instanceof Student) {
                        Student student = (Student) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByCampCommittee(student);
                        Report report = new CampReport(forExport, false, true);
                        CSV.exportToCSV(choseString, report);
                    }
                    if (UserController.getCurrentUser() instanceof Staff) {
                        Staff staff = (Staff) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByStaff(staff);
                        Report report = new CampReport(forExport, false, true);
                        CSV.exportToCSV(choseString, report);
                    }
                }
            }
            else if (chose == 222) {
                // Camp Report
                if (UserController.getCurrentUser() != null) {
                    if (UserController.getCurrentUser() instanceof Student) {
                        Student student = (Student) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByCampCommittee(student);
                        Report report = new CampReport(forExport, true, false);
                        CSV.exportToCSV(choseString, report);
                    }
                    if (UserController.getCurrentUser() instanceof Staff) {
                        Staff staff = (Staff) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByStaff(staff);
                        Report report = new CampReport(forExport, true, false);
                        CSV.exportToCSV(choseString, report);
                    }
                }
            }
            else if (chose == 223) {
                // Camp Report
                if (UserController.getCurrentUser() != null) {
                    if (UserController.getCurrentUser() instanceof Student) {
                        Student student = (Student) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByCampCommittee(student);
                        Report report = new CampReport(forExport, true, true);
                        CSV.exportToCSV(choseString, report);
                    }
                    if (UserController.getCurrentUser() instanceof Staff) {
                        Staff staff = (Staff) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByStaff(staff);
                        Report report = new CampReport(forExport, true, true);
                        CSV.exportToCSV(choseString, report);
                    }
                }
            }else if (chose == 231) {
                if (UserController.getCurrentUser() instanceof Staff) {
                    Staff staff = (Staff) UserController.getCurrentUser();
                    CampList forExport = campListForExport.filterByStaff(staff);
                    Report report = new PerformanceReport(forExport);
                    CSV.exportToCSV(choseString, report);
                }
                // Performance Report
            } else if (chose == 232) {
                if (UserController.getCurrentUser() != null) {
                    if (UserController.getCurrentUser() instanceof Student) {
                        Student student = (Student) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByCampCommittee(student);
                        Report report = new EnquiryReport(forExport);
                        CSV.exportToCSV(choseString, report);
                    }
                    if (UserController.getCurrentUser() instanceof Staff) {
                        Staff staff = (Staff) UserController.getCurrentUser();
                        CampList forExport = campListForExport.filterByStaff(staff);
                        Report report = new EnquiryReport(forExport);
                        CSV.exportToCSV(choseString, report);
                    }
                }
                // Enquiries Report
            }
        }
    }
}
