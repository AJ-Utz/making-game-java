package PlatfomerGame;

public class Game implements Runnable{
    
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int gameFPS = 120;

    Game(){
        gamePanel = new GamePanel();
        new Gameframe(); 
        GameStart();
    }

    private void GameStart(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
        double frameTime = 1000000000.0 / gameFPS;
        long lastFrame = System.nanoTime();
        long current = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while(true){

            current = System.nanoTime();
            if(current - lastFrame >= frameTime){
                
                gamePanel.repaint();
                lastFrame = current;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    
    }


}
