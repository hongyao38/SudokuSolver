package ui;

import java.util.*;

import main.*;

public class Cell extends Button {

    // Cell parameters
    public int i;
    public int j;
    public int prevNumber;
    public boolean isFixed;
    private List<Integer> candidates = new ArrayList<>();
    
    public Cell(int x, int y, int i, int j, AppPanel ap) {
        // Set cell position
        super(x, y, ap.tileSize, ap.tileSize, ap);
        this.i = i;
        this.j = j;

        // Initalise candidates
        resetCandidates();
    }

    @Override
    public void onMouseEnter() {
        enlarge();
        ap.board.hoverOverCell = this;
    }

    @Override
    public void onMouseExit() {
        shrink();
        ap.board.hoverOverCell = null;
    }

    public void onLeftClick() {
        // If cell is already fixed
        if (this.isFixed) {
            isFixed = false;
        }

        // If there is already a selected cell, reselect
        Cell previouslySelected = ap.board.selectedCell;
        if (previouslySelected != null) {
            previouslySelected.number = previouslySelected.prevNumber;
        }

        // Keep track of previously entered number
        this.prevNumber = this.number;

        // Set selected cell to this, display selection
        this.number = 10;
        ap.board.selectedCell = this;
    }

    public void onRightClick() {
        // Reset to empty cell
        number = 0;
        isFixed = false;
    }
    
    public boolean hasCandidates(boolean movedBack) {

        if (movedBack) {
            // Remove previously tried candidate
            candidates = candidates.subList(1, candidates.size());
        } else {
            // Reset to get all candidates
            resetCandidates();
        }

        // If no more candidates left after backtracking
        if (candidates.size() == 0) {
            return false;
        }

        // Get next candidate in line
        number = candidates.get(0);

        // Keep trying the next candidate till valid
        while (candidates.size() > 0 && !isValidCell()) {
            candidates = candidates.subList(1, candidates.size());
            if (candidates.size() > 0) {
                number = candidates.get(0);
            }
        }

        return candidates.size() != 0;
    }

    public Cell getNextCell() {
        int cellNo = i * 9 + j;
        // If last cell has been reached
        if (cellNo == 80) {
            return null;
        }

        int nextI = (cellNo + 1) / 9;
        int nextJ = (cellNo + 1) % 9;
        return ap.board.board[nextI][nextJ];
    }

    public void resetCandidates() {
        candidates.clear();
        for (int k = 1; k < 10; k++) {
            candidates.add(k);
        }
    }
    
    public boolean isValidCell() {
        // If empty cell, do not validate
        if (number == 0) {
            return true;
        }

        // Check row
        if (!ap.board.isValidRow(this)) {
            // System.out.println("Row already contains this number!");
            return false;
        }

        // Check column
        if (!ap.board.isValidCol(this)) {
            // System.out.println("Column already contains this number!");
            return false;
        }

        // Check zone
        if (!ap.board.isValidZone(this)) {
            // System.out.println("Zone already contains this number!");
            return false;
        }
        
        return true;
    }
}
