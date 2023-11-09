package ui;

import ui.widgets.ICallBack;
import ui.widgets.TEXT_ALIGNMENT;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetLabel;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.util.ArrayList;
import java.util.List;

public class OverlayChooseBox extends WindowOverlayClass {
    WidgetButton cancelButton;
    private int chose;
    private String choseString;
    List<WidgetButton> choices;
    Window callbackWindow;

    public OverlayChooseBox(int x, int offsetY, int offsetX, String windowName, List<String> options, Window callbackWindow) {
        super(3 + options.size(), x, offsetY, offsetX, windowName);
        choices = new ArrayList<>();
        this.callbackWindow = callbackWindow;
        for(int i = 0 ; i < options.size() ; i++){
            WidgetButton choice = new WidgetButton(1, 2 + i, x - 2, options.get(i));
            addWidget(choice);
        }
    }

    public void messageLoop() {
        super.messageLoop();
        for(int i = 0 ; i < choices.size() * 2 ; i++){
            if(choices.get(i).getPressed()){
                System.out.println(i);
                chose = i;
                choseString = widgets.get(i).getText();
                setDestroy();
            }
        }
    }
    public void onExit(){
        cancelButton.clearPressed();
        if(callbackWindow instanceof ICallBack){
            ((ICallBack)callbackWindow).onWindowFinished(chose, choseString);
        }
    }
}
