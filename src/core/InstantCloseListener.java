package core;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class InstantCloseListener extends WindowAdapter {

    private final Core parent;

    public InstantCloseListener(Core parent) {
        this.parent = parent;
    }

    @Override
    public final void windowClosing(WindowEvent windowEvent) {
        Core.getMainFrame(this.parent).setVisible(false);
    }

}

