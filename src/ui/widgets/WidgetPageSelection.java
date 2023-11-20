package ui.widgets;

import com.googlecode.lanterna.TextColor;
import ui.windows.Window;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * WidgetPageSelection represents a paginated selection widget in a UI.
 * This widget allows displaying a list of options spread across multiple pages.
 */
public class WidgetPageSelection extends Widget {
    private int selectedOption = -1;
    List<List<WidgetButton>> selectionsButton = new ArrayList<>();
    WidgetButton nextButton;
    WidgetButton prevButton;
    private int height;
    private Window mainWindow;
    private int currentPage = 0;
    private int maxPage = 0;
    private int perPage = 0;
    private int itemHeight = 0;
    private int updateAt = 0;
    private int addCounter = 0;
    /**
     * Constructor for creating a WidgetPageSelection.
     *
     * @param x The x-coordinate of the widget.
     * @param y The y-coordinate of the widget.
     * @param len Length of the widget.
     * @param height Height of the widget.
     * @param text Text associated with the widget.
     * @param selections List of selection options.
     * @param mainWindow The main window in which the widget is displayed.
     */
    public WidgetPageSelection(int x, int y, int len, int height, String text, ArrayList<ArrayList<String>> selections, Window mainWindow) {
        super(x, y, len, text);
        addCounter = 0;
        selectedOption = -1;
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_MID;
        this.height = height;
        this.mainWindow = mainWindow;
        if(selections.size() > 0) {
            itemHeight = selections.get(0).size();
        }
        else{
            itemHeight = 1;
        }
        maxPage = selections.size() * itemHeight / (height - 1);
        perPage = (height - 1) / itemHeight;

        prevButton = new WidgetButton(x, getY() + height - 1, len / 2, "Prev");
        int second = len / 2;
        if(len % 2 == 1)
            second++;
        nextButton = new WidgetButton((x + getLen() / 2), getY() + height - 1, second, "Next");
        addSelections(selections);
    }

    /**
     * Updates the list of selections in the widget.
     *
     * @param selections New list of selections to be updated.
     */
    public void updateList(ArrayList<ArrayList<String>> selections){
        mainWindow.removeWidget(prevButton);
        mainWindow.removeWidget(nextButton);
        addCounter = 0;
        for(List<WidgetButton> list : selectionsButton){
            for(WidgetButton button: list){
                mainWindow.removeWidget(button);
            }
        }
        selectionsButton = new ArrayList<>();
        updateAt = mainWindow.getWidgetIndex(prevButton);
        if(selections.size() > 0) {
            itemHeight = selections.get(0).size();
        }
        else{
            itemHeight = 1;
        }
        selectedOption = -1;
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_MID;
        maxPage = (int)Math.ceil((double)selections.size() / (height - 1) / itemHeight);
        perPage = (height - 1) / itemHeight;
        addSelections(selections);
        currentPage = 0;
    }

    /**
     * Gets the index of the currently selected option.
     *
     * @return Index of selected option.
     */
    public int getSelectedOption(){
        return selectedOption;
    }

    /**
     * Clears the currently selected option.
     */
    public void clearSelectedOption(){
        selectedOption = -1;
    }

    /**
     * Gets the list of buttons in the widget.
     *
     * @return List of buttons in the widget.
     */
    public List<List<WidgetButton>> getSelectionsButton() {
        return selectionsButton;
    }

    /**
     * Adds the list of selections to the widget.
     *
     * @param selections List of selections to be added.
     */
    public void addSelections(ArrayList<ArrayList<String>> selections){
        for(int i = 0 ; i < selections.size() ; i++){
            int position = getY() + ((i % (perPage))) * itemHeight;
            List<WidgetButton> buttons = new ArrayList<>();
            WidgetButton button = new WidgetButton(x, position, len, selections.get(i).get(0), TEXT_ALIGNMENT.ALIGN_LEFT);
            buttons.add(button);
            mainWindow.addWidget(button);
            for(int j = 1 ; j < itemHeight ; j++) {
                button = new WidgetButton(x, position + j, len, selections.get(i).get(j), TEXT_ALIGNMENT.ALIGN_LEFT);
                button.setSkipSelection(true);
                buttons.add(button);
                mainWindow.addWidget(button);
            }
            selectionsButton.add(buttons);
            addCounter += 1;
        }
        int padding = ((selections.size()) % (perPage)) * itemHeight;
        int extra = perPage - ((selections.size()) % (perPage));
        for(int i = 0 ; i < extra ; i++){
            int position = getY() + padding + i * itemHeight;
            List<WidgetButton> buttons = new ArrayList<>();
            WidgetButton button = new WidgetButton(x, position, len, "", TEXT_ALIGNMENT.ALIGN_LEFT);
            button.setSkipSelection(true);
            buttons.add(button);
            mainWindow.addWidget(button);
            for(int j = 1 ; j < itemHeight ; j++) {
                button = new WidgetButton(x, position + j, len, "", TEXT_ALIGNMENT.ALIGN_LEFT);
                button.setSkipSelection(true);
                buttons.add(button);
                mainWindow.addWidget(button);
            }
            selectionsButton.add(buttons);
            addCounter += 1;
        }
        mainWindow.addWidget(prevButton);
        mainWindow.addWidget(nextButton);
    }

    /**
     * Draws the widget on the screen.
     *
     * @param buffer Buffer to draw on.
     * @param printColor Color of the text.
     * @param backColor Color of the background.
     */
    @Override
    public void draw(char[][] buffer, TextColor[][] printColor, TextColor[][] backColor) {
        if(prevButton.getPressed()){
            currentPage -= 1;
            currentPage = max(0, currentPage);
            prevButton.clearPressed();
        }
        if(nextButton.getPressed()){
            currentPage += 1;
            currentPage = min(maxPage, currentPage);
            nextButton.clearPressed();
        }
        int start = currentPage * perPage;
        int end = (currentPage + 1) * perPage;
        if(end > selectionsButton.size()){
            end = selectionsButton.size();
        }
//        System.out.println(start + " - " + end);
        for(int i = 0 ; i < selectionsButton.size() ; i++){
            List<WidgetButton> buttons = selectionsButton.get(i);
            for(WidgetButton button : buttons){
                button.setHide(true);
            }
        }
        for(int i = start; i < end ; i++){
            List<WidgetButton> buttons = selectionsButton.get(i);
            for(WidgetButton button : buttons){
                button.setHide(false);
            }
            if(buttons.get(0).selected){
                for(WidgetButton button : buttons){
                    button.select();
                }
            }
            else{
                for(WidgetButton button : buttons){
                    button.unselect();
                }
            }
            if(buttons.get(0).getPressed()){
                buttons.get(0).clearPressed();
                selectedOption = i;
            }
        }
    }
}