package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class PatternRadioButtonActionListener implements ActionListener {

    public final MainFrame parent;

    public PatternRadioButtonActionListener(MainFrame parent) {
        this.parent = parent;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("ZigZag Pattern")) {
            MainFrame.a(this.parent, true);
            parent.repaint();
            parent.revalidate();
            MainFrame.a(parent).setText("No custom pattern set");
            MainFrame.setCleanPattern(parent, MainFrame.getDefaultCleanPattern(this.parent));
            if (parent.getSelectPatternFrame() == null) {
                return;
            }

            parent.getSelectPatternFrame().dispose();
            MainFrame.setCleanPattern(parent, null);
            return;
        }

        MainFrame.a(parent, false);
        if (parent.getSelectPatternFrame() == null) {
            parent.setSelectPatternFrame(new SelectPatternFrame());
            parent.getSelectPatternFrame().setVisible(true);
            parent.getSelectPatternFrame().addEventListener(new FinishedPatternEventListener(this));
        }

        parent.repaint();
        parent.revalidate();
    }

}

