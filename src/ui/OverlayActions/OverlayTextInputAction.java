package ui.OverlayActions;

import ui.widgets.WidgetButton;
import ui.widgets.WidgetLabel;
import ui.widgets.WidgetTextBox;
import ui.windows.ICallBack;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.util.List;

/**
 * The {@code OverlayTextInputAction} class represents an overlay window for receiving text input with options to enter or cancel.
 * It extends the {@code WindowOverlayClass} and implements the {@code ICallBack} interface.
 */
public class OverlayTextInputAction extends WindowOverlayClass {
    /**
     * The button to cancel the text input.
     */
    WidgetButton cancelButton;

    /**
     * The selected choice index.
     */
    private int chose;

    /**
     * The selected choice string.
     */
    private String choseString;

    /**
     * The list of choices presented to the user.
     */
    List<WidgetButton> choices;

    /**
     * The callback window to notify upon completion.
     */
    Window callbackWindow;

    /**
     * The button to exit the text input.
     */
    protected WidgetButton exitButton;

    /**
     * The button to abort the text input.
     */
    protected WidgetButton abordButton;

    /**
     * The text input box.
     */
    protected WidgetTextBox textInput;

    /**
     * The return code to be passed to the callback window.
     */
    private int returnCode = 255;

    /**
     * Indicates whether the text input is aborted.
     */
    private boolean abord = false;

    /**
     * Constructs an instance of {@code OverlayTextInputAction}.
     *
     * @param x               The x-coordinate of the window.
     * @param offsetY         The y-coordinate offset of the window.
     * @param offsetX         The x-coordinate offset of the window.
     * @param windowName      The name of the window.
     * @param prompt          The prompt message for text input.
     * @param callbackWindow  The callback window to notify upon completion.
     */
    public OverlayTextInputAction(int x, int offsetY, int offsetX, String windowName, String prompt, Window callbackWindow) {
        super(12, x, offsetY, offsetX, windowName);
        WidgetLabel widgetLabel = new WidgetLabel(3, 3, x - 10, prompt);
        addWidget(widgetLabel);
        exitButton = new WidgetButton(3, 8, getLenX() - 4, "Enter");
        addWidget(exitButton);
        abordButton = new WidgetButton(3, 10, getLenX() - 4, "Cancel");
        addWidget(abordButton);
        textInput = new WidgetTextBox(3, 5, getLenX() - 4, "");
        addWidget(textInput);
        this.callbackWindow = callbackWindow;
    }

    /**
     * Constructs an instance of {@code OverlayTextInputAction} with a specified return code.
     *
     * @param x              The x-coordinate of the window.
     * @param offsetY        The y-coordinate offset of the window.
     * @param offsetX        The x-coordinate offset of the window.
     * @param windowName     The name of the window.
     * @param prompt         The prompt message for text input.
     * @param callbackWindow The callback window to notify upon completion.
     * @param returnCode     The return code to be passed to the callback window.
     */
    public OverlayTextInputAction(int x, int offsetY, int offsetX, String windowName, String prompt, Window callbackWindow, int returnCode) {
        this(x, offsetY, offsetX, windowName, prompt, callbackWindow);
        this.returnCode = returnCode;
    }

    /**
     * Constructs an instance of {@code OverlayTextInputAction} with a specified pre-loaded text.
     *
     * @param x              The x-coordinate of the window.
     * @param offsetY        The y-coordinate offset of the window.
     * @param offsetX        The x-coordinate offset of the window.
     * @param windowName     The name of the window.
     * @param prompt         The prompt message for text input.
     * @param callbackWindow The callback window to notify upon completion.
     * @param loadText       The pre-loaded text in the text input box.
     */
    public OverlayTextInputAction(int x, int offsetY, int offsetX, String windowName, String prompt, Window callbackWindow, String loadText) {
        this(x, offsetY, offsetX, windowName, prompt, callbackWindow);
        textInput.setText(loadText);
    }

    /**
     * Handles the message loop for the overlay window.
     */
    public void messageLoop() {
        if (exitButton.getPressed()) {
            exitButton.clearPressed();
            setDestroy();
        }
        if (abordButton.getPressed()) {
            abordButton.clearPressed();
            setDestroy();
            abord = true;
        }
    }

    /**
     * Performs actions upon exiting the overlay window.
     */
    public void onExit() {
        super.onExit();
        if (abord) {
            ((ICallBack) callbackWindow).onWindowFinished(-1, textInput.getText());
        }
        if (callbackWindow instanceof ICallBack) {
            ((ICallBack) callbackWindow).onWindowFinished(returnCode, textInput.getText());
        }
    }
}

