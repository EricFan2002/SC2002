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
    protected WidgetButton abordButton;
    protected WidgetTextBox textInput;
    private int returnCode = 255;
    private boolean abord = false;

    public OverlayTextInput(int x, int offsetY, int offsetX, String windowName, String prompt, Window callbackWindow) {
        super(12, x, offsetY, offsetX, windowName);
        WidgetLabel widgetLabel = new WidgetLabel(3, 3, x-10, prompt);
        addWidget(widgetLabel);
        exitButton = new WidgetButton(3, 8, getLenX() - 4, "Enter");
        addWidget(exitButton);
        abordButton = new WidgetButton(3, 10, getLenX() - 4, "Cancel");
        addWidget(abordButton);
        textInput = new WidgetTextBox(3,5, getLenX() - 4, "");
        addWidget(textInput);
        this.callbackWindow = callbackWindow;
    }

    public OverlayTextInput(int x, int offsetY, int offsetX, String windowName, String prompt, Window callbackWindow, int returnCode) {
        this(x,offsetY, offsetX,windowName,prompt,callbackWindow);
        this.returnCode = returnCode;
    }

    public OverlayTextInput(int x, int offsetY, int offsetX, String windowName, String prompt, Window callbackWindow, String loadText) {
        this(x,offsetY, offsetX,windowName,prompt,callbackWindow);
        textInput.setText(loadText);
    }

    public void messageLoop() {
        if(exitButton.getPressed()){
            exitButton.clearPressed();
            setDestroy();
        }
        if(abordButton.getPressed()){
            abordButton.clearPressed();
            setDestroy();
            abord = true;
        }
    }
    public void onExit(){
        super.onExit();
        if(abord){
            ((ICallBack)callbackWindow).onWindowFinished(-1, textInput.getText());
        }
        if(callbackWindow instanceof ICallBack) {
            ((ICallBack)callbackWindow).onWindowFinished(returnCode, textInput.getText());
        }
    }
}
