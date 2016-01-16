package ui;

import core.DankHerb;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SelectHerbActionListener implements ActionListener {

    private final MainFrame parent;

    public SelectHerbActionListener(MainFrame parent) {
        this.parent = parent;
    }

    @Override
    public final void actionPerformed(ActionEvent serializable) {
        JComboBox box = (JComboBox) serializable.getSource();
        MainFrame.setSelectedHerb(this.parent, (DankHerb) box.getSelectedItem());
    }

}

