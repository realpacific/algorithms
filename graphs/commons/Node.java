package graphs.commons;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value;
    public List<Node> nodes;
    public State state;

    public Node(int value) {
        this.value = value;
        this.state = State.UNVISITED;
        nodes = new ArrayList<>();
    }

    public Node deepCopy() {
        this.state = State.UNVISITED;
        for (Node n : this.nodes) {
            if (n.state != State.UNVISITED) {
                n.deepCopy();
            }
        }
        return this;
    }
}
