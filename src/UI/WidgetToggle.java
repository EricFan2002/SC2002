package UI;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class WidgetToggle extends Widget implements IClickable, ITextInput, ISelectable{
    private boolean pressed;
    String rawText;
    public WidgetToggle(int x, int y, int len, String text) {
        super(x, y, len, "✖ " + text);
        rawText = text;
        pressed = false;
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_MID;
    }
    public WidgetToggle(int x, int y, int len, String text, TEXT_ALIGNMENT textAlignment) {
        super(x, y, len, "✖ " + text);
        rawText = text;
        pressed = false;
        this.textAlignment = textAlignment;
    }

    public void select(){
        selected = true;
    }
    public void unselect(){
        selected = false;
    }

    public boolean getPressed(){
        return pressed;
    }
    public void clearPressed(){
        pressed = false;
    }
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
        }
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
