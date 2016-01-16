package core;

import org.osbot.rs07.input.mouse.BotMouseListener;

import java.awt.*;
import java.awt.event.MouseEvent;

public final class RetardMouseListener implements BotMouseListener {

    private final Core parent;

    public RetardMouseListener(Core parent) {
        this.parent = parent;
    }

    public boolean blockInput(Point point) {
        return false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.parent.getBot().addPainter(graphics2D -> {
        });
    }

}

