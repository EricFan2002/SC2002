package ui.widgets;

import com.googlecode.lanterna.TextColor;

/**
 * Interface for selectable widgets.
 */
public interface ISelectable {
    /**
     * Draws the selection on the buffer.
     * @param buffer The buffer to draw on.
     * @param printColor The color of the text.
     * @param backColor The color of the background.
     */
    public void drawSelection(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor);
    /**
     * Selects the widget.
     */
    public void select();
    /**
     * Unselects the widget.
     */
    public void unselect();
}
