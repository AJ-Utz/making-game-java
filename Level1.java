package PlatfomerGame.Levels;

public class Level1 {
    private int[][] lvlData;

    public Level1(int[][] lvlData){
        this.lvlData = lvlData;
    }

    public int getspriteidx(int x, int y){
        return lvlData[y][x];
    }

}
