package ui.windows;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowsManager {
    private List<Window> windows = new ArrayList<>();
    Screen screen;
    int currentWindow = 0;
    int x;
    int y;
    public WindowsManager(Screen screen, int x, int y){
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    public void addWindow(Window window){
        windows.add(window);
    }

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

    public void draw(int x, int y){
        windows.get(currentWindow).draw(screen, x, y, 1);

        windows.get(currentWindow).messageLoopHelper();
        if(windows.get(currentWindow).getSwitchToWindow() != -1){
            switchFrom(currentWindow, windows.get(currentWindow).getSwitchToWindow(), 1000, 1, 0);
        }
    }

    public void keyStroke(KeyStroke keyStroke){
        windows.get(currentWindow).keyStrokeHelper(keyStroke);
    }
}
