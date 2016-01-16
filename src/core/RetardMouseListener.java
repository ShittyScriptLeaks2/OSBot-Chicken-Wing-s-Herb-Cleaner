package core;

import org.osbot.rs07.input.mouse.BotMouseListener;

import java.awt.*;
import java.awt.event.MouseEvent;

public final class RetardMouseListener implements BotMouseListener {

    private final Core parent;

    public RetardMouseListener(Core parent) {
        this.parent = parent;
    }

    public final boolean blockInput(Point point) {
        return false;
    }

    public final void mouseEntered(MouseEvent mouseEvent) {
    }

    public final void mousePressed(MouseEvent mouseEvent) {
    }

    public final void mouseReleased(MouseEvent mouseEvent) {
    }

    public final void mouseExited(MouseEvent mouseEvent) {
    }

    public final void mouseClicked(MouseEvent mouseEvent) {
        this.parent.getBot().addPainter(graphics2D -> {

        });
    }

}

