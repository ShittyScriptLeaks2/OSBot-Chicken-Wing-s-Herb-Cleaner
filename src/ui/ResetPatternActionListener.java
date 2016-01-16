/*
 * Decompiled with CFR 0_110.
 */
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

final class ResetPatternActionListener implements ActionListener {
    private SelectPatternFrame a;

    ResetPatternActionListener(SelectPatternFrame coN2) {
        this.a = coN2;
    }

    @Override
    public final void actionPerformed(ActionEvent object) {
        SelectPatternFrame.d(a).clear();
        Iterator<Integer> it = SelectPatternFrame.getPatternStack(a).iterator();
        do {
            if (!it.hasNext()) {
                SelectPatternFrame.getPatternStack(this.a).clear();
                return;
            }

            ImageLabel kk = SelectPatternFrame.getIndexToLabelMap(this.a).get(it.next());
            kk.setSelected(false);
            kk.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            SelectPatternFrame.d(this.a).clear();
        } while (true);
    }
}

