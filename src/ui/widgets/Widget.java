package ui.widgets;

import com.googlecode.lanterna.TextColor;

public abstract class Widget implements IDrawable {
    protected int x, y, len;
    protected boolean selected;
    protected String text;
    TEXT_ALIGNMENT textAlignment;
    private int widgetID;
    protected boolean hide;
    private boolean skipSelection = false;

    public Widget(int x, int y, int len, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.len = len;
        this.widgetID = -1;
        this.hide = false;
        textAlignment = TEXT_ALIGNMENT.ALIGN_LEFT;
    }

    public boolean isHide() {
        return hide;
    }
    public boolean getSkipSelection(){
        return skipSelection;
    }
    public void setSkipSelection(boolean skipSelection){
        this.skipSelection = skipSelection;
    }

    public void setHide(boolean ifHide){
        hide = ifHide;
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
    public boolean getSelected(){
        return selected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void drawText(char[][] buffer){
        if(hide)
            return;
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
