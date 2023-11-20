package ui;

import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.camp.CampList;
import entity.camp.CampRepository;
import entity.user.Staff;
import entity.user.User;
import entity.user.UserList;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CampListView extends Window implements ICallBack {
    private String formLine(String part1, String part2, int length){
        int padding = length / 2 - part1.length();
        String res = part1;
        for(int i = 0 ; i < padding ; i ++){
            res += " ";
        }
        res += "│ ";
        res += part2;
        return res;
    }

    protected WidgetLabel filterLabel1 = new WidgetLabel(1, 2, 19, "Date Filter:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter1Start = new WidgetTextBox(20, 2, getLenX() / 4 - 18, "");
    protected WidgetLabel filter1Comment = new WidgetLabel(20 + getLenX() / 4 - 19, 2, 1 , "-");
    protected WidgetTextBox filter1End = new WidgetTextBox(20 + getLenX() / 4 - 17, 2, getLenX() / 4 - 17, "");
    protected WidgetToggle filter1Enable = new WidgetToggle(getLenX() / 2 - 14, 2, 14, "Enable");
    protected WidgetLabel filterLabel2 = new WidgetLabel(1, 3, 19, "Location Filter:", TEXT_ALIGNMENT.ALIGN_RIGHT);
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

    public CampListView(int loginSwitchToWindowIndex, int changePasswordWindowIndex, int forgotPasswordWindowIndex){
        super(55, 190, "Camp View");
        displayCamps = new ArrayList<>();
        WidgetLabel widgetLabel = new WidgetLabel(1, 1,40, "Filters:", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(widgetLabel);
        for(int i = 1 ; i < getY() ; i++){
            WidgetLabel widgetTmp = new WidgetLabel(getX()/2, i, 2, "┃");
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
        filter4Index = addWidget(filter4Enable);
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        CampList camps = RepositoryCollection.campRepository.getAll();
        for(int i = 0 ; i < camps.size() ; i ++){
            Camp camp = camps.get(i);
            displayCamps.add(camp);
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(camp.getName() + " Camp");
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            tmp.add("    " + formLine("Creator: " + camp.getStaffInCharge().getName(),  "School: " + camp.getSchool(), getX() / 2 - 14));
            tmp.add("    " + formLine(ft.format(camp.getStartDate()) + " ─ " + ft.format(camp.getEndDate()),  "Registration Close at: " + ft.format(camp.getCloseRegistrationDate()), getX() / 2 - 14));
            tmp.add("    " + formLine("Participants: " + camp.getAttendees().size(),  "Committee: " + camp.getCommittees().size(), getX() / 2 - 14));
            String line = "";
            for(int j = 0 ; j < getX() / 2 - 3 ; j ++){
                line += "─";
            }
            tmp.add(line);
            options.add(tmp);
        }
        widgetPageSelection = new WidgetPageSelection(1, 10, getX() / 2 -2, getY() - 14, "PartyList", options, CampListView.this);
        addWidget(widgetPageSelection);
    }

    String lastFilter = "";
    @Override
    public void messageLoop() {
        super.messageLoop();
//        System.out.println("Message loop");
        String newFilter = "";
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        CampList camps = RepositoryCollection.campRepository.getAll();
        if(filter1Enable.getPressed() && !filter1Start.getText().equals("") && !filter1End.getText().equals("")){
            newFilter += filter1Start.getText() + filter1End.getText();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                Date dateStart = ft.parse(filter1Start.getText());
                Date dateEnd = ft.parse(filter1End.getText());
                camps = camps.filterByDateRange(dateStart, dateEnd);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if(filter2Enable.getPressed() && !filter2.getText().equals("")){
            newFilter += filter2.getText();
            CampList res = new CampList();
            for(Camp camp : camps){
                if(camp.getLocation().toLowerCase().contains(filter3.getText().toLowerCase())){
                    res.add(camp);
                }
            }
            camps = res;
        }
        if(filter3Enable.getPressed() && !filter3.getText().equals("")){
            newFilter += filter3.getText();
            CampList res = new CampList();
            for(Camp camp : camps){
                if(camp.getSchool().toLowerCase().contains(filter3.getText().toLowerCase())){
                    res.add(camp);
                }
            }
            camps = res;
        }
        if(filter4Enable.getPressed() && !filter4.getText().equals("")){
            newFilter += filter4.getText();
            CampList res = new CampList();
            for(Camp camp : camps){
                if(camp.getStaffInCharge().getName().toLowerCase().contains(filter4.getText().toLowerCase())){
                    res.add(camp);
                }
            }
            camps = res;
        }
        displayCamps.clear();
        for(int i = 0 ; i < camps.size() ; i ++){
            Camp camp = camps.get(i);
            displayCamps.add(camp);
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(camp.getName() + " Camp");
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            tmp.add("    " + formLine("Creator: " + camp.getStaffInCharge().getName(),  "School: " + camp.getSchool(), getX() / 2 - 14));
            tmp.add("    " + formLine(ft.format(camp.getStartDate()) + " ─ " + ft.format(camp.getEndDate()),  "Registration Close at: " + ft.format(camp.getCloseRegistrationDate()), getX() / 2 - 14));
            tmp.add("    " + formLine("Participants: " + camp.getAttendees().size(),  "Committee: " + camp.getCommittees().size(), getX() / 2 - 14));
            String line = "";
            for(int j = 0 ; j < getX() / 2 - 3 ; j ++){
                line += "─";
            }
            tmp.add(line);
            options.add(tmp);
        }
        if(!newFilter.equals(lastFilter)) {
            widgetPageSelection.updateList(options);
            lastFilter = newFilter;
        }
    }
    public void onExit(){
    }

    @Override
    public void onWindowFinished(int chose, String choseString) {
    }
}
