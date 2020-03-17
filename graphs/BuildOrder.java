import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import commons.Graph;
import commons.State;
import commons.Graph.Vertex;

class BuildOrder {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Graph.Vertex vA = new Graph.Vertex("a");
        Graph.Vertex vB = new Graph.Vertex("b");
        Graph.Vertex vC = new Graph.Vertex("c");
        Graph.Vertex vE = new Graph.Vertex("e");
        Graph.Vertex vD = new Graph.Vertex("d");
        Graph.Vertex vG = new Graph.Vertex("g");
        Graph.Vertex vF = new Graph.Vertex("f");
        Graph.Vertex vH = new Graph.Vertex("h");

        vA.edges.add(new Graph.Edge(vA, vE));

        vB.edges.add(new Graph.Edge(vB, vA));
        vB.edges.add(new Graph.Edge(vB, vE));
        vB.edges.add(new Graph.Edge(vB, vH));

        vF.edges.add(new Graph.Edge(vF, vB));
        vF.edges.add(new Graph.Edge(vF, vA));
        vF.edges.add(new Graph.Edge(vF, vC));

        vC.edges.add(new Graph.Edge(vC, vA));

        vD.edges.add(new Graph.Edge(vD, vG));
        // vH.edges.add(new Graph.Edge(vH, vF));

        List<Graph.Vertex> listOfVertices = Arrays.asList(vA, vB, vC, vD, vE, vF, vG);
        graph.vertices.addAll(listOfVertices);

        System.out.println(graph.toString());

        for (Graph.Vertex v : graph.vertices) {
            System.out.println(v + ": " + v.edges);
        }

        // Pick one arbitary vertex in a graph
        LinkedHashSet<Graph.Vertex> buildOrder = new LinkedHashSet<>();
        for (Graph.Vertex v : listOfVertices) {
            // Perform dfs
            dfs(v, buildOrder);
        }
        System.out.println("----BUILD ORDER---");
        for (int i = buildOrder.size() - 1; i >= 0; i--) {
            System.out.println(buildOrder.toArray()[i]);
        }

    }

    static void dfs(Graph.Vertex v, LinkedHashSet<Graph.Vertex> result) {
        // This is the leaf node in the graph
        // This must be built last as it depends upon its parent
        if (v.edges.isEmpty()) {
            result.add(v);
            return;
        }

        // Visit all its edges but in DFS manner
        for (int i = 0; i < v.edges.size(); i++) {
            // Check for cycles
            if(v.edges.get(i).end.state == State.VISITING) {
                System.out.println("HAS CYCLES");
                throw new RuntimeException("HAS CYCLES");
            }
            // Ignore if already visited
            if (v.edges.get(i).end.state == State.VISITED) {
                continue;
            }
            v.edges.get(i).end.state = State.VISITING;
            dfs(v.edges.get(i).end, result);
            v.edges.get(i).end.state = State.VISITED;
        }
        result.add(v);
    }
}