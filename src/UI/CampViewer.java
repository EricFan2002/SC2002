package UI;

import java.util.ArrayList;

public class CampViewer extends Window {
    WidgetLabel widgetLabel;
    WidgetLabel widgetLabel1;
    ArrayList<WidgetTextBox> widgetTextBoxes;
    WidgetButton button ;
    private int loginSwitchToWindowIndex;
    public CampViewer(int y, int x, int loginSwitchToWindowIndex){
        super(y, x, "Camp Viewer");
        WidgetLabel widgetLabel = new WidgetLabel(3, 3,30, "Current Parties Includes:", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        for(int i = 0 ; i < 10 ; i++) {
            widgetLabel1 = new WidgetLabel(2, 5 + i, 9, "Part:", TEXT_ALIGNMENT.ALIGN_RIGHT);
            addWidget(widgetLabel1);
            WidgetTextBox tmpWidgetTextBox = new WidgetTextBox(14, 5 + i, 20, "Party " + i);
            addWidget(tmpWidgetTextBox);
        }
        button = new WidgetButton(5, 20, 30, "Click Me");
        addWidget(button);
        setPointer(button);
        this.loginSwitchToWindowIndex = loginSwitchToWindowIndex;
    }

    public void messageLoop() {
        super.messageLoop();
        if(button.getPressed()){
            switchToWindow = loginSwitchToWindowIndex;
        }
    }
    public void onExit(){
        button.clearPressed();
    }
}
