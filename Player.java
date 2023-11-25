package PlatfomerGame.entinys;

import static PlatfomerGame.utils.Constants.Directions.DOWN;
import static PlatfomerGame.utils.Constants.Directions.LEFT;
import static PlatfomerGame.utils.Constants.Directions.RIGHT;
import static PlatfomerGame.utils.Constants.Directions.UP;
import static PlatfomerGame.utils.Constants.PlayerConstants.ATTACK_GROUND;
import static PlatfomerGame.utils.Constants.PlayerConstants.IDLE;
import static PlatfomerGame.utils.Constants.PlayerConstants.RUNNING;
import static PlatfomerGame.utils.Constants.PlayerConstants.RecieveSpriteAmnt;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import PlatfomerGame.utils.SaveandLoad;
import PlatfomerGame.utils.Constants.PlayerConstants;

public class Player extends entity{

    private BufferedImage[][] animations;
    private int aniTic;
    private int aniSpeed = 15;
    private int aniIndex;
    private int player_action = PlayerConstants.IDLE;
    private int player_direction = -1;
    private boolean isMoving = false; 
    private boolean isAttackingSide = false;
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private float player_speed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnim();
    }

    public void update(){
        positionUpdate();
        updateAni();
        setAni();
        
    }

    public void render(Graphics g){
        g.drawImage(animations[player_action][aniIndex],(int)x, (int)y, 64, 64, null);
        
    }


    private void updateAni(){
      aniTic++;
        if(aniTic >= aniSpeed){
            aniTic = 0;
            aniIndex++;
            if(aniIndex >= PlayerConstants.RecieveSpriteAmnt(player_action)){
                aniIndex = 0;
                isAttackingSide = false;
            }
        }
    }

    private void setAni(){

        int animationStart = player_action;


        if(isMoving){
            player_action = PlayerConstants.RUNNING;
        } else {
            player_action = PlayerConstants.IDLE;
        }

        if(isAttackingSide){
            player_action = PlayerConstants.ATTACK_GROUND;
        }

        if(animationStart != player_action){
            resetAniTic();
        }

    }

    private void resetAniTic(){
        aniTic = 0;
        aniIndex = 0;
    }


    private void positionUpdate() {
        
        isMoving = false;

        if(left && !right){
            x -= player_speed;
            isMoving = true;
        } else if(right && !left) {
            x += player_speed;
            isMoving = true;
        }

        if(up && !down){
            y -= player_speed;
            isMoving = true;
        } else if (down && !up){
            y += player_speed;
            isMoving = true;
        }
    }

    private void loadAnim(){

            BufferedImage img = SaveandLoad.GetSpriteAtlas(SaveandLoad.PLAYER_ATLAS);

            animations = new BufferedImage[8][6];

            for(int j = 0; j < animations.length; j++){
                for(int i = 0; i < animations[j].length; i++){
                    animations[j][i] = img.getSubimage( i * 32, j * 32, 32,32);
                }
            }
        
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirectionBools() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void sideAttack(boolean isAttackingSide){
        this.isAttackingSide = isAttackingSide;
    }

    

}
