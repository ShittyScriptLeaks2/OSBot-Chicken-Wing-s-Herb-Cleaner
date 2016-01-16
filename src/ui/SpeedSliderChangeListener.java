package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class SpeedSliderChangeListener implements ChangeListener {

    private final MainFrame frame;

    public SpeedSliderChangeListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public final void stateChanged(ChangeEvent serializable) {
        JSlider slider = (JSlider) serializable.getSource();
        if (slider.getValueIsAdjusting()) {
            return;
        }

        MainFrame.setCleanDelay(this.frame, slider.getValue());
    }

}

