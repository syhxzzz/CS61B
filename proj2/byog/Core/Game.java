package byog.Core;
import java.awt.Color;
import java.util.Deque;
import java.util.Random;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 81;
    public static final int HEIGHT = 31;
    long seed=0;
    String input;
    TETile[][] world;
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {//套用Main
        drawUI();
    }


    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    public void drawUI(){
        drawInitializeInterface();
        StdDraw.text(WIDTH / 2, 3 * HEIGHT / 4, "CS61B: THE GAME");
        StdDraw.text(WIDTH / 2, HEIGHT / 4 + 2, "New Game (N)");
        StdDraw.text(WIDTH / 2, HEIGHT / 4, "Load Game (L)");
        StdDraw.text(WIDTH / 2, HEIGHT / 4 - 2, "Quit (Q)");
        StdDraw.show();
        char c = getFirstChar();
        switch(c){
            case 'n':
                newGame();
                break;
            case 'l':
                loadGame();
                break;
            case 'q':
                System.exit(0);
                break;
            default:
        }
    }

    public void drawInitializeInterface(){
        StdDraw.setCanvasSize(WIDTH * 16, (HEIGHT + 1) * 16);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT + 1);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.WHITE);
    }
    public void newGame(){
        seed = getSeed();
        TETile[][] world = createNewWorld(seed);
        ter.renderFrame(world);
        play();
    }
    public void loadGame(){

    }
    private long getSeed(){
        seed = 0;
        char c;
        String str="";
        StdDraw.clear(Color.BLACK);
        StdDraw.text(WIDTH / 2, 3 * HEIGHT / 4, "Input a number as a seed");
        StdDraw.text(WIDTH / 2, 3 * HEIGHT / 4-2, "Press S to Stop");
        StdDraw.show();
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            c = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (c == 's') {
                seed = Long.parseLong(str);
                break;
            }
            str+=c;
        }
        return seed;
    }
    private char getFirstChar() {
        char c;
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            c = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (c == 'n' || c == 'l' || c == 'q') {
                break;
            }
        }
        return c;
    }
    private TETile[][] createNewWorld(long seed){
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        createBoringWorld(world);
        createPath(world,seed);        
        ter.initialize(WIDTH, HEIGHT);
        return world;
    }
    private void play(){

    }
    private void createBoringWorld(TETile[][] world){
        for (int i=0;i<=world.length-1;i++)
            for (int j=0;j<=world[0].length-1;j++)
                world[i][j]= Tileset.WALL;

        for (int i=1;i<=world.length-2;i+=2)
            for (int j=1;j<=world[0].length-2;j+=2)
                world[i][j]=Tileset.NOTHING;
    }
    private void createPath(TETile[][] world,long seed){
        Random random = new Random(seed);
        LinkedListDeque <position> positionList = new LinkedListDeque<position>();
        position firstPosition = findFirstPosition(world, seed);
        positionList.addFirst(firstPosition);
        goThroughAllThePosition(positionList, firstPosition, world, seed);
        while(!positionList.isEmpty()){
            int index = random.nextInt(positionList.size());
            position pos = positionList.get(index);
            goThroughAllThePosition(positionList, pos, world, seed);
            positionList.remove(index);
        }
    }
    private position findFirstPosition(TETile[][] world, long seed){
        Random random = new Random(seed);
        int x,y;
        x=random.nextInt(WIDTH-2);
        y=random.nextInt(HEIGHT-2);
        x = (x % 2 == 1 ? x : x + 1);
        y = (y % 2 == 1 ? y : y + 1);
        return new position(x,y);
    }
    private void goThroughAllThePosition(LinkedListDeque<position> positionList,position start,TETile[][] world,long seed){

    }
}
