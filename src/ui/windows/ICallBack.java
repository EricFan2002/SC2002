package ui.windows;

/**
 * Interface defining a callback mechanism for window actions.
 */
public interface ICallBack {
    /**
     * Called when a window action is completed.
     *
     * @param chose The choice made in the window.
     * @param choseString The string representation of the choice.
     */
    void onWindowFinished(int chose, String choseString);
}
