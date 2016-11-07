package pl.lodz.p.ai.optimisation;

import pl.lodz.p.ai.graph.Node;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by Piotr on 2016-11-07.
 */
public class ClosedList {
    HashMap<String, Node> hashMap = new HashMap<>();

    public void add(Node node) {
        hashMap.put(node.getPuzzleState().toString(), node);
    }

    public boolean contains(Node node) {
        return hashMap.containsKey(node.getPuzzleState().toString());
    }

    public int getNodeCost(Node node) {
        return hashMap.get(node.getPuzzleState().toString()).getCost();
    }

    public void remove(Node node) {
        hashMap.remove(node.getPuzzleState().toString());
    }
}
