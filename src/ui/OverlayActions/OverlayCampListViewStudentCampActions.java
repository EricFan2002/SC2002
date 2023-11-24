package ui.OverlayActions;

import entity.camp.Camp;
import entity.user.Student;
import ui.widgets.WidgetButton;
import ui.windows.ICallBack;
import ui.windows.Window;
import ui.windows.WindowOverlayClass;

import java.util.ArrayList;
import java.util.List;

public class OverlayCampListViewStudentCampActions extends WindowOverlayClass {
    WidgetButton cancelButton;
    private int chose;
    private String choseString;
    List<WidgetButton> choices;
    Window callbackWindow;
    Student student;
    Camp camp;

    public final static int optionCount = 6;

    public OverlayCampListViewStudentCampActions(int x, int offsetY, int offsetX, String windowName, Window callbackWindow, Student student, Camp camp) {
        super(3 + optionCount, x, offsetY, offsetX, windowName);

        this.student = student;
        this.camp = camp;

        List<String> options = new ArrayList<>();

        options.add("View Details");
        if((!camp.getAttendees().contains(student)) && (!camp.getCommittees().contains(student)))
            options.add("Join Camp");
        if(camp.getAttendees().contains(student))
            options.add("Quit Camp");
        if(!camp.getCommittees().contains(student))
            options.add("Enquiry");
        if(camp.getCommittees().contains(student))
            options.add("Reply Enquiry");
        if(camp.getCommittees().contains(student))
            options.add("Suggestions");
        options.add("Cancel");

        setY(options.size() + 3);

        choices = new ArrayList<>();
        this.callbackWindow = callbackWindow;
        for(int i = 0 ; i < options.size() ; i++){
            WidgetButton choice = new WidgetButton(1, 2 + i, x - 2, options.get(i));
            addWidget(choice);
            choices.add(choice);
        }
    }

    public void messageLoop() {
        for(int i = 0 ; i < choices.size(); i++){
            if(choices.get(i).getPressed()){
                System.out.println(i);
                chose = i;
                choseString = widgets.get(i).getText();
                setDestroy();
            }
        }
    }
    public void onExit(){
        super.onExit();
        if(callbackWindow instanceof ICallBack) {
            ((ICallBack)callbackWindow).onWindowFinished(chose, choseString);
        }
    }
}
