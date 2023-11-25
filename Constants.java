package PlatfomerGame.utils;



public class Constants {
    
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int JUMP = 1;
        public static final int RUNNING = 2;
        public static final int ATTACK_GROUND = 3;
        public static final int ATTACK_AIR = 4;
        public static final int DAMAGE = 5;
        public static final int FALLING = 6;

        public static int RecieveSpriteAmnt(int player_action){

            switch(player_action){
                case RUNNING:
                    return 6;
                case JUMP:
                case IDLE:
                    return 4;
                case ATTACK_AIR:
                    return 3;
                case ATTACK_GROUND:
                    return 5;
                case DAMAGE:
                case FALLING:
                    return 1;
                default:
                    return 1;
            }

        }
    }
}
