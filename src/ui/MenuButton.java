package ui;

import javax.swing.SwingWorker;

import main.*;

public class MenuButton extends Button {

    private SwingWorker<String, String> worker;

    public MenuButton(int x, int y, int width, int height, AppPanel ap) {
        super(x, y, width, height, ap);
    }

    @Override
    public void onLeftClick() {

        // For Reset Button
        if (this.equals(ap.board.resetButton)) {
            ap.board.reset();
        }

        // For Solve Button
        else if (this.equals(ap.board.solveButton)) {
            // Exit out of any selected cell
            ap.board.selectedCell = null;

            // Instantiate SwingWorker to start concurrent thread
            worker = new SwingWorker<String,String>() {

                @Override
                protected String doInBackground() throws Exception {
                    ap.board.solve();
                    return null;
                }
                
            };
            worker.execute();
        }
        
    }

    @Override
    public void onRightClick() {}
}
