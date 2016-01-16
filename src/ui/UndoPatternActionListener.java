/*
 * Decompiled with CFR 0_110.
 */
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class UndoPatternActionListener implements ActionListener {

    private SelectPatternFrame a;

    public UndoPatternActionListener(SelectPatternFrame coN2) {
        this.a = coN2;
    }

    @Override
    public final void actionPerformed(ActionEvent serializable) {
        if (SelectPatternFrame.getPatternStack(this.a).isEmpty()) {
            return;
        }

        ImageLabel label = SelectPatternFrame.getIndexToLabelMap(this.a).get(SelectPatternFrame.getPatternStack(this.a).pop());
        label.setSelected(false);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        SelectPatternFrame.d(this.a).remove(SelectPatternFrame.d(this.a).size() - 1);
    }

}

