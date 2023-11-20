package ui.windows;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for managing multiple windows.
 * This class provides a framework for managing a collection of windows in a user interface. It allows for
 * adding and switching between windows, handling user input, and rendering the currently active window.
 */
public class WindowsManager {
    private List<Window> windows = new ArrayList<>();
    Screen screen;
    int currentWindow = 0;
    int x;
    int y;

    /**
     * Constructor for WindowsManager.
     *
     * @param screen The screen where windows will be managed.
     * @param x The X position for window management.
     * @param y The Y position for window management.
     */
    public WindowsManager(Screen screen, int x, int y){
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    /**
     * Adds a window to the window manager.
     *
     * @param window The window to add.
     */
    public void addWindow(Window window){
        windows.add(window);
    }

    /**
     * Switches from one window to another with a transition effect.
     *
     * @param fromIndex The index of the window to switch from.
     * @param toIndex The index of the window to switch to.
     * @param ms The duration of the transition in milliseconds.
     * @param directionX The X direction of the transition.
     * @param directionY The Y direction of the transition.
     */
    public void switchFrom(int fromIndex, int toIndex, int ms, int directionX, int directionY){
        if(fromIndex >= windows.size() || toIndex >= windows.size()){
            System.out.println("Error! windows index out of bound!");
            return;
        }
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < ms) {
            double percentage = (double)(System.currentTimeMillis() - startTime) / ms;
            windows.get(toIndex).switchFrom(windows.get(fromIndex), screen, x, y, percentage, directionX, directionY);
            try {
                screen.refresh();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Thread.yield();
        }
        windows.get(fromIndex).switchToWindow = -1;
        windows.get(fromIndex).onExit();
        currentWindow = toIndex;
        screen.clear();
    }

    /**
     * Draws the currently active window on the screen.
     *
     * @param x The X position to draw the window at.
     * @param y The Y position to draw the window at.
     */
    public void draw(int x, int y){
        windows.get(currentWindow).draw(screen, x, y, 1);

        windows.get(currentWindow).messageLoopHelper();
        if(windows.get(currentWindow).getSwitchToWindow() != -1){
            switchFrom(currentWindow, windows.get(currentWindow).getSwitchToWindow(), 1000, 1, 0);
        }
    }

    /**
     * Handles a key stroke event for the currently active window.
     *
     * @param keyStroke The key stroke event to handle.
     */
    public void keyStroke(KeyStroke keyStroke){
        windows.get(currentWindow).keyStrokeHelper(keyStroke);
    }
}
