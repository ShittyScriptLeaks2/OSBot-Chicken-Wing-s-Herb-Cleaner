package ui;

public final class FinishedPatternEventListener implements PatternEventListener {

    private PatternRadioButtonActionListener listener;

    public FinishedPatternEventListener(PatternRadioButtonActionListener listener) {
        this.listener = listener;
    }

    @Override
    public final void callback(PatternEventObject event) {
        MainFrame.setCleanPattern(this.listener.parent, event.getCleanPattern());
        MainFrame.a(this.listener.parent).setText("<html><font color='green'>Pattern set!</font></html>");
    }

}

