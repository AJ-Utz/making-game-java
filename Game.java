package PlatfomerGame;

import java.awt.Graphics;

import javax.swing.SwingUtilities;

import PlatfomerGame.Levels.LevelManaging;
import PlatfomerGame.entinys.Player;

public class Game implements Runnable{
    
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int gameFPS = 120;
    private final int gameUPS = 200;
    private Player player;
    private LevelManaging levelManaging;

    public final static int DEFAULT_TILE_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int NUMBER_TILES_WIDTH = 26;
    public final static int NUMBER_TILES_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * NUMBER_TILES_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * NUMBER_TILES_HEIGHT;

    Game(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        initialClasses();
        
        gamePanel.setGame(this);
        
        GameStart();
        
    }

    private void initialClasses() {
        player = new Player(200, 200);
        levelManaging = new LevelManaging(this);
    }

    private void GameStart(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update(){
        player.update();
        levelManaging.update();
    }

    public void render(Graphics g){
        
        levelManaging.draw(g);
        player.render(g);
    
    }

    @Override
    public void run() {
        
        double frameTime = 1000000000.0 / gameFPS;
        double updateTime = 1000000000.0 / gameUPS;
        
        long previousTime = System.nanoTime();


        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true){
            
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / updateTime;
            deltaF += (currentTime - previousTime) / frameTime;
            previousTime = currentTime;


            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }


            if(deltaF >= 1){
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        gamePanel.repaint();
                    }
                });
                
                deltaF--;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }
    
    }

    public Player getPlayer(){
        return player;
    }

    public void lostWindowFocus() {
        player.resetDirectionBools();
    }


}
