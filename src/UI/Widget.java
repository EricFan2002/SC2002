package UI;

import com.googlecode.lanterna.TextColor;

public abstract class Widget implements IDrawable {
    protected int x, y, len;
    protected boolean selected;
    protected String text;
    TEXT_ALIGNMENT textAlignment;
    private int widgetID;

    public Widget(int x, int y, int len, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.len = len;
        this.widgetID = -1;
        textAlignment = TEXT_ALIGNMENT.ALIGN_LEFT;
    }
    public int getLen() {
        return len;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void drawText(char[][] buffer){
        int blank = 1;
        if(textAlignment == TEXT_ALIGNMENT.ALIGN_MID){
            blank = (getLen() - text.length()) / 2;
        }
        else if(textAlignment == TEXT_ALIGNMENT.ALIGN_RIGHT){
            blank = (getLen() - text.length());
        }
        for (int i = 0; i < text.length(); i++) {
            buffer[y][x + i + blank] = text.charAt(i);
        }
    }

    public void setWidgetID(int id){
        widgetID = id;
    }

    public int getWidgetID(){
        return widgetID;
    }


    public abstract void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor);
}
