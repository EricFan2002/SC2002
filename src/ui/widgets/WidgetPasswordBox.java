package ui.widgets;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

/**
 * WidgetPasswordBox represents a password input field in a user interface.
 * It behaves like a text input field but does not display entered characters.
 */
public class WidgetPasswordBox extends WidgetTextBox {

    /**
     * Constructs a WidgetPasswordBox with default text alignment to the left.
     *
     * @param x The x-coordinate of the widget.
     * @param y The y-coordinate of the widget.
     * @param len The length of the widget.
     * @param text The initial text of the widget (usually empty for password fields).
     */
    public WidgetPasswordBox(int x, int y, int len, String text) {
        super(x, y, len, text);
        // Optional: Set text alignment if needed
    }

    /**
     * Constructs a WidgetPasswordBox with specified text alignment.
     *
     * @param x The x-coordinate of the widget.
     * @param y The y-coordinate of the widget.
     * @param len The length of the widget.
     * @param text The initial text of the widget (usually empty for password fields).
     * @param textAlignment The alignment of the text within the widget.
     */
    public WidgetPasswordBox(int x, int y, int len, String text, TEXT_ALIGNMENT textAlignment) {
        super(x, y, len, text, textAlignment);
        // Optional: Additional constructor logic if required
    }

    /**
     * Overrides the draw method to not display the entered characters.
     *
     * @param buffer The buffer to draw the widget to.
     * @param printColor The color of the text.
     * @param backColor The color of the background.
     */
    @Override
    public void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor) {
        drawSelection(buffer, printColor, backColor);
        drawMaskedText(buffer);
        if(selected){
            buffer[y][x] = '[';
            buffer[y][x + getLen() - 1] = ']';
        }
        else{
            buffer[y][x] = ' ';
            buffer[y][x + getLen() - 1] = ' ';
        }
    }

    private void drawMaskedText(char[][] buffer) {
        for (int i = 0; i < text.length() && i < getLen() - 2; i++) {
            buffer[y][x + i + 1] = '*';  // Replace with '*' or another masking character
        }
    }

    // You can override other methods as needed, depending on your application's requirements
}
