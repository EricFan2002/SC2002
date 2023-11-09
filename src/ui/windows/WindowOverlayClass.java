package ui.windows;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

public class WindowOverlayClass extends Window{
    private int offsetX;
    private int offsetY;
    private boolean destroy;
    public WindowOverlayClass(int y, int x, int offsetY, int offsetX, String windowName) {
        super(y, x, windowName);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        destroy = false;
    }

    @Override
    public void draw(Screen screen, int x, int y, double transparency) {
        super.draw(screen, x + offsetX, y + offsetY, transparency);
    }

    public boolean getDestroy(){
        return destroy;
    }
    public void setDestroy(){
        destroy = true;
    }

    public void clearArea(Screen screen){
        for (int i = 0; i < buffer.length * 2; i++) {
            for (int j = 0; j < buffer[0].length * 2; j++) {
                int modY = i + getY();
                int modX = j + getX();
                TerminalPosition cellToModify = new TerminalPosition( modX, modY );
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

}
