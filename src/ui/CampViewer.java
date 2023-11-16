package ui;

import ui.widgets.*;
import ui.windows.Window;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CampViewer extends Window {
    WidgetLabel widgetLabel;
    WidgetLabel widgetLabel1;
    ArrayList<WidgetTextBox> widgetTextBoxes;
    private int loginSwitchToWindowIndex;
    public CampViewer(int y, int x, int loginSwitchToWindowIndex){
        super(y, x, "Camp Viewer");
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i ++){
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add("Camp: " + i);
            for(int j = 0 ; j < 3 ; j++){
                tmp.add("    Details: " + j);
            }
            options.add(tmp);
        }
        WidgetPageSelection widgetPageSelection = new WidgetPageSelection(2, 2, 20, 10, "Selection", options, CampViewer.this);
        addWidget(widgetPageSelection);
        this.loginSwitchToWindowIndex = loginSwitchToWindowIndex;
    }

    public void messageLoop() {
        super.messageLoop();
    }
    public void onExit(){
    }
}
