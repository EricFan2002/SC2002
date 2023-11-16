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

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void messageLoop(){
//        System.out.println(windowName + ": Error empty message loop.");
    }

    public void messageLoopHelper(){
        if(overlays.size() != 0){
            overlays.get(overlays.size() - 1).messageLoop();
        }
        else{
            messageLoop();
        }
    }

    public void onExit(){
    }

    public int getSwitchToWindow() {
        return switchToWindow;
    }

    public int getLenX() {
        return lenX;
    }

    public int getLenY() {
        return lenY;
    }

    public void selectNext(){
        if(widgets.get(selected) instanceof ISelectable) {
            ((ISelectable)widgets.get(selected)).unselect();
        }
        selected += 1;
        selected %= widgets.size();
        while(!(widgets.get(selected) instanceof ISelectable) || widgets.get(selected).isHide()){
            selected += 1;
            selected %= widgets.size();
        }
        ISelectable selectedWidget = (ISelectable) widgets.get(selected);
        selectedWidget.select();
    }

    public void selectPrev(){
        if(widgets.get(selected) instanceof ISelectable) {
            ((ISelectable)widgets.get(selected)).unselect();
        }
        selected -= 1;
        selected += widgets.size();
        selected %= widgets.size();
        while(!(widgets.get(selected) instanceof ISelectable) || widgets.get(selected).isHide()){
            selected -= 1;
            selected += widgets.size();
            selected %= widgets.size();
        }
        ISelectable selectedWidget = (ISelectable) widgets.get(selected);
        selectedWidget.select();
    }

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

    public void keyStrokeHelper(KeyStroke keyStroke){
        if(overlays.size() != 0){
            overlays.get(overlays.size() - 1).keyStroke(keyStroke);
        }
        else{
            keyStroke(keyStroke);
        }
    }

    public void switchFrom(Window oldWindow, Screen screen, int x, int y, double percentage, double directionX, double directionY){
        percentage = Math.tanh((percentage - 0.5) * 4) / 1.928 + 0.5;
        clearArea(screen, x, x + oldWindow.getLenX()*2, y, y + oldWindow.getLenY()*2);
        oldWindow.draw(screen, (int)(x - oldWindow.getLenX() * directionX * percentage ), (int)(y - oldWindow.getLenY() * percentage * directionY), 1 - percentage);
        draw(screen, (int)(x + oldWindow.getLenX() * directionX - oldWindow.getLenX() * percentage * directionX), (int)(y + oldWindow.getLenY() * directionY - oldWindow.getLenY() * percentage * directionY), percentage);
    }

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

    public int addWidget(Widget widget) {
        widgets.add(widget);
        widget.setWidgetID(widgetID);
        widgetID += 1;
        return widgetID - 1;
    }

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
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < buffer.length; i++) {
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
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < buffer[0].length; i++) {
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

    public void setPointer(Widget widget) {
        this.pointer = widget;
    }
    public void addOverlay(WindowOverlayClass windowOverlayClass){
        this.overlays.add(windowOverlayClass);
    }
}
