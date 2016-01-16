/*
 * Decompiled with CFR 0_110.
 */
package ui;

import javax.swing.*;

public final class ImageLabel extends JLabel {

    private boolean selected;

    public ImageLabel(ImageIcon imageIcon) {
        super(imageIcon);
        this.selected = false;
    }

    public final boolean isSelected() {
        return this.selected;
    }

    public final void setSelected(boolean bl) {
        this.selected = bl;
    }


}

