package ui;

import ui.windows.Window;

public class LandingView extends Window {
    public LandingView() {
        super(1,2, "");
    }

    @Override
    public void messageLoop() {
        System.exit(0);
    }

    public void onExit(){
        System.exit(0);
    }
}
