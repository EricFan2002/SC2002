package app.ui.widgets;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

/**
 * WidgetToggle represents a toggle button in a user interface.
 * It can be pressed or unpressed, changing its state and appearance.
 */
public class WidgetToggle extends Widget implements ITextInput, ISelectable{
    private boolean pressed;
    String rawText;

    /**
     * Constructs a WidgetToggle with a specified text and default alignment.
     *
     * @param x The x-coordinate of the widget.
     * @param y The y-coordinate of the widget.
     * @param len The length of the widget.
     * @param text The text displayed next to the toggle.
     */
    public WidgetToggle(int x, int y, int len, String text) {
        super(x, y, len, "✖ " + text);
        rawText = text;
        pressed = false;
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_MID;
    }

    /**
     * Constructs a WidgetToggle with a specified text and alignment.
     *
     * @param x The x-coordinate of the widget.
     * @param y The y-coordinate of the widget.
     * @param len The length of the widget.
     * @param text The text displayed next to the toggle.
     * @param textAlignment The alignment of the text within the widget.
     */
    public WidgetToggle(int x, int y, int len, String text, TEXT_ALIGNMENT textAlignment) {
        super(x, y, len, "✖ " + text);
        rawText = text;
        pressed = false;
        this.textAlignment = textAlignment;
    }

    /**
     * Sets the toggle to a selected state.
     */
    public void select(){
        selected = true;
    }

    /**
     * Sets the toggle to an unselected state.
     */
    public void unselect(){
        selected = false;
    }

    /**
     * Returns the pressed state of the toggle.
     *
     * @return true if the toggle is pressed, false otherwise.
     */
    public boolean getPressed(){
        return pressed;
    }

    /**
     * Clears the pressed state of the toggle, setting it to unpressed.
     */
    public void clearPressed(){
        this.text = "✖ " + rawText;
        pressed = false;
    }

    /**
     * Sets the toggle to a pressed state.
     */
    public void setPressed(){
        this.text = "✓ " + rawText;
        pressed = true; }

    /**
     * Draws the selection state of the toggle on the provided buffer.
     *
     * @param buffer The character buffer for drawing the widget.
     * @param printColor The color buffer for text color.
     * @param backColor The color buffer for background color.
     */
    @Override
    public void drawSelection(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor) {
        for (int i = 0; i < getLen(); i++) {
            buffer[y][x + i] = ' ';
            if(selected){
                backColor[y][x + i] = TextColor.ANSI.BLACK_BRIGHT;
                printColor[y][x + i] = TextColor.ANSI.CYAN_BRIGHT;
            }
            else {
                backColor[y][x + i] = TextColor.ANSI.BLACK_BRIGHT;
                printColor[y][x + i] = TextColor.ANSI.WHITE_BRIGHT;
            }
            if(pressed){
                printColor[y][x + i] = TextColor.ANSI.CYAN_BRIGHT;
            }
        }
    }

    /**
     * Draws the toggle widget on the provided buffer.
     *
     * @param buffer The character buffer for drawing the widget.
     * @param printColor The color buffer for text color.
     * @param backColor The color buffer for background color.
     */
    @Override
    public void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor) {
        drawSelection(buffer, printColor, backColor);
        drawText(buffer);
        if(selected){
            buffer[y][x] = '[';
            buffer[y][x + getLen() - 1] = ']';
        }
        else{
            buffer[y][x] = ' ';
            buffer[y][x + getLen() - 1] = ' ';
        }
    }

    /**
     * Handles a key stroke input for the toggle.
     *
     * @param keyStroke The key stroke input.
     */
    public void keyStroke(KeyStroke keyStroke){
        if(keyStroke.getKeyType() == KeyType.Enter){
            pressed = !pressed;
        }
        if(pressed){
            text = "✓ " + rawText;
        }
        else{
            text = "✖ " + rawText;
        }
    }
}
