package ui;

import ui.widgets.WidgetButton;
import ui.widgets.WidgetLabel;
import ui.widgets.WidgetTextBox;
import ui.windows.ICallBack;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.util.List;

public class OverlayTextInput extends WindowOverlayClass {
    WidgetButton cancelButton;
    private int chose;
    private String choseString;
    List<WidgetButton> choices;
    Window callbackWindow;
    protected WidgetButton exitButton;
    protected WidgetTextBox textInput;

    public OverlayTextInput(int x, int offsetY, int offsetX, String windowName, String prompt, Window callbackWindow) {
        super(12, x, offsetY, offsetX, windowName);
        WidgetLabel widgetLabel = new WidgetLabel(3, 3, x-10, prompt);
        addWidget(widgetLabel);
        exitButton = new WidgetButton(3, 10, getLenX() - 4, "Enter");
        addWidget(exitButton);
        textInput = new WidgetTextBox(3,5, getLenX() - 4, "");
        addWidget(textInput);
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
            ((ICallBack)callbackWindow).onWindowFinished(255, textInput.getText());
        }
    }
}
