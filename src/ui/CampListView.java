package ui;

import entity.RepositoryCollection;
import entity.camp.Camp;
import entity.camp.CampList;
import entity.camp.CampRepository;
import ui.widgets.*;
import ui.windows.ICallBack;
import ui.windows.Window;

import java.util.ArrayList;
import java.util.List;

public class CampListView extends Window implements ICallBack {
    public CampListView(int loginSwitchToWindowIndex, int changePasswordWindowIndex, int forgotPasswordWindowIndex){
        super(55, 190, "Party View");
        WidgetLabel widgetLabel = new WidgetLabel(1, 1,40, "Filters:", TEXT_ALIGNMENT.ALIGN_LEFT);
        for(int i = 1 ; i < getY() ; i++){
            WidgetLabel widgetTmp = new WidgetLabel(getX()/2, i, 2, "┃");
            addWidget(widgetTmp);
        }
        addWidget(widgetLabel);
        WidgetLabel filterLabel1 = new WidgetLabel(1, 2, 19, "Filter 1:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(filterLabel1);
        WidgetTextBox filter1 = new WidgetTextBox(20, 2, getLenX() / 2 - 20, "");
        addWidget(filter1);
        WidgetLabel filterLabel2 = new WidgetLabel(1, 3, 19, "Filter 2:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(filterLabel2);
        WidgetTextBox filter2 = new WidgetTextBox(20, 3, getLenX() / 2 - 20, "");
        addWidget(filter2);
        WidgetLabel filterLabel3 = new WidgetLabel(1, 4, 19, "Filter 3:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(filterLabel3);
        WidgetTextBox filter3 = new WidgetTextBox(20, 4, getLenX() / 2 - 20, "");
        addWidget(filter3);
        WidgetLabel filterLabel4 = new WidgetLabel(1, 5, 19, "Filter 4:", TEXT_ALIGNMENT.ALIGN_RIGHT);
        addWidget(filterLabel4);
        WidgetTextBox filter4 = new WidgetTextBox(20, 5, getLenX() / 2 - 20, "");
        addWidget(filter4);
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        CampList camps = RepositoryCollection.campRepository.getAll();
        for(int i = 0 ; i < camps.size() ; i ++){
            Camp camp = camps.get(i);
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add("Camp: " + camp.getName());
            tmp.add("    " + camp.getStartDate().toString() + " To " + camp.getEndDate().toString());
            tmp.add("    Participants: " + camp.getAttendees().size() + ", Committee: " + camp.getCommittees().size());
            tmp.add("    Creator: " + camp.getStaffInCharge().getName() + ", Close at: " + camp.getCloseRegistrationDate().toString());
            options.add(tmp);
        }
//        ArrayList<ArrayList<String>> options = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i ++){
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add("Camp: " + i);
            for(int j = 0 ; j < 3 ; j++){
                tmp.add("    Details: " + j);
            }
            options.add(tmp);
        }
        WidgetPageSelection widgetPageSelection = new WidgetPageSelection(1, 7, getX() / 2 -2, getY() - 10, "PartyList", options, CampListView.this);
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
