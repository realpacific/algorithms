package commons;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value;
    public List<Node> nodes = new ArrayList<>();
    public State state = State.UNVISITED;

    public Node(int value) {
        this.value = value;
    }
}