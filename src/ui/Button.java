package ui;

import javax.swing.JComponent;

import main.*;
import controls.*;

public abstract class Button extends JComponent {

    protected AppPanel ap;
    protected MouseHandler mouse;

    // Button position and dimension
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    // Button states
    public int number;
    
    public Button(int x, int y, int width, int height, AppPanel ap) {
        this.ap = ap;

        // Set button position and bounds
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setBounds(x, y, width, height);

        // Create and implement mouse listener
        mouse = new MouseHandler(this);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public abstract void onLeftClick();

    public abstract void onRightClick();

    public void showMouseClick() {
        ap.ui.cursorImg = 3 + 20;
    }

    public void revertCursor() {
        ap.ui.cursorImg = 2 + 20;
    }

    public void onMouseEnter() {
        // Enlarge the cell
        x -= 4;
        y -= 4;
        width += 8;
        height += 8;

        // Change cursor
        ap.ui.cursorImg = 2 + 20;
    };

    public void onMouseExit() {
        // Return to original size
        x += 4;
        y += 4;
        width -= 8;
        height -= 8;

        // Change cursor
        ap.ui.cursorImg = 1 + 20;
    };
}
