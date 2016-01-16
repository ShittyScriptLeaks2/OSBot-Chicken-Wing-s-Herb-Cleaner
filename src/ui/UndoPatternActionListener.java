package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class UndoPatternActionListener implements ActionListener {

    private final SelectPatternFrame parent;

    public UndoPatternActionListener(SelectPatternFrame parent) {
        this.parent = parent;
    }

    @Override
    public final void actionPerformed(ActionEvent serializable) {
        if (SelectPatternFrame.getPatternStack(this.parent).isEmpty()) {
            return;
        }

        ImageLabel label = SelectPatternFrame.getIndexToLabelMap(this.parent).get(SelectPatternFrame.getPatternStack(this.parent).pop());
        label.setSelected(false);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        SelectPatternFrame.d(this.parent).remove(SelectPatternFrame.d(this.parent).size() - 1);
    }

}

