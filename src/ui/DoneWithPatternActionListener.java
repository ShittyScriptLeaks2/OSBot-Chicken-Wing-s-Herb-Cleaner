package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class DoneWithPatternActionListener implements ActionListener {

    private final SelectPatternFrame parent;

    public DoneWithPatternActionListener(SelectPatternFrame parent) {
        this.parent = parent;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        if (SelectPatternFrame.getPatternStack(this.parent).size() != 28) {
            JOptionPane.showMessageDialog(this.parent, "Pattern is not complete! Please finish the pattern", "Error", 0);
            return;
        }

        int[] pattern = new int[28];
        for (int i = 27; i >= 0; i--) {
            pattern[i] = SelectPatternFrame.getPatternStack(this.parent).pop();
        }

        parent.fireEvent(new PatternEventObject(this, pattern));
        parent.dispose();
    }

}

