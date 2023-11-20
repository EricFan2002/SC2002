package ui.widgets;

import com.googlecode.lanterna.TextColor;

/**
 * Interface for drawable widgets.
 */
public interface IDrawable {
    /**
     * Draw the widget to the buffer.
     * @param buffer The buffer to draw to.
     */
    public void drawText(char[][] buffer);
    /**
     * Draw the widget to the buffer.
     * @param buffer The buffer to draw to.
     * @param printColor The color to print the text in.
     * @param backColor The color to print the background in.
     */
    abstract void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor);
}
