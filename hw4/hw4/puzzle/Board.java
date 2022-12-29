package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Board implements WorldState{
    private int N;
    private int tiles[][];
    private int row,col;
    public Board(int[][] tiles){
        if(tiles==null||tiles[0]==null||tiles.length!=tiles[0].length){
            throw new IllegalArgumentException();
        }
        N = tiles.length;
        this.tiles = new int[N][N];
        for(int i=0;i<N;i+=1){
            for(int j=0;j<N;j+=1){
                this.tiles[i][j] = tiles[i][j];
                if(tiles[i][j]==0){
                    row = i;
                    col = j;
                }
            }
        }
    } 
    public int tileAt(int i, int j){
        if(i>=size()||j>=size()) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];  //return Row i Col j
    }
    public int size(){
        return N;
    }
    public Iterable<WorldState> neighbors(){
        Queue <WorldState> listBoard = new Queue<>();
        int[][] tileCopy = tiles.clone();
        for(int i=0;i<N;i+=1){
            for(int j=0;j<N;j+=1){
                if(Math.abs(row-i)+Math.abs(col-j)==1){
                    tileCopy[row][col] = tileCopy[i][j];
                    tileCopy[i][j] = 0;
                    listBoard.enqueue(new Board(tileCopy));
                    tileCopy[i][j] = tileCopy[row][col];
                    tileCopy[row][col] = 0;
                }
            }
        }
        return listBoard;
    }
    public int hamming(){
        int num=0;
        for(int i=0;i<N;i+=1){
            for(int j=0;j<N;j+=1){
                if(tiles[i][j]>0&&tileAt(i, j)!=i*N+j+1)  num+=1;
            }
        }
        return num;
    }
    public int manhattan(){
        int num=0;
        int x,y;
        for(int i=0;i<N;i+=1){
            for(int j=0;j<N;j+=1){
                if(tileAt(i, j)!=(i)*N+j+1){
                    x=tileAt(i, j)%N;
                    y=tileAt(i, j)/N;
                    num+=Math.abs(x-i)+Math.abs(y-j);
                }
            }
        }
        return num;
    }
    public int estimatedDistanceToGoal(){
        return manhattan();
    }
    public boolean equals(Object y){
        if(y==this) return true;
        if(y==null||y.getClass()!=this.getClass()) return false;
        Board yToBoard = (Board) y;
        for(int i=0;i<N;i+=1){
            for(int j=0;j<N;j+=1){
                if(tileAt(i, j)==(yToBoard.tileAt(i,j))) return false;
            }
        }
        return true;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
