package ui.widgets;

import com.googlecode.lanterna.TextColor;

public interface IDrawable {
    public void drawText(char[][] buffer);
    abstract void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor);
}
