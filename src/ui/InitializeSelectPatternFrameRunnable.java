package ui;

public final class InitializeSelectPatternFrameRunnable implements Runnable {

    @Override
    public final void run() {
        try {
            new SelectPatternFrame().setVisible(true);
            return;
        } catch (Exception v0) {
            v0.printStackTrace();
        }
    }
}

