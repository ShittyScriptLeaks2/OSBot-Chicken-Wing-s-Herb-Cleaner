package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class AddToPatternMouseListener implements MouseListener {

    private SelectPatternFrame parent;
    private ImageLabel label;

    public AddToPatternMouseListener(SelectPatternFrame parent, ImageLabel label) {
        this.parent = parent;
        this.label = label;
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
        if (this.label.isSelected()) {
            return;
        }

        label.setBorder(BorderFactory.createLineBorder(Color.RED));
        label.setSelected(true);
        SelectPatternFrame.d(parent).addElement(SelectPatternFrame.getLabelToIndexMap(this.parent).get(this.label));
        SelectPatternFrame.getPatternStack(parent).add(SelectPatternFrame.getLabelToIndexMap(this.parent).get(this.label));
        System.out.println(SelectPatternFrame.getLabelToIndexMap(this.parent).get(this.label));
    }

    @Override
    public final void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
    }
}

