/*
 * Decompiled with CFR 0_110.
 */
package ui;

import core.DankHerb;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public final class MainFrame extends JFrame {

    private DankHerb selectedHerb;
    private SelectPatternFrame b;
    private int c;
    private JLabel d;
    private int[] e;
    private boolean f;
    private int[] g;

    public MainFrame() {
        super("Settings");
        MainFrame ddd2 = this;
        MainFrame ddd3 = this;

        this.c = 0;
        this.selectedHerb = DankHerb.a;
        this.f = true;
        ddd3.e = new int[]{0, 1, 4, 5, 8, 9, 12, 13, 16, 17, 20, 21, 24, 25, 2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 22, 23, 26, 27};
        ddd3.g = this.e;
        ddd3.d = new JLabel("No custom pattern set");
        ddd2.setLocationRelativeTo(null);
        ddd2.setDefaultCloseOperation(2);
        ddd2.setLayout(new BorderLayout());
    }

    static JLabel a(MainFrame frame) {
        return frame.d;
    }

    static int[] a(MainFrame frame, int[] arrn) {
        frame.g = arrn;
        return frame.g;
    }

    static boolean a(MainFrame frame, boolean bl) {
        frame.f = bl;
        return frame.f;
    }

    static SelectPatternFrame b(MainFrame frame) {
        return frame.b;
    }

    static SelectPatternFrame a(MainFrame ddd2, SelectPatternFrame coN2) {
        ddd2.b = coN2;
        return ddd2.b;
    }

    static int setCleanDelay(MainFrame parent, int n) {
        parent.c = n;
        return parent.c;
    }

    static int[] c(MainFrame ddd2) {
        return ddd2.e;
    }

    static DankHerb setSelectedHerb(MainFrame frame, DankHerb herb) {
        frame.selectedHerb = herb;
        return frame.selectedHerb;
    }

    private boolean d() {
        return this.f;
    }

    private void asde() {
        this.dispose();
    }

    public final void init() {
        JPanel var2 = new JPanel(new FlowLayout());
        JPanel var3 = new JPanel(new FlowLayout());
        JPanel var4 = new JPanel(new GridLayout(4, 1));

        var3.add(new JLabel("Herb to clean: "));
        var4.add(new JLabel("<html><font color=\'red\'>Make sure ESC key is enabled for faster xp!</font></html>"));
        var4.add(this.d);

        JComboBox<DankHerb> herbComboBox = new JComboBox<>(DankHerb.values());
        herbComboBox.setSelectedIndex(0);
        herbComboBox.addActionListener(new SelectHerbActionListener(this));
        var3.add(herbComboBox);

        JButton startButton = new JButton("Start");
        var2.add(startButton);
        startButton.addActionListener((var2x) -> this.dispose());

        JSlider speedSlider = new JSlider(0, 0, 100, 0);
        speedSlider.addChangeListener(new SpeedSliderChangeListener(this));
        speedSlider.setMajorTickSpacing(10);
        speedSlider.setPaintTicks(true);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("No delay"));
        labels.put(100, new JLabel("High delay"));
        speedSlider.setLabelTable(labels);
        speedSlider.setPaintLabels(true);
        var4.add(speedSlider);

        JPanel var9 = new JPanel(new GridLayout(1, 2));
        var4.add(var9);

        JRadioButton zigZagPatternButton = new JRadioButton("ZigZag Pattern");
        zigZagPatternButton.setActionCommand("ZigZag Pattern");
        zigZagPatternButton.setSelected(true);

        JRadioButton customPatternButton = new JRadioButton("Custom");
        customPatternButton.setActionCommand("Custom");

        ButtonGroup patternButtonGroup = new ButtonGroup();
        patternButtonGroup.add(zigZagPatternButton);
        patternButtonGroup.add(customPatternButton);

        PatternRadioButtonActionListener listener = new PatternRadioButtonActionListener(this);
        zigZagPatternButton.addActionListener(listener);
        customPatternButton.addActionListener(listener);

        var9.add(zigZagPatternButton);
        var9.add(customPatternButton);

        this.add(var2, "South");
        this.add(var3, "North");
        this.add(var4, "Center");
        this.pack();
    }

    public final int[] a() {
        return this.g;
    }

    public final int b() {
        return this.c;
    }

    public final DankHerb getSelectedHerb() {
        return this.selectedHerb;
    }

}

