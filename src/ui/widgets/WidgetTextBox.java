package ui.widgets;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class WidgetTextBox extends Widget implements ITextInput, ISelectable {
    public WidgetTextBox(int x, int y, int len, String text) {
        super(x, y, len, text);
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_LEFT;
    }
    public WidgetTextBox(int x, int y, int len, String text, TEXT_ALIGNMENT textAlignment) {
        super(x, y, len, text);
        this.textAlignment = textAlignment;
    }
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
    public void select(){
        selected = true;
    }
    public void unselect(){
        selected = false;
    }
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