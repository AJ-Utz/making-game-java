package PlatfomerGame;

import java.awt.Dimension;

import javax.swing.*;

import PlatfomerGame.Controls.KeyBoardInputs;
import PlatfomerGame.Controls.MouseInputs;

public class Gameframe extends JFrame{
    
    
    Gameframe(){
        GamePanel gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(400, 400));
        this.setTitle("Platforming Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        KeyBoardInputs keyboardListener = new KeyBoardInputs(gamePanel);
        this.addKeyListener(keyboardListener);
        
        MouseInputs mouseListener = new MouseInputs(gamePanel);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        this.setResizable(true);
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
