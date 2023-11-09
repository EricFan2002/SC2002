package ui.widgets;

import com.googlecode.lanterna.TextColor;

public interface ISelectable {
    public void drawSelection(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor);
    public void select();
    public void unselect();
}
