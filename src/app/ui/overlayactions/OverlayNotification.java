package app.ui.overlayactions;

import app.ui.widgets.WidgetButton;
import app.ui.widgets.WidgetLabel;
import app.ui.windows.ICallBack;
import app.ui.windows.Window;
import app.ui.windows.WindowOverlayClass;

import java.util.List;

/**
 * The {@code OverlayNotification} class represents an overlay window for displaying notifications with an OK button.
 * It extends the {@code WindowOverlayClass} and implements the {@code ICallBack} interface.
 */
public class OverlayNotification extends WindowOverlayClass {
    /**
     * The button to cancel the notification.
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
     * The button to exit the notification.
     */
    protected WidgetButton exitButton;

    /**
     * Constructs an instance of {@code OverlayNotification}.
     *
     * @param x               The x-coordinate of the window.
     * @param offsetY         The y-coordinate offset of the window.
     * @param offsetX         The x-coordinate offset of the window.
     * @param windowName      The name of the window.
     * @param notification    The notification message to be displayed.
     * @param callbackWindow  The callback window to notify upon completion.
     */
    public OverlayNotification(int x, int offsetY, int offsetX, String windowName, String notification, Window callbackWindow) {
        super(12, x, offsetY, offsetX, windowName);
        WidgetLabel widgetLabel = new WidgetLabel(3, 3, getX() - 10, notification);
        addWidget(widgetLabel);
        exitButton = new WidgetButton(3, 8, getLenX() - 4, "OK");
        addWidget(exitButton);
        this.callbackWindow = callbackWindow;
    }

    /**
     * Handles the message loop for the overlay window.
     */
    public void messageLoop() {
        if (exitButton.getPressed()) {
            exitButton.clearPressed();
            setDestroy();
        }
    }

    /**
     * Performs actions upon exiting the overlay window.
     */
    public void onExit() {
        super.onExit();
        if (callbackWindow instanceof ICallBack) {
            ((ICallBack) callbackWindow).onWindowFinished(0, "CFM");
        }
    }
}

