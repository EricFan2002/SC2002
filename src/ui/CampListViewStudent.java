package ui;

import ui.widgets.WidgetButton;
import ui.widgets.WidgetToggle;

public class CampListViewStudent extends CampListView{
    protected WidgetToggle toggleJoinedCamp = new WidgetToggle(1, 7, getLenX() / 4 - 1, "Joined Camps");
    protected WidgetToggle toggleCommittee = new WidgetToggle(getLenX() / 4 + 1, 7, getLenX() / 4 - 1, "As Committee");
    protected WidgetToggle toggleAvailable = new WidgetToggle(1, 8, getLenX() / 4 - 1, "Available As Participant");
    protected WidgetToggle toggleCommitteeAvailableC = new WidgetToggle(getLenX() / 4 + 1, 8, getLenX() / 4 - 1, "Available As Committee");
    public CampListViewStudent(int loginSwitchToWindowIndex, int changePasswordWindowIndex, int forgotPasswordWindowIndex) {
        super(loginSwitchToWindowIndex, changePasswordWindowIndex, forgotPasswordWindowIndex);
        addWidgetAfter(toggleCommitteeAvailableC, filter4Index);
        addWidgetAfter(toggleAvailable, filter4Index);
        addWidgetAfter(toggleCommittee, filter4Index);
        addWidgetAfter(toggleJoinedCamp, filter4Index);
    }
}
