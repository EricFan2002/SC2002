package ui.OverlayActions;

import ui.widgets.WidgetButton;
import ui.widgets.WidgetLabel;
import ui.windows.ICallBack;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.util.ArrayList;
import java.util.List;

public class OverlayNotification extends WindowOverlayClass {
    WidgetButton cancelButton;
    private int chose;
    private String choseString;
    List<WidgetButton> choices;
    Window callbackWindow;
    protected WidgetButton exitButton;

    public OverlayNotification(int x, int offsetY, int offsetX, String windowName, String notification, Window callbackWindow) {
        super(12, x, offsetY, offsetX, windowName);
        WidgetLabel widgetLabel = new WidgetLabel(3, 3, getX() - 10, notification);
        addWidget(widgetLabel);
        exitButton = new WidgetButton(3, 8, getLenX() - 4, "OK");
        addWidget(exitButton);
        this.callbackWindow = callbackWindow;
    }

    public void messageLoop() {
        if(exitButton.getPressed()){
            exitButton.clearPressed();
            setDestroy();
        }
    }
    public void onExit(){
        super.onExit();
        if(callbackWindow instanceof ICallBack) {
            ((ICallBack)callbackWindow).onWindowFinished(0, "CFM");
        }
    }
}
