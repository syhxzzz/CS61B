package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player {
    int x;
    int y;
    TETile[][] world;
    TETile symbol = Tileset.PLAYER;
    public  Player(int x,int y,TETile[][] world){
        this.x = x;
        this.y = y;
        this.world = world;
    }
    public void moveLeft(){
        if(world[x-1][y] == Tileset.FLOOR){
            x-=1;
            world[x+1][y] = Tileset.FLOOR;
            world[x][y] = symbol;
        }
    }
    public void moveRight(){
        if(world[x+1][y] == Tileset.FLOOR){
            x+=1;
            world[x-1][y] = Tileset.FLOOR;
            world[x][y] = symbol;
        }
    }
    public void moveUp(){
        if(world[x][y+1] == Tileset.FLOOR){
            y+=1;
            world[x][y-1] = Tileset.FLOOR;
            world[x][y] = symbol;
        }
    }
    public void moveDown(){
        if(world[x][y-1] == Tileset.FLOOR){
            y-=1;
            world[x][y+1] = Tileset.FLOOR;
            world[x][y] = symbol;
        }
    }
}
