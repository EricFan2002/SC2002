package app.ui.widgets;

import com.googlecode.lanterna.TextColor;

/**
 * WidgetLabel is a UI widget that represents a label. It extends the Widget class.
 */
public class WidgetLabel extends Widget {
    /**
     * Constructs a WidgetLabel with specified position, length, and text.
     *
     * @param x The x-coordinate of the label.
     * @param y The y-coordinate of the label.
     * @param len The length of the label.
     * @param text The text displayed on the label.
     */
    public WidgetLabel(int x, int y, int len, String text) {
        super(x, y, len, text);
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_LEFT;
    }

    /**
     * Constructs a WidgetLabel with specified position, length, text, and text alignment.
     *
     * @param x The x-coordinate of the label.
     * @param y The y-coordinate of the label.
     * @param len The length of the label.
     * @param text The text displayed on the label.
     * @param textAlignment The text alignment of the label.
     */
    public WidgetLabel(int x, int y, int len, String text, TEXT_ALIGNMENT textAlignment) {
        super(x, y, len, text);
        this.textAlignment = textAlignment;
    }

    /**
     * Draws the label on the given buffer with specified colors.
     *
     * @param buffer The character buffer for drawing the label.
     * @param printColor The colors for the printed characters.
     * @param backColor The background colors.
     */
    @Override
    public void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor) {
        drawText(buffer);
//        for (int i = 0; i < getLen(); i++) {
//            printColor[y][x + i] = ANSI.WHITE_BRIGHT;
//        }
    }
}
