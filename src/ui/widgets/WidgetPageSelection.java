package ui.widgets;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ui.windows.Window;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class WidgetPageSelection extends Widget {
    private int selected = -1;
    List<WidgetButton> selectionsButton = new ArrayList<>();
    WidgetButton nextButton;
    WidgetButton prevButton;
    private int height;
    private Window mainWindow;
    private int currentPage = 0;
    private int maxPage = 0;
    private int perPage = 0;
    public WidgetPageSelection(int x, int y, int len, int height, String text, List<String> selections, Window mainWindow) {
        super(x, y, len, text);
        selected = -1;
        this.textAlignment = TEXT_ALIGNMENT.ALIGN_MID;
        this.height = height;
        this.mainWindow = mainWindow;
        maxPage = (int)Math.ceil((double)selections.size() / (height - 1));
        perPage = height - 1;
        addSelections(selections);
    }

    public void addSelections(List<String> selections){
        for(int i = 0 ; i < selections.size() ; i++){
            int position = getY() + ((i) % (perPage));
            WidgetButton button = new WidgetButton(x, position, len, selections.get(i), TEXT_ALIGNMENT.ALIGN_LEFT);
            selectionsButton.add(button);
            mainWindow.addWidget(button);
        }
        int padding = (selections.size()) % (perPage);
        int extra = perPage - ((selections.size()) % (perPage));
        for(int i = 0 ; i < extra ; i++){
            int position = getY() + padding + i;
            WidgetButton button = new WidgetButton(x, position, len, "", TEXT_ALIGNMENT.ALIGN_LEFT);
            selectionsButton.add(button);
            mainWindow.addWidget(button);
        }
        prevButton = new WidgetButton(x, getY() + height - 1, len / 2, "Prev");
        nextButton = new WidgetButton((x + getLen() / 2), getY() + height - 1, len / 2, "Next");
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
            selectionsButton.get(i).setHide(true);
        }
        for(int i = start; i < end ; i++){
            selectionsButton.get(i).setHide(false);
        }
    }
}