package pl.lodz.p.ai.graph;

import pl.lodz.p.ai.puzzle.PuzzleState;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Node {
    Logger log = Logger.getLogger(Node.class.getName());
    private PuzzleState puzzleState;
    private Node parent;
    private int depth;
    private ArrayList<Node> children = new ArrayList<Node>();

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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addChild(Node child) {
        if (child == null) {
            log.severe("Trying to add null as a child");
            return;
        }
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
}
