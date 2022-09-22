package controls;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import ui.*;

public class MouseHandler implements MouseInputListener {

    private Button button;

    public MouseHandler(Button button) {
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            button.onLeftClick();

        } else if (SwingUtilities.isRightMouseButton(e)) {
            button.onRightClick();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.onMouseEnter();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.onMouseExit();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button.showMouseClick();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button.revertCursor();
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
    
}
