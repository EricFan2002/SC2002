package ui;

import ui.widgets.TEXT_ALIGNMENT;
import ui.widgets.WidgetButton;
import ui.widgets.WidgetLabel;
import ui.windows.WindowOverlayClass;

public class OverlayTestClass extends WindowOverlayClass {
    WidgetButton cancelButton;

    public OverlayTestClass(int y, int x, int offsetY, int offsetX, String windowName) {
        super(y, x, offsetY, offsetX, windowName);
        WidgetLabel widgetLabel = new WidgetLabel(3, 3,20, "Overlay OK", TEXT_ALIGNMENT.ALIGN_MID);
        addWidget(widgetLabel);
        cancelButton = new WidgetButton(2, 14, 20, "Back");
        addWidget(cancelButton);
    }
    public void messageLoop() {
        super.messageLoop();
        if(cancelButton.getPressed()){
            setDestroy();
        }
    }
    public void onExit(){
        cancelButton.clearPressed();
    }
}
