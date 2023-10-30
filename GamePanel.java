package PlatfomerGame;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class GamePanel extends JPanel {

    private float xDelta = 100;
    private float yDelta = 100;
    private float xDim = 1F;
    private float yDim = 1F;
    private Color color = new Color(150, 20, 60);
    private Random random;
    

    GamePanel(){
        random = new Random();
    }

    public void changeXDelta(int value){
        this.xDelta += value;
        
    }

    public void changeYDelta(int value){
        this.yDelta += value;
        
    }

    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        
    }

    

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        updateRect();
        g.setColor(color);
        g.fillRect((int) xDelta,(int) yDelta, 50, 50);      
        
    }
    
    private void updateRect(){
        xDelta += xDim;
            if(xDelta > 400 || xDelta < 0)
                xDim *= -1;
                color = getRndColor();
        yDelta += yDim;
            if(yDelta > 400 || yDelta < 0)
                yDim *= -1;
                color = getRndColor();
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }
}
