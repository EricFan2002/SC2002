package app.ui.widgets;

import com.googlecode.lanterna.TextColor;

/**
 * Abstract class for all widgets in the UI package.
 * This class provides basic properties and functionalities
 * common to all widgets, such as position, text, and visibility.
 */
public abstract class Widget implements IDrawable {
    protected int x, y, len;
    protected boolean selected;
    protected String text;
    TEXT_ALIGNMENT textAlignment;
    private int widgetID;
    protected boolean hide;
    private boolean skipSelection = false;

    /**
     * Constructor for Widget.
     * @param x x coordinate of the widget.
     * @param y y coordinate of the widget.
     * @param len length of the widget.
     * @param text text of the widget.
     */
    public Widget(int x, int y, int len, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.len = len;
        this.widgetID = -1;
        this.hide = false;
        textAlignment = TEXT_ALIGNMENT.ALIGN_LEFT;
    }

    /**
     * Checks if the widget is hidden.
     *
     * @return true if the widget is hidden, false otherwise.
     */
    public boolean isHide() {
        return hide;
    }

    /**
     * Checks if the widget's selection is skipped.
     *
     * @return true if the widget's selection is skipped, false otherwise.
     */
    public boolean getSkipSelection(){
        return skipSelection;
    }

    /**
     * Sets whether the widget's selection should be skipped.
     *
     * @param skipSelection true to skip the selection, false otherwise.
     */
    public void setSkipSelection(boolean skipSelection){
        this.skipSelection = skipSelection;
    }

    /**
     * Sets the visibility of the widget.
     *
     * @param ifHide true to hide the widget, false to show it.
     */
    public void setHide(boolean ifHide){
        hide = ifHide;
    }

    /**
     * Returns the length of the widget.
     *
     * @return The length of the widget.
     */
    public int getLen() {
        return len;
    }

    /**
     * Returns the text of the widget.
     *
     * @return The text of the widget.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the widget.
     *
     * @param text The text to set for the widget.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Checks if the widget is selected.
     *
     * @return true if the widget is selected, false otherwise.
     */
    public boolean getSelected(){
        return selected;
    }

    /**
     * Returns the x-coordinate of the widget.
     *
     * @return The x-coordinate of the widget.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the widget.
     *
     * @return The y-coordinate of the widget.
     */
    public int getY() {
        return y;
    }

    /**
     * Draws the text of the widget onto a buffer.
     *
     * @param buffer The character buffer to draw onto.
     */
    public void drawText(char[][] buffer){
        if(hide)
            return;
        String renderText = text;
        if(renderText.length() + 2 > getLen()){
            renderText = renderText.substring(Math.max(0, renderText.length() - getLen()), renderText.length() - 1);
        }
        int blank = 1;
        if(textAlignment == TEXT_ALIGNMENT.ALIGN_MID){
            blank = (getLen() - renderText.length()) / 2;
        }
        else if(textAlignment == TEXT_ALIGNMENT.ALIGN_RIGHT){
            blank = (getLen() - renderText.length());
        }
        for (int i = 0; i < renderText.length(); i++) {
            buffer[y][x + i + blank] = renderText.charAt(i);
        }
    }

    /**
     * Sets the widget ID.
     *
     * @param id The new ID for the widget.
     */
    public void setWidgetID(int id){
        widgetID = id;
    }

    /**
     * Returns the widget ID.
     *
     * @return The ID of the widget.
     */
    public int getWidgetID(){
        return widgetID;
    }

    /**
     * Abstract method to draw the widget. This method should be implemented by subclasses.
     *
     * @param buffer     The character buffer to draw onto.
     * @param printColor The array of text colors for each character.
     * @param backColor  The array of background colors for each character.
     */
    public abstract void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor);
}
