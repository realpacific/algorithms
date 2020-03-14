package commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    public List<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return String.format("Graph(%s)", Arrays.toString(vertices.toArray()));
    }

    /**
     * The node in a graph
     */
    public static class Vertex {
        public String value;
        public List<Edge> edges = new ArrayList<>();
        public State state = State.UNVISITED;

        public Vertex(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Vertex(%s)", value);
        }
    }

    /**
     * The line between two nodes or vertices in graph
     */
    public static class Edge {
        public Vertex start;
        public Vertex end;

        public Edge(Vertex start, Vertex end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("Edge(%s, %s)", start, end);
        }
    }
}