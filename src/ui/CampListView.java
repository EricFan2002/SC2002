package ui;

import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.camp.CampList;
import entity.camp.CampRepository;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    protected WidgetLabel filterLabel1 = new WidgetLabel(1, 2, 19, "Filter 1:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter1 = new WidgetTextBox(20, 2, getLenX() / 2 - 20, "");
    protected WidgetLabel filterLabel2 = new WidgetLabel(1, 3, 19, "Filter 2:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter2 = new WidgetTextBox(20, 3, getLenX() / 2 - 20, "");
    protected WidgetLabel filterLabel3 = new WidgetLabel(1, 4, 19, "Filter 3:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter3 = new WidgetTextBox(20, 4, getLenX() / 2 - 20, "");
    protected WidgetLabel filterLabel4 = new WidgetLabel(1, 5, 19, "Filter 4:", TEXT_ALIGNMENT.ALIGN_RIGHT);
    protected WidgetTextBox filter4 = new WidgetTextBox(20, 5, getLenX() / 2 - 20, "");
    protected WidgetPageSelection widgetPageSelection;
    protected int filter4Index = 0;

    public CampListView(int loginSwitchToWindowIndex, int changePasswordWindowIndex, int forgotPasswordWindowIndex){
        super(55, 190, "Party View");
        WidgetLabel widgetLabel = new WidgetLabel(1, 1,40, "Filters:", TEXT_ALIGNMENT.ALIGN_LEFT);
        addWidget(widgetLabel);
        for(int i = 1 ; i < getY() ; i++){
            WidgetLabel widgetTmp = new WidgetLabel(getX()/2, i, 2, "┃");
            addWidget(widgetTmp);
        }
        addWidget(filterLabel1);
        addWidget(filter1);
        addWidget(filterLabel2);
        addWidget(filter2);
        addWidget(filterLabel3);
        addWidget(filter3);
        addWidget(filterLabel4);
        filter4Index = addWidget(filter4);
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        CampList camps = RepositoryCollection.campRepository.getAll();
        for(int i = 0 ; i < camps.size() ; i ++){
            Camp camp = camps.get(i);
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

    @Override
    public void messageLoop() {
    }
    public void onExit(){
    }

    @Override
    public void onWindowFinished(int chose, String choseString) {
    }
}
