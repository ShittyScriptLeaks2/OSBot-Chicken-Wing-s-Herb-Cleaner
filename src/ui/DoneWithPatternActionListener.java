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
        int n;
        if (SelectPatternFrame.getPatternStack(this.parent).size() != 28) {
            JOptionPane.showMessageDialog(this.parent, "Pattern is not complete! Please finish the pattern", "Error", 0);
            return;
        }

        int[] pattern = new int[28];
        int n2 = n = 27;
        do {
            if (n2 < 0) {
                parent.a(new PatternEventObject(this, pattern));
                parent.dispose();
                return;
            }
            pattern[n--] = SelectPatternFrame.getPatternStack(this.parent).pop();
            n2 = n;
        } while (true);
    }

}

