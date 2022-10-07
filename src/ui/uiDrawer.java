package ui;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.*;

import main.*;

public class uiDrawer {

    private AppPanel ap;

    // Cursor settings
    protected int cursorImg  = 21;
    private int prevCursor = 21;
    private Cursor customCursor;

    // Hover Over Cell
    private Cell hoverOverCell;

    // UI image libary
    private BufferedImage[] imgs = new BufferedImage[30];
    private int imgCounter;
    
    public uiDrawer(AppPanel ap) {
        this.ap = ap;

        // Load cell image library
        addImage("app/res/cell/filled00.png"); // 0
        addImage("app/res/cell/filled01.png"); // 1
        addImage("app/res/cell/filled02.png"); // 2
        addImage("app/res/cell/filled03.png"); // 3
        addImage("app/res/cell/filled04.png"); // 4
        addImage("app/res/cell/filled05.png"); // 5
        addImage("app/res/cell/filled06.png"); // 6
        addImage("app/res/cell/filled07.png"); // 7
        addImage("app/res/cell/filled08.png"); // 8
        addImage("app/res/cell/filled09.png"); // 9
        addImage("app/res/cell/selected.png"); // 10
        addImage("app/res/cell/fixed01.png");  // 11
        addImage("app/res/cell/fixed02.png");  // 12
        addImage("app/res/cell/fixed03.png");  // 13
        addImage("app/res/cell/fixed04.png");  // 14
        addImage("app/res/cell/fixed05.png");  // 15
        addImage("app/res/cell/fixed06.png");  // 16
        addImage("app/res/cell/fixed07.png");  // 17
        addImage("app/res/cell/fixed08.png");  // 18
        addImage("app/res/cell/fixed09.png");  // 19

        // Add other UI images to library
        addImage("app/res/board/zoneLines.png" ); // 20
        addImage("app/res/cursor/cursor0.png"  ); // 21
        addImage("app/res/cursor/cursor1.png"  ); // 22
        addImage("app/res/cursor/cursor2.png"  ); // 23
        addImage("app/res/board/Background.png"); // 24

        // Load menu button image library
        addImage("app/res/board/solveButton.png"); // 25
        addImage("app/res/board/resetButton.png"); // 26
    }

    public void update() {
        // Update cursor only if it changes
        if (cursorImg != prevCursor) {
            prevCursor = cursorImg;
            customCursor = Toolkit.getDefaultToolkit().createCustomCursor(imgs[cursorImg], new Point(0, 0), "custom cursor");
            ap.setCursor(customCursor);
        }

        // Update hover over cell
        hoverOverCell = ap.board.hoverOverCell;
    }


    public void addImage(String imageFilePath) {
        BufferedImage newImg = null;
        try {
            newImg = ImageIO.read(new FileInputStream(imageFilePath));
        } catch (IOException e) {}

        imgs[imgCounter++] = newImg;
    }


    public void drawBoard(Graphics2D g2, SudokuBoard sudokuBoard) {
        Cell[][] board = sudokuBoard.board;

        // Draw Background
        g2.drawImage(imgs[24], 0, 0, ap.screenWidth, ap.screenHeight, null);

        // Draw each cell
        for (Cell[] row : board) {
            for (Cell c : row) {
                int cellFill = c.isFixed ? c.number + 10 : c.number;
                g2.drawImage(imgs[cellFill], c.x, c.y, c.width, c.height, null);
            }
        }
        // Draw dark zone lines
        g2.drawImage(imgs[20], ap.tileSize, ap.tileSize, 9 * ap.tileSize, 9 * ap.tileSize, null);

        // Draw selected cell above
        if (ap.board.selectedCell != null) {
            drawButton(g2, ap.board.selectedCell);
        }

        // Draw hover over cell above
        if (hoverOverCell != null) {
            Cell c = hoverOverCell;
            int imgNum = hoverOverCell.number;
            if (hoverOverCell.isFixed) imgNum += 10;
            g2.drawImage(imgs[imgNum], c.x, c.y, c.width, c.height, null);
        }
    }


    private void drawButton(Graphics2D g2, Button button) {
        int x = button.x;
        int y = button.y;
        int width = button.width;
        int height = button.height;
        int imgNum = button.number;
        g2.drawImage(imgs[imgNum], x, y, width, height, null);
    }


    public void drawMenuButtons(Graphics2D g2) {
        // Draw SOLVE button
        drawButton(g2, ap.board.solveButton);

        // Draw RESET button
        drawButton(g2, ap.board.resetButton);
    }
}
