package commons;

public class Tree {
    public Tree left;
    public Tree right;
    public Integer data;

    public Tree(Integer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("Node(%s, l=%s, r=%s)", data, left, right);
    }
}