/*
 * Decompiled with CFR 0_110.
 */
package ui;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public final class ScrollPaneAdjustmentListener implements AdjustmentListener {

    @Override
    public final void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
        adjustmentEvent.getAdjustable().setValue(adjustmentEvent.getAdjustable().getMaximum());
    }

}

