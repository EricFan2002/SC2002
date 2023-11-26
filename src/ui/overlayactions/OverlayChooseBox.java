package ui.overlayactions;

import ui.windows.ICallBack;
import ui.widgets.WidgetButton;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code OverlayChooseBox} class represents an overlay window for presenting a list of choices to the user.
 * It extends the {@code WindowOverlayClass} and implements the {@code ICallBack} interface.
 */
public class OverlayChooseBox extends WindowOverlayClass {
    /**
     * The button to cancel the choice.
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
     * The list of choice buttons presented to the user.
     */
    List<WidgetButton> choices;
    /**
     * The callback window to notify upon completion.
     */
    Window callbackWindow;
    /**
     * Constructs an instance of {@code OverlayChooseBox}.
     *
     * @param x              The x-coordinate of the window.
     * @param offsetY        The y-coordinate offset of the window.
     * @param offsetX        The x-coordinate offset of the window.
     * @param windowName     The name of the window.
     * @param options        The list of choices to be presented.
     * @param callbackWindow The callback window to notify upon completion.
     */

    public OverlayChooseBox(int x, int offsetY, int offsetX, String windowName, List<String> options, Window callbackWindow) {
        super(3 + options.size(), x, offsetY, offsetX, windowName);
        choices = new ArrayList<>();
        this.callbackWindow = callbackWindow;
        for(int i = 0 ; i < options.size() ; i++){
            WidgetButton choice = new WidgetButton(1, 2 + i, x - 2, options.get(i));
            addWidget(choice);
            choices.add(choice);
        }
    }
    /**
     * Handles the message loop for the overlay window.
     */
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
    /**
     * Performs actions upon exiting the overlay window.
     */
    public void onExit(){
        super.onExit();
        if(callbackWindow instanceof ICallBack) {
            ((ICallBack)callbackWindow).onWindowFinished(chose, choseString);
        }
    }
}
