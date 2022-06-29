package extructures.trees.node;

public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private Node<T> father;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getFather() {
        return father;
    }

    public void setFather(Node<T> father) {
        this.father = father;
    }

    public void of(Node<T> node) {
        setFather(node.getFather());
        setRight(node.getRight());
        setLeft(node.getLeft());
        setData(node.getData());
    }
}
