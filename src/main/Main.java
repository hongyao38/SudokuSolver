package main;

import javax.swing.JFrame;
import java.awt.*;

public class Main {
    
    public static void main(String[] args) {

        // Create JFrame Parameters
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Sudoku Solver");
        Image icon = Toolkit.getDefaultToolkit().getImage("app/res/board/Icon.png");
        window.setIconImage(icon);

        // Create a Panel
        AppPanel appPanel = new AppPanel();
        window.add(appPanel);
        window.pack();

        // Set window position on screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Start app thread
        appPanel.startThread();
    }
}