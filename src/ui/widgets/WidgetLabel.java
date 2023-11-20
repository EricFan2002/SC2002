package ui.widgets;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;

public class WidgetLabel extends Widget {
    public WidgetLabel(int x, int y, int len, String text) {
        super(x, y, len, text);
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_LEFT;
    }

    public WidgetLabel(int x, int y, int len, String text, TEXT_ALIGNMENT textAlignment) {
        super(x, y, len, text);
        this.textAlignment = textAlignment;
    }

    @Override
    public void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor) {
        drawText(buffer);
//        for (int i = 0; i < getLen(); i++) {
//            printColor[y][x + i] = ANSI.WHITE_BRIGHT;
//        }
    }
}
