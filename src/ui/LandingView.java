package ui;

import ui.windows.ICallBack;
import ui.windows.Window;

public class LandingView extends Window implements ICallBack {
    // Sliding big logo of "CAMS"
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

    @Override
    public void onWindowFinished(int chose, String choseString) {
        System.exit(0);
    }
}
