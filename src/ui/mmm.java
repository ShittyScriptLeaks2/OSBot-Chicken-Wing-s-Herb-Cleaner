/*
 * Decompiled with CFR 0_110.
 */
package ui;

final class mmm implements jjj {
    private PatternRadioButtonActionListener a;

    mmm(PatternRadioButtonActionListener lll2) {
        this.a = lll2;
    }

    @Override
    public final void a(PatternEventObject ggg2) {
        MainFrame.a(this.a.parent, ggg2.a());
        MainFrame.a(this.a.parent).setText("<html><font color='green'>Pattern set!</font></html>");
    }
}

