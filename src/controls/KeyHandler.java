package controls;

import java.awt.event.*;

import ui.*;
import main.*;

public class KeyHandler implements KeyListener {

    private AppPanel ap;

    public KeyHandler(AppPanel ap) {
        this.ap = ap;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Cell cell = ap.board.selectedCell;

        // If no cell selected, key input is not required
        if (cell == null) {
            return;
        }

        // If selected cell is preset, set number according to key press
        int code = e.getKeyCode();

        if      (code == KeyEvent.VK_1 || code == KeyEvent.VK_NUMPAD1) {cell.number = 1;}
        else if (code == KeyEvent.VK_2 || code == KeyEvent.VK_NUMPAD2) {cell.number = 2;}
        else if (code == KeyEvent.VK_3 || code == KeyEvent.VK_NUMPAD3) {cell.number = 3;}
        else if (code == KeyEvent.VK_4 || code == KeyEvent.VK_NUMPAD4) {cell.number = 4;}
        else if (code == KeyEvent.VK_5 || code == KeyEvent.VK_NUMPAD5) {cell.number = 5;}
        else if (code == KeyEvent.VK_6 || code == KeyEvent.VK_NUMPAD6) {cell.number = 6;}
        else if (code == KeyEvent.VK_7 || code == KeyEvent.VK_NUMPAD7) {cell.number = 7;}
        else if (code == KeyEvent.VK_8 || code == KeyEvent.VK_NUMPAD8) {cell.number = 8;}
        else if (code == KeyEvent.VK_9 || code == KeyEvent.VK_NUMPAD9) {cell.number = 9;}

        // If user presses SPACE, move to next cell
        else if (code == KeyEvent.VK_SPACE) {
            cell.number = 0;
            ap.board.selectedCell = cell.getNextCell();
            ap.board.selectedCell.number = 10;
        }

        // Any other key press will exit selection
        else {
            cell.number = cell.prevNumber;
            ap.board.selectedCell = null;
        }

        // If invalid cell, reset cell to empty, deselect cell
        if (!cell.isValidCell()) {
            cell.number = 0;
            cell.isFixed = false;
            ap.board.selectedCell = null;
            return;
        }

        // If cell has been assigned a number, fix the cell
        if (cell.number != 0) {
            cell.isFixed = true;

            // Move selection to next cell
            ap.board.selectedCell = cell.getNextCell();
            if (ap.board.selectedCell != null) ap.board.selectedCell.number = 10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
