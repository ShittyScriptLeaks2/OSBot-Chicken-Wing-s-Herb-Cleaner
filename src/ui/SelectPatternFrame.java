/*
 * Decompiled with CFR 0_110.
 */
package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Stack;

public class SelectPatternFrame extends JFrame {

    private static final long a = 1121340224391950561L;
    private DefaultListModel b = new DefaultListModel();
    private HashMap<ImageLabel, Integer> labelToIndexMap = new HashMap<>();
    private JPanel d;
    private Stack<Integer> patternStack = new Stack<>();
    private HashMap<Integer, ImageLabel> indexToLabelMap = new HashMap<>();
    private JButton doneButton;
    private EventListenerList h = new EventListenerList();

    public SelectPatternFrame() {
        this.setDefaultCloseOperation(0);
        this.setBounds(100, 100, 210, 460);
        this.d = new JPanel();
        this.d.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.d);
        this.d.setLayout(new BorderLayout(0, 0));
        JPanel var1 = new JPanel();
        this.d.add(var1, "Center");
        var1.setLayout(new GridLayout(7, 4, 0, 0));
        JPanel var2 = new JPanel();
        this.d.add(var2, "North");
        var2.setLayout(new BorderLayout(0, 0));
        JList var6 = new JList(this.b);
        var6.setSelectionMode(0);
        var6.setLayoutOrientation(0);
        var6.setVisibleRowCount(4);
        var6.setMinimumSize(new Dimension(50, 50));

        JScrollPane scrollPane = new JScrollPane(var6);
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new ScrollPaneAdjustmentListener());
        var2.add(scrollPane);

        var2 = new JPanel(new GridLayout(2, 1));
        JPanel var3 = new JPanel();
        var2.add(var3);

        JPanel var4 = new JPanel(new FlowLayout());
        var2.add(var4);
        this.d.add(var2, "South");

        JButton undoButton = new JButton("Reset");
        var3.add(undoButton);
        undoButton.addActionListener(new ResetPatternActionListener(this));

        JButton resetButton = new JButton("Undo");
        var3.add(resetButton);
        resetButton.addActionListener(new UndoPatternActionListener(this));

        this.doneButton = new JButton("Done");
        var4.add(this.doneButton);
        this.doneButton.addActionListener(new DoneWithPatternActionListener(this));

        URL var9 = null;
        try {
            var9 = new URL("https://i.gyazo.com/f991bd51f7d991c8183da6fdaa953f62.png");
        } catch (MalformedURLException var5) {
            var5.printStackTrace();
        }
        ImageIcon var8 = new ImageIcon(var9);

        for (int i = 0; i < 28; i++) {
            ImageLabel label = new ImageLabel(var8);
            this.labelToIndexMap.put(label, i);
            this.indexToLabelMap.put(i, label);
            var1.add(label);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            label.addMouseListener(new AddToPatternMouseListener(this, label));
        }
    }

    static HashMap<Integer, ImageLabel> getIndexToLabelMap(SelectPatternFrame coN2) {
        return coN2.indexToLabelMap;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new InitializeSelectPatternFrameRunnable());
    }

    static Stack<Integer> getPatternStack(SelectPatternFrame coN2) {
        return coN2.patternStack;
    }

    static HashMap<ImageLabel, Integer> getLabelToIndexMap(SelectPatternFrame coN2) {
        return coN2.labelToIndexMap;
    }

    static DefaultListModel d(SelectPatternFrame coN2) {
        return coN2.b;
    }

    public final void a(PatternEventObject obj) {
        int n;
        Object[] arrobject = this.h.getListenerList();
        int n2 = n = 0;
        while (n2 < arrobject.length) {
            if (arrobject[n] == jjj.class) {
                ((jjj) arrobject[n + 1]).a(obj);
            }
            n2 = n += 2;
        }
    }

    private void b(jjj jjj2) {
        this.h.remove(jjj.class, jjj2);
    }

    public final void a(jjj jjj2) {
        this.h.add(jjj.class, jjj2);
    }
}

