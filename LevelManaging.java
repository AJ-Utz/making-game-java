package PlatfomerGame.Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import PlatfomerGame.Game;
import PlatfomerGame.utils.SaveandLoad;

public class LevelManaging {
    
    private Game game;
    private BufferedImage[] lvSprite;
    private Level1 levelone;

    public LevelManaging(Game game){
        this.game = game;
        importGroundSprites();
        levelone = new Level1(SaveandLoad.RecieveLevelData());
    }

    private void importGroundSprites() {
        BufferedImage img = SaveandLoad.GetSpriteAtlas(SaveandLoad.LEVEL_ATLAS);
        
        lvSprite = new BufferedImage[10];
        for(int y = 0; y < 2; y++){
            for(int x = 0; x < 5; x++){
                int index = y * 5 + x;
                lvSprite[index] = img.getSubimage(x * 32,y * 32, 32, 32);
            }
        }
    }



    public void draw(Graphics g){

        for(int y = 0; y < Game.NUMBER_TILES_HEIGHT; y++){
            for(int x = 0; x < Game.NUMBER_TILES_WIDTH; x++){
                int index = levelone.getspriteidx(x, y);
                g.drawImage(lvSprite[index], Game.TILES_SIZE * x, Game.TILES_SIZE * y, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
        
    }

    public void update(){

    }
}

