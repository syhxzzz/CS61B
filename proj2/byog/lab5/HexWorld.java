package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
    public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 60;  

    public static void addHexagon(TETile[][] world,position pos,int length,TETile z){ ////此处的x和y为六边形最左上角那个
    printUpperTrapezoidal(world,pos.setXscale(),pos.setYscale(),length,z);
    printLowerTrapezoidal(world,pos.setXscale(),pos.setYscale()-length*2+1,length,z);
    }   

    public static void printUpperTrapezoidal(TETile[][] world,int x,int y,int length,TETile z){  //此处的x和y为六边形最左上角那个
        for(int i=0;i<length;i+=1){
            for(int j=-i;j<length+i;j+=1){
                world[x+j][y-i] = z;
            }
        }
    }
    public static void printLowerTrapezoidal(TETile[][] world,int x,int y,int length,TETile z){  //此处的x和y为六边形最左下角那个
        for(int i=0;i<length;i+=1){
            for(int j=-i;j<length+i;j+=1){
                world[x+j][y+i] = z;
            }
        }
    }
    public static position calRightUpperHexagon(position pos,int length){
        position posToRet = new position(pos.setXscale()+2*length-1, pos.setYscale()+length);
        return posToRet;
    }
    public static position calRightLowerHexagon(position pos,int length){
        position posToRet = new position(pos.setXscale()+2*length-1, pos.setYscale()-length);
        return posToRet;
    }
    public static position calLowerHexagon(position pos,int length){
        position posToRet = new position(pos.setXscale(), pos.setYscale()-2*length);
        return posToRet;
    }
    public static void printColumnOfHexagon(TETile[][] world,position pos,int height,int length){
        for(int i=0;i<height;i+=1){
            addHexagon(world, pos, length, RandomWorldDemo.randomTile());
            pos = calLowerHexagon(pos, length);
        }
    }
    public static void printHexagonHexagon(TETile[][] world,position pos,int x,int y,int length){
        for(int i=0;i<x;i+=1){
            printColumnOfHexagon(world, pos, y+i,length);
            pos = calRightUpperHexagon(pos,length);
        }
        for(int i=x;i>=0;i-=1){
            printColumnOfHexagon(world, pos, y+i,length);
            pos = calRightLowerHexagon(pos,length);
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles     为窗口中添加空白
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        //TETile z = Tileset.WATER;
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        position FirstPoint = new position(2, 40);
        printHexagonHexagon(world, FirstPoint, 3, 3, 3);
        ter.renderFrame(world);
    }
}
