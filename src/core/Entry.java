package core;

import ui.MainFrame;

public final class Entry {

    private Entry() {
    }

    public static void main(String[] object) {
        MainFrame frame = new MainFrame();
        frame.init();
        frame.setVisible(true);
    }

}

