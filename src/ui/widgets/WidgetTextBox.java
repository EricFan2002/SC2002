package ui.widgets;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

/**
 * WidgetTextBox represents a text input field in a user interface.
 * It allows for text input and display, and can be selected or unselected.
 */
public class WidgetTextBox extends Widget implements ITextInput, ISelectable {
    /**
     * Constructs a WidgetTextBox with default text alignment to the left.
     *
     * @param x The x-coordinate of the widget.
     * @param y The y-coordinate of the widget.
     * @param len The length of the widget.
     * @param text The initial text of the widget.
     */
    public WidgetTextBox(int x, int y, int len, String text) {
        super(x, y, len, text);
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_LEFT;
    }

    /**
     * Constructs a WidgetTextBox with specified text alignment.
     *
     * @param x The x-coordinate of the widget.
     * @param y The y-coordinate of the widget.
     * @param len The length of the widget.
     * @param text The initial text of the widget.
     * @param textAlignment The alignment of the text within the widget.
     */
    public WidgetTextBox(int x, int y, int len, String text, TEXT_ALIGNMENT textAlignment) {
        super(x, y, len, text);
        this.textAlignment = textAlignment;
    }

    /**
     * Constructs a WidgetTextBox with default text alignment to the left.
     *
     * @param buffer The buffer to draw the widget to.
     * @param printColor The color of the text.
     * @param backColor The color of the background.
     */
    public void drawSelection(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor){
        for (int i = 0; i < getLen(); i++) {
            buffer[y][x + i] = ' ';
            if(selected){
                backColor[y][x + i] = TextColor.ANSI.WHITE_BRIGHT;
                printColor[y][x + i] = TextColor.ANSI.MAGENTA;
            }
            else {
                backColor[y][x + i] = TextColor.ANSI.WHITE_BRIGHT;
                printColor[y][x + i] = TextColor.ANSI.BLACK_BRIGHT;
            }
        }
    }

    /**
     * Selects the widget.
     */
    public void select(){
        selected = true;
    }

    /**
     * Unselects the widget.
     */
    public void unselect(){
        selected = false;
    }

    /**
     * draws the widget to the buffer.
     *
     * @param buffer The buffer to draw the widget to.
     * @param printColor The color of the text.
     * @param backColor The color of the background.
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
     * keyStroke processes a key stroke.
     *
     * @param keyStroke The key stroke to process.
     */
    public void keyStroke(KeyStroke keyStroke){
        if(keyStroke.getKeyType() == KeyType.Backspace){
            if(text.length() >= 1){
                text = text.substring(0, text.length() - 1);
            }
        }
        else if(keyStroke.getKeyType() == KeyType.Character){
            text = text + keyStroke.getCharacter();
        }
    }
}