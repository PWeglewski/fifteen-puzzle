package pl.lodz.p.ai.graph;

import pl.lodz.p.ai.puzzle.PuzzleState;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
    //    Logger log = Logger.getLogger(Node.class.getName());
    private PuzzleState puzzleState;
    private Node parent;
    private int depth;
    private int helperValue;
    private ArrayList<Node> children = new ArrayList<Node>();
    /**
     * Used only in A* algorithm
     */
    private int cost;

    /**
     * Constructor for graph root
     *
     * @param parent
     * @param puzzleState
     */
    public Node(Node parent, PuzzleState puzzleState) {
        this.puzzleState = puzzleState;
        this.depth = 0;
        this.parent = parent;
    }

    public Node(Node parent, PuzzleState puzzleState, int depth) {
        this.puzzleState = puzzleState;
        this.depth = depth;
        this.parent = parent;
    }

    public Node(Node parent, PuzzleState puzzleState, int depth, int cost) {
        this.puzzleState = puzzleState;
        this.depth = depth;
        this.parent = parent;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void increaseCost(int value) {
        setCost(getCost() + value);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addChild(Node child) {
//        if (child == null) {
//            log.severe("Trying to add null as a child");
//            return;
//        }
        children.add(child);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public PuzzleState getPuzzleState() {
        return puzzleState;
    }

    public void setPuzzleState(PuzzleState puzzleState) {
        this.puzzleState = puzzleState;
    }

    public int getDepth() {
        return depth;
    }

    public int getValue() {
        return helperValue;
    }

    public void setValue(int value) {
        this.helperValue = value;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.valueOf(this.getCost()).compareTo(o.getCost());
    }

    @Override
    public int hashCode() {
        return puzzleState.hashCode();
    }
}
