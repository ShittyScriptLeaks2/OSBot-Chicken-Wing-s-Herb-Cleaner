package ui;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public final class ScrollPaneAdjustmentListener implements AdjustmentListener {

    @Override
    public void adjustmentValueChanged(AdjustmentEvent event) {
        event.getAdjustable().setValue(event.getAdjustable().getMaximum());
    }

}

