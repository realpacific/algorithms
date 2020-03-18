package graphs;

import graphs.commons.Node;
import graphs.commons.State;

import java.util.LinkedList;

class RoutesBetweenNodes {
    public static void main(final String[] args) throws CloneNotSupportedException {
        final Node n1 = new Node(1);
        final Node n2 = new Node(2);
        final Node n3 = new Node(3);
        final Node n4 = new Node(4);
        final Node n5 = new Node(5);
        final Node n6 = new Node(6);
        final Node n7 = new Node(7);
        final Node n8 = new Node(8);
        final Node n9 = new Node(9);

        n1.nodes.add(n2);

        n2.nodes.add(n3);
        n2.nodes.add(n4);

        n4.nodes.add(n5);
        n4.nodes.add(n6);
        n5.nodes.add(n7);
        n7.nodes.add(n8);
        n8.nodes.add(n9);

        final Node n10 = new Node(10);
        final Node n11 = new Node(11);
        final Node n12 = new Node(12);

        n10.nodes.add(n11);
        n11.nodes.add(n10);
        n10.nodes.add(n12);

        assert isNodeConnected(n1.deepCopy(), n2.deepCopy());
        assert isNodeConnected(n1.deepCopy(), n9.deepCopy());
        assert isNodeConnected(n1.deepCopy(), n7.deepCopy());
        assert isNodeConnected(n1.deepCopy(), n5.deepCopy());

        assert !isNodeConnected(n1.deepCopy(), n10.deepCopy());
        assert isNodeConnected(n11.deepCopy(), n10.deepCopy());
        assert !isNodeConnected(n12.deepCopy(), n1.deepCopy());
        System.out.println("Completed");

    }

    static boolean isNodeConnected(final Node sNode, final Node eNode) {
        if (sNode == eNode)
            return true;

        final LinkedList<Node> list = new LinkedList<>();
        list.add(sNode);
        while (!list.isEmpty()) {
            Node current = null;
            current = list.removeFirst();
            current.state = State.VISITED;
            if (current.value == eNode.value) {
                return true;
            }
            for (final Node n : current.nodes) {
                if (n.state != State.VISITED) {
                    list.add(n);
                }
            }
        }

        return false;
    }
}
