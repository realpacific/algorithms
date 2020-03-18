package graphs.commons;

public class BidirectionalTree {
    public BidirectionalTree parent;
    public BidirectionalTree left;
    public BidirectionalTree right;
    public Integer data;

    public BidirectionalTree(BidirectionalTree parent, Integer data) {
        this.parent = parent;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("BidirectionalTree(%s)", data);
    }
}
