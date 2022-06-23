package extructures.trees;

public class NodeRB<T extends Comparable<T>> extends Node<T> {
    private NodeColor color;

    public NodeRB() {
        super();
        color = NodeColor.RED;
    }

    public NodeRB(T data) {
        super(data);
        color = NodeColor.RED;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }
}
