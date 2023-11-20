package ui.windows;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import ui.widgets.ISelectable;
import ui.widgets.ITextInput;
import ui.widgets.Widget;
import ui.widgets.WidgetTextBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a basic window in a user interface.
 * This class provides a foundation for creating windows in a user interface with text-based graphics.
 * It includes methods for adding, removing, and managing widgets within the window, handling user input,
 * and rendering the window's content on a screen.
 */
public class Window {

    protected char[][] buffer;
    protected TextColor[][] printColor;
    protected TextColor[][] backColor;
    protected List<Widget> widgets = new ArrayList<>();
    private Widget pointer;  // the widget that the pointer is pointing at
    private int lenX;
    private int lenY;
    private String windowName;
    private int selected = 0;
    protected int switchToWindow = -1;
    protected ArrayList<WindowOverlayClass> overlays;
    private int y;
    private int x;
    private int widgetID;
    private int needClear = 0;

    /**
     * Constructor for Window class.
     *
     * @param y The Y position of the window.
     * @param x The X position of the window.
     * @param windowName The name of the window.
     */
    public Window(int y, int x, String windowName) {
        buffer = new char[y][x];
        printColor = new TextColor[y][x];
        backColor = new TextColor[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                buffer[i][j] = ' ';
                printColor[i][j] = TextColor.ANSI.DEFAULT;
                backColor[i][j] = TextColor.ANSI.WHITE;
            }
        }
        lenX = x;
        lenY = y;
        this.windowName = windowName;
        selected = 0;
        overlays = new ArrayList<>();
        this.x = x;
        this.y = y;
        widgetID = 0;
    }

    /**
     * Gets the Y position of the window.
     *
     * @return The Y position.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the X position of the window.
     *
     * @return The X position.
     */
    public int getX() {
        return x;
    }

    /**
     * The main loop for processing window messages. This method should be overridden by subclasses to define specific behavior.
     */
    public void messageLoop(){
//        System.out.println(windowName + ": Error empty message loop.");
    }

    /**
     * The helper method for processing window messages. This method should be overridden by subclasses to define specific behavior.
     */
    public void messageLoopHelper(){
        if(overlays.size() != 0){
            overlays.get(overlays.size() - 1).messageLoop();
        }
        else{
            messageLoop();
        }
    }

    /**
     * onExit method for the window. This method should be overridden by subclasses to define specific behavior.
     */
    public void onExit(){
    }

    /**
     * switchToWindow method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @return The window to switch to.
     */
    public int getSwitchToWindow() {
        return switchToWindow;
    }

    /**
     * Gets the length of the window in the X direction.
     *
     * @return The length of the window in the X direction.
     */
    public int getLenX() {
        return lenX;
    }

    /**
     * Gets the length of the window in the Y direction.
     *
     * @return The length of the window in the Y direction.
     */
    public int getLenY() {
        return lenY;
    }

    /**
     * Selects the next selectable widget in the window.
     */
    public void selectNext(){
        if(widgets.get(selected) instanceof ISelectable) {
            ((ISelectable)widgets.get(selected)).unselect();
        }
        selected += 1;
        selected %= widgets.size();
        while(!(widgets.get(selected) instanceof ISelectable) || widgets.get(selected).isHide() || widgets.get(selected).getSkipSelection()){
            selected += 1;
            selected %= widgets.size();
        }
        ISelectable selectedWidget = (ISelectable) widgets.get(selected);
        selectedWidget.select();
    }

    /**
     * Selects the previous selectable widget in the window.
     */
    public void selectPrev(){
        if(widgets.get(selected) instanceof ISelectable) {
            ((ISelectable)widgets.get(selected)).unselect();
        }
        selected -= 1;
        selected += widgets.size();
        selected %= widgets.size();
        while(!(widgets.get(selected) instanceof ISelectable) || widgets.get(selected).isHide() || widgets.get(selected).getSkipSelection()){
            selected -= 1;
            selected += widgets.size();
            selected %= widgets.size();
        }
        ISelectable selectedWidget = (ISelectable) widgets.get(selected);
        selectedWidget.select();
    }

    /**
     * keyStroke method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @param keyStroke The key stroke to process.
     */
    public void keyStroke(KeyStroke keyStroke){
        if(keyStroke.getKeyType() == KeyType.ArrowRight || keyStroke.getKeyType() == KeyType.ArrowDown || keyStroke.getKeyType() == KeyType.Tab){
            selectNext();
        }
        else if(keyStroke.getKeyType() == KeyType.ArrowLeft || keyStroke.getKeyType() == KeyType.ArrowUp){
            selectPrev();
        }
        Widget target = widgets.get(selected);
        if(target instanceof ITextInput) {
            ITextInput input = (ITextInput) target;
            input.keyStroke(keyStroke);
        }
    }

    /**
     * keyStrokeHelper method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @param keyStroke The key stroke to process.
     */
    public void keyStrokeHelper(KeyStroke keyStroke){
        if(overlays.size() != 0){
            overlays.get(overlays.size() - 1).keyStroke(keyStroke);
        }
        else{
            keyStroke(keyStroke);
        }
    }

    /**
     * switchFrom method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @param oldWindow The window to switch from.
     * @param screen The screen to draw on.
     * @param x The X position of the window.
     * @param y The Y position of the window.
     * @param percentage The percentage of the transition.
     * @param directionX The X direction of the transition.
     * @param directionY The Y direction of the transition.
     */
    public void switchFrom(Window oldWindow, Screen screen, int x, int y, double percentage, double directionX, double directionY){
        percentage = Math.tanh((percentage - 0.5) * 4) / 1.928 + 0.5;
        clearArea(screen, x, x + oldWindow.getLenX()*2, y, y + oldWindow.getLenY()*2);
        oldWindow.draw(screen, (int)(x - oldWindow.getLenX() * directionX * percentage ), (int)(y - oldWindow.getLenY() * percentage * directionY), 1 - percentage);
        draw(screen, (int)(x + oldWindow.getLenX() * directionX - oldWindow.getLenX() * percentage * directionX), (int)(y + oldWindow.getLenY() * directionY - oldWindow.getLenY() * percentage * directionY), percentage);
    }

    /**
     * clearArea method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @param screen The screen to draw on.
     * @param startX The X position of the start of the area to clear.
     * @param endX The X position of the end of the area to clear.
     * @param startY The Y position of the start of the area to clear.
     * @param endY The Y position of the end of the area to clear.
     */
    public void clearArea(Screen screen, int startX, int endX, int startY, int endY){
        for(int i = startX ; i <= endX ; i ++){
            for(int j = startY ; j <= endY ; j++){
                TerminalPosition cellToModify = new TerminalPosition( i, j);
                TextCharacter textCharacter = screen.getBackCharacter(cellToModify);
                if(textCharacter != null) {
                    textCharacter = textCharacter.withForegroundColor(TextColor.ANSI.DEFAULT);
                    textCharacter = textCharacter.withBackgroundColor(TextColor.ANSI.DEFAULT);
                    textCharacter = textCharacter.withCharacter(' ');
                    screen.setCharacter(cellToModify, textCharacter);
                }
            }
        }
    }

    /**
     * Adds a widget to the window.
     *
     * @param widget The widget to be added.
     * @return The ID assigned to the widget.
     */
    public int addWidget(Widget widget) {
        widgets.add(widget);
        widget.setWidgetID(widgetID);
        widgetID += 1;
        return widgetID;
    }

    /**
     * getWidgetIndex method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @param widget The widget to get the index of.
     *
     * @return The index of the widget.
     */
    public int getWidgetIndex(Widget widget) {
        return widgets.indexOf(widget);
    }

    /**
     * removeWidget method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @param widget The widget to remove.
     *
     * @return The ID of the widget.
     */
    public int removeWidget(Widget widget){
        widgetID -= 1;
        widgets.remove(widget);
        return widgetID;
    }

    /**
     * addWidgetAfter method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @param widget The widget to add.
     * @param afterWhich The index of the widget to add after.
     *
     * @return The ID of the widget.
     */
    public int addWidgetAfter(Widget widget, int afterWhich) {
        widgets.add(afterWhich, widget);
        widget.setWidgetID(widgetID);
        widgetID += 1;
        return widgetID;
    }

    /**
     * setClear method for the window. This method should be overridden by subclasses to define specific behavior.
     */
    public void setClear(){
//        needClear = 2;
    }


    public void draw(Screen screen, int x, int y, double transparency) {
        if(needClear > 0){
            screen.clear();
            needClear -= 1;
        }
        if(widgets.get(selected) instanceof ISelectable) {
            ((ISelectable)widgets.get(selected)).select();
        }
        boolean ifSetCursorNull = true;
        for (Widget widget : widgets) {
            widget.draw(buffer, printColor, backColor);
            if(widget.getSelected() && (widget instanceof WidgetTextBox)){
                TerminalPosition curPosition = new TerminalPosition( x + widget.getX() + widget.getText().length() + 1, y+ widget.getY() );
                screen.setCursorPosition(curPosition);
                ifSetCursorNull = false;
            }
        }
        if(ifSetCursorNull){
            screen.setCursorPosition(null);
        }
        for (int i = 0; i < buffer.length; i++) {
            for (int j = 0; j < buffer[i].length; j++) {
                int modY = i + y;
                int modX = j + x;
                TerminalPosition cellToModify = new TerminalPosition( modX, modY );
                TextCharacter textCharacter = screen.getBackCharacter(cellToModify);
                if(textCharacter != null) {
                    textCharacter = textCharacter.withForegroundColor(printColor[i][j]);
                    textCharacter = textCharacter.withBackgroundColor(backColor[i][j]);
                    textCharacter = textCharacter.withCharacter(buffer[i][j]);
                    screen.setCharacter(cellToModify, textCharacter);
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < buffer.length + 1; i++) {
                int modY = y + i;
                int modX = buffer[0].length + x + j;
                TerminalPosition cellToModify = new TerminalPosition(modX, modY);
                TextCharacter textCharacter = screen.getBackCharacter(cellToModify);
                if(textCharacter != null) {
                    textCharacter = textCharacter.withForegroundColor(TextColor.ANSI.DEFAULT);
                    textCharacter = textCharacter.withBackgroundColor(TextColor.ANSI.DEFAULT);
                    textCharacter = textCharacter.withCharacter(' ');
                    screen.setCharacter(cellToModify, textCharacter);
                }
            }
        }
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < buffer[0].length + 1; i++) {
                int modY = buffer.length + y + j;
                int modX = x + i;
                TerminalPosition cellToModify = new TerminalPosition(modX, modY);
                TextCharacter textCharacter = screen.getBackCharacter(cellToModify);
                if(textCharacter != null) {
                    textCharacter = textCharacter.withForegroundColor(TextColor.ANSI.DEFAULT);
                    textCharacter = textCharacter.withBackgroundColor(TextColor.ANSI.DEFAULT);
                    textCharacter = textCharacter.withCharacter(' ');
                    screen.setCharacter(cellToModify, textCharacter);
                }
            }
        }
        for(int i = 0 ; i < getLenX() ; i++){
            TerminalPosition cellToModify = new TerminalPosition( i + x, y );
            TextCharacter textCharacter = screen.getBackCharacter(cellToModify);
            if(textCharacter != null) {
                textCharacter = textCharacter.withForegroundColor(TextColor.ANSI.WHITE);
                textCharacter = textCharacter.withBackgroundColor(TextColor.ANSI.BLACK_BRIGHT);
                screen.setCharacter(cellToModify, textCharacter);
            }
        }
        for(int i = 0 ; i < windowName.length() ; i++){
            TerminalPosition cellToModify = new TerminalPosition( i + 1 + x, y );
            TextCharacter textCharacter = screen.getBackCharacter(cellToModify);
            if(textCharacter != null) {
                textCharacter = textCharacter.withForegroundColor(TextColor.ANSI.WHITE);
                textCharacter = textCharacter.withBackgroundColor(TextColor.ANSI.BLACK_BRIGHT);
                textCharacter = textCharacter.withCharacter(windowName.charAt(i));
                screen.setCharacter(cellToModify, textCharacter);
            }
        }
        // Draw Overlay
        ArrayList<WindowOverlayClass> newOverlays = new ArrayList<>();
        for(WindowOverlayClass overlay : overlays){
            if(!overlay.getDestroy()){
                newOverlays.add(overlay);
            }
            else{
                overlay.clearArea(screen);
                overlay.onExit();
                screen.clear();
            }
        }
        overlays = newOverlays;
        for(WindowOverlayClass overlay : overlays){
            overlay.draw(screen, x, y, transparency);
        }
        String sizeLabel = windowName;
    }

    /**
     * setPointer method for the window. This method should be overridden by subclasses to define specific behavior.
     *
     * @param widget The widget to set as the pointer.
     */
    public void setPointer(Widget widget) {
        this.pointer = widget;
    }

    /**
     * Adds an overlay to the window.
     *
     * @param windowOverlayClass The overlay to be added.
     */
    public void addOverlay(WindowOverlayClass windowOverlayClass){
        this.overlays.add(windowOverlayClass);
    }
}
