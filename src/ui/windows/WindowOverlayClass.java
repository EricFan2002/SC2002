package ui.windows;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

/**
 * Class representing an overlay window with additional offset and destruction capabilities.
 * This class extends the base {@link Window} class and adds the ability to position the window with an offset
 * and control its destruction.
 */
public class WindowOverlayClass extends Window{
    protected int offsetX;
    protected int offsetY;
    private boolean destroy;

    /**
     * Constructor for WindowOverlayClass.
     *
     * @param y The Y position of the window.
     * @param x The X position of the window.
     * @param offsetY The Y offset.
     * @param offsetX The X offset.
     * @param windowName The name of the window.
     */
    public WindowOverlayClass(int y, int x, int offsetY, int offsetX, String windowName) {
        super(y, x, windowName);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        destroy = false;
    }


    /**
     * Draws the window on the screen with the specified transparency and offset.
     *
     * @param screen The screen to draw on.
     * @param x The X position to draw at.
     * @param y The Y position to draw at.
     * @param transparency The transparency level of the window.
     */
    @Override
    public void draw(Screen screen, int x, int y, double transparency) {
        super.draw(screen, x + offsetX, y + offsetY, transparency);
    }

    /**
     * Gets the status of whether this overlay window should be destroyed.
     *
     * @return {@code true} if the window should be destroyed, {@code false} otherwise.
     */
    public boolean getDestroy(){
        return destroy;
    }

    /**
     * Overrides the default message loop behavior to handle overlays if present.
     */
    @Override
    public void messageLoop() {
        if(overlays.size() != 0){
            overlays.get(overlays.size() - 1).messageLoop();
        }
        else {
            super.messageLoop();
        }
    }

    /**
     * Sets the status to destroy this overlay window.
     */
    public void setDestroy(){
        destroy = true;
    }

    /**
     * Clears the area occupied by this overlay window on the screen.
     *
     * @param screen The screen to clear the area on.
     */
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
