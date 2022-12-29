package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode pre;
        public SearchNode(WorldState state,int moves,SearchNode pre){
            this.state = state;
            this.moves = moves;
            this.pre = pre;
        }
        public WorldState getState(){
            return state;
        }
        public int getMoves(){
            return moves;
        }
        public SearchNode getPre(){
            return pre;
        }

        @Override
        public int compareTo(SearchNode o){
            return this.moves+ this.state.estimatedDistanceToGoal()-o.moves-o.state.estimatedDistanceToGoal();
        }
    }

    private MinPQ<SearchNode> pq = new MinPQ<>();
    private int totalMove;
    private List<SearchNode> bestSolution = new LinkedList<>();
    public Solver(WorldState initial){
        pq.insert(new SearchNode(initial, 0, null));
        while(true){
            SearchNode node = pq.delMin();
            if(node.getState().isGoal()){
                getAnswer(node);
                return;
            }else{
                for(WorldState neighbor:node.state.neighbors()){
                    if(node.pre==null||!neighbor.equals(node.pre.state)){
                        pq.insert(new SearchNode(neighbor, node.moves+1, node));
                    }    
                }
            }
        }
    }
    public int moves(){
        return totalMove;
    }
    private void getAnswer(SearchNode node){
        SearchNode p = node;
        totalMove = node.moves;
        while(p!=null){
            bestSolution.add(p);
            p = p.pre;
        }
    }
    public Iterable<WorldState> solution(){
        List<WorldState> solution = new LinkedList<>();
        for(int i=totalMove;i>=0;i-=1){
            solution.add(bestSolution.get(i).state);
        }
        return solution;
    }
}
