package pl.lodz.p.ai.optimisation;

import pl.lodz.p.ai.graph.Node;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by Piotr on 2016-11-07.
 */
public class OpenList {
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    HashMap<String, Node> hashMap = new HashMap<>();

    public void add(Node node) {
        priorityQueue.add(node);
        hashMap.put(node.getPuzzleState().toString(), node);
    }

    public Node poll() {
        Node head = priorityQueue.poll();
        hashMap.remove(head.getPuzzleState().toString());
        return head;
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public boolean contains(Node node) {
        return hashMap.containsKey(node.getPuzzleState().toString());
    }

    public int getNodeCost(Node node) {
        return hashMap.get(node.getPuzzleState().toString()).getCost();
    }

    public void replaceNode(Node newNode) {
        Node oldNode = hashMap.get(newNode.getPuzzleState().toString());
        hashMap.replace(newNode.getPuzzleState().toString(), newNode);

        priorityQueue.remove(oldNode);
        priorityQueue.add(newNode);
    }

    public long size() {
        return hashMap.size();
    }

    public Node removeLastElement() {
        Node last = (Node) priorityQueue.toArray()[priorityQueue.size()-1];
        priorityQueue.remove(last);
        hashMap.remove(last.getPuzzleState().toString());
        return last;
    }
}
