package main;

import java.awt.*;
import javax.swing.*;

import ui.*;
import controls.*;

public class AppPanel extends JPanel implements Runnable {

    // Tile Settings
    public final int scale = 4;
    public final int tileSize = 16 * scale; // 64px

    // Window Size Settings
    public final int maxScreenCol = 14;
    public final int maxScreenRow = 11;
    public final int screenWidth  = maxScreenCol * tileSize;  // 896px
    public final int screenHeight = maxScreenRow * tileSize;  // 704px

    // App parameters
    private Thread thread;
    private KeyHandler  keyH  = new KeyHandler(this);
    public  uiDrawer    ui    = new uiDrawer(this);
    public  SudokuBoard board = new SudokuBoard(this);
    
    public AppPanel() {

        // Screen settings
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setLayout(null);

        // Key Listener
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (thread != null) {
            update();
            repaint();
        }
    }

    public void update() {
        ui.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // Draw the board
        ui.drawBoard(g2, board);

        // Draw menu buttons
        ui.drawMenuButtons(g2);
    }
}
