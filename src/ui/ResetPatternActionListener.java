package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ResetPatternActionListener implements ActionListener {

    private final SelectPatternFrame parent;

    public ResetPatternActionListener(SelectPatternFrame parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent object) {
        SelectPatternFrame.d(parent).clear();
        for (Integer i : SelectPatternFrame.getPatternStack(parent)) {
            ImageLabel label = SelectPatternFrame.getIndexToLabelMap(this.parent).get(i);
            label.setSelected(false);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            SelectPatternFrame.d(this.parent).clear();
        }

        SelectPatternFrame.getPatternStack(this.parent).clear();
    }
}

