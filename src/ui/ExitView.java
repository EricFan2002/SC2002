package ui;

import ui.windows.Window;

public class ExitView extends Window {
    public ExitView() {
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
