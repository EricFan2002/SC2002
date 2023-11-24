package ui.widgets;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

/**
 * WidgetButton is a UI widget that represents a button. It extends the Widget class
 * and implements IClickable, ITextInput, and ISelectable interfaces.
 */
public class WidgetButton extends Widget implements ITextInput, ISelectable{
    private boolean pressed;

    /**
     * Constructs a WidgetButton with specified position, length, and text.
     * The button is initially not pressed.
     *
     * @param x The x-coordinate of the button.
     * @param y The y-coordinate of the button.
     * @param len The length of the button.
     * @param text The text displayed on the button.
     */
    public WidgetButton(int x, int y, int len, String text) {
        super(x, y, len, text);
        pressed = false;
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_MID;
    }

    /**
     * Constructs a WidgetButton with specified position, length, text, and text alignment.
     * The button is initially not pressed.
     *
     * @param x The x-coordinate of the button.
     * @param y The y-coordinate of the button.
     * @param len The length of the button.
     * @param text The text displayed on the button.
     * @param textAlignment The text alignment of the button.
     */
    public WidgetButton(int x, int y, int len, String text, TEXT_ALIGNMENT textAlignment) {
        super(x, y, len, text);
        pressed = false;
        this.textAlignment = textAlignment;
    }

    /**
     * Marks the button as selected.
     */
    public void select(){
        selected = true;
    }

    /**
     * Marks the button as unselected.
     */
    public void unselect(){
        selected = false;
    }

    /**
     * Returns the pressed status of the button.
     *
     * @return true if the button is pressed, false otherwise.
     */
    public boolean getPressed(){
        return pressed;
    }

    /**
     * Clears the pressed status of the button.
     */
    public void clearPressed(){
        pressed = false;
    }

    /**
     * Returns the text displayed on the button.
     *
     * @param buffer The buffer to draw the text on.
     * @param printColor The print color of the text.
     * @param backColor The background color of the text.
     */
    @Override
    public void drawSelection(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor) {
        for (int i = 0; i < getLen(); i++) {
            buffer[y][x + i] = ' ';
            if(!pressed) {
                if(selected){
                    backColor[y][x + i] = TextColor.ANSI.BLACK_BRIGHT;
                    printColor[y][x + i] = TextColor.ANSI.CYAN_BRIGHT;
                }
                else {
                    backColor[y][x + i] = TextColor.ANSI.BLACK_BRIGHT;
                    printColor[y][x + i] = TextColor.ANSI.WHITE_BRIGHT;
                }
            }
            else {
                backColor[y][x + i] = TextColor.ANSI.WHITE_BRIGHT;
                printColor[y][x + i] = TextColor.ANSI.MAGENTA;
            }
        }
    }

    /**
     * Draws the text displayed on the button.
     *
     * @param buffer The buffer to draw the text on.
     * @param printColor The print color of the text.
     * @param backColor The background color of the text.
     */
    @Override
    public void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor) {
        if(hide){
            return;
        }
        else {
            drawSelection(buffer, printColor, backColor);
            drawText(buffer);
            if (selected) {
                buffer[y][x] = '[';
                buffer[y][x + getLen() - 1] = ']';
            } else {
                buffer[y][x] = ' ';
                buffer[y][x + getLen() - 1] = ' ';
            }
        }
    }

    /**
     * Handles a keystroke input and changes the pressed status if the key is Enter.
     *
     * @param keyStroke The KeyStroke input to handle.
     */
    public void keyStroke(KeyStroke keyStroke){
        if(keyStroke.getKeyType() == KeyType.Enter){
            pressed = true;
        }
    }
}