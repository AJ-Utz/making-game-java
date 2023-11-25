package PlatfomerGame.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import PlatfomerGame.Game;

public class SaveandLoad {
    
    public static final String PLAYER_ATLAS = "res/Running_guy.png";
    public static final String LEVEL_ATLAS = "res/Platform-1.png";
    public static final String LEVEL_ONE = "res/Level-1-example.png";

    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = SaveandLoad.class.getResourceAsStream("/" + fileName);
    
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    public static int[][] RecieveLevelData(){
        int[][] lvlData = new int[Game.NUMBER_TILES_HEIGHT][Game.NUMBER_TILES_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE);

        for(int y = 0; y < Game.NUMBER_TILES_HEIGHT && y < img.getHeight(); y++){
            for(int x = 0; x < Game.NUMBER_TILES_WIDTH && x < img.getWidth(); x++){
                Color color = new Color(img.getRGB(x, y));
                int value = color.getRed();
                if(value >= 10){
                    value = 0;
                }
                lvlData[y][x] = value;
            }
        }
        return lvlData;
    }
}

