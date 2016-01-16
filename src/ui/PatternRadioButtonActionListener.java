/*
 * Decompiled with CFR 0_110.
 */
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
            MainFrame.a(parent, MainFrame.c(this.parent));
            if (MainFrame.b(parent) == null) return;
            MainFrame.b(parent).dispose();
            MainFrame.a(parent, (int[]) null);
            return;
        }

        MainFrame.a(parent, false);
        if (MainFrame.b(parent) == null) {
            MainFrame.a(this.parent, new SelectPatternFrame());
            MainFrame.b(parent).setVisible(true);
            MainFrame.b(parent).a(new mmm(this));
        }

        parent.repaint();
        parent.revalidate();
    }

}

