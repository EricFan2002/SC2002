package ui;

import ui.widgets.WidgetButton;
import ui.windows.ICallBack;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.util.ArrayList;
import java.util.List;

public class OverlayCampListViewStudentCampActions extends WindowOverlayClass {
    WidgetButton cancelButton;
    private int chose;
    private String choseString;
    List<WidgetButton> choices;
    Window callbackWindow;

    public final static int optionCount = 6;

    public OverlayCampListViewStudentCampActions(int x, int offsetY, int offsetX, String windowName, Window callbackWindow) {
        super(3 + optionCount, x, offsetY, offsetX, windowName);
        List<String> options = new ArrayList<>();

        options.add("View Details");
        options.add("Join Camp");
        options.add("Quit Camp");
        options.add("Enquiry");
        options.add("Reply Enquiry");
        options.add("Suggestions");
        options.add("Cancel");

        choices = new ArrayList<>();
        this.callbackWindow = callbackWindow;
        for(int i = 0 ; i < options.size() ; i++){
            WidgetButton choice = new WidgetButton(1, 2 + i, x - 2, options.get(i));
            addWidget(choice);
            choices.add(choice);
        }
    }

    public void messageLoop() {
        for(int i = 0 ; i < choices.size(); i++){
            if(choices.get(i).getPressed()){
                System.out.println(i);
                chose = i;
                choseString = widgets.get(i).getText();
                setDestroy();
            }
        }
    }
    public void onExit(){
        super.onExit();
        if(callbackWindow instanceof ICallBack) {
            ((ICallBack)callbackWindow).onWindowFinished(chose, choseString);
        }
    }
}
