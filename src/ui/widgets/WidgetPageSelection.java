package ui.widgets;

import com.googlecode.lanterna.TextColor;
import ui.windows.Window;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class WidgetPageSelection extends Widget {
    private int selected = -1;
    List<List<WidgetButton>> selectionsButton = new ArrayList<>();
    WidgetButton nextButton;
    WidgetButton prevButton;
    private int height;
    private Window mainWindow;
    private int currentPage = 0;
    private int maxPage = 0;
    private int perPage = 0;
    private int itemHeight = 0;
    public WidgetPageSelection(int x, int y, int len, int height, String text, ArrayList<ArrayList<String>> selections, Window mainWindow) {
        super(x, y, len, text);
        selected = -1;
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
        addSelections(selections);
    }

    public void updateList(ArrayList<ArrayList<String>> selections){
        selected = -1;
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_MID;
        this.height = height;
        this.mainWindow = mainWindow;
        if(selections.size() >= 0) {
            itemHeight = selections.get(0).size();
        }
        else{
            itemHeight = 1;
        }
        maxPage = (int)Math.ceil((double)selections.size() / (height - 1) / itemHeight);
        perPage = (height - 1) / itemHeight;
        addSelections(selections);
        currentPage = 0;
    }

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
        }
        prevButton = new WidgetButton(x, getY() + height - 1, len / 2, "Prev");
        int second = len / 2;
        if(len % 2 == 1)
            second++;
        nextButton = new WidgetButton((x + getLen() / 2), getY() + height - 1, second, "Next");
        mainWindow.addWidget(prevButton);
        mainWindow.addWidget(nextButton);
    }

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
        }
    }
}