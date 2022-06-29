package extructures.trees.node;

public class RBNode<T extends Comparable<T>> extends Node<T> {
    private NodeColor color;

    public RBNode() {
        super();
        color = NodeColor.RED;
    }

    public RBNode(T data) {
        super(data);
        color = NodeColor.RED;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    @Override
    public void of(Node<T> node) {
        super.of(node);

        if (node instanceof RBNode<T>) {
            setColor(((RBNode<T>) node).getColor());
        }
    }
}
