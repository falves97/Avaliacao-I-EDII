package extructures.trees;

import extructures.ExtructuriesStrategy;

import java.util.function.Consumer;

public class TreeSet<T extends Comparable<T>> implements ExtructuriesStrategy<T> {
    protected Node<T> root;

    protected long size;

    public Node<T> getRoot() {
        return root;
    }

    public long size() {
        return size;
    }

    protected long height(Node<T> node) {
        if (node == null) {
            return -1;
        }

        long treeL = height(node.getLeft());
        long treeR = height(node.getRight());

        return Math.max(treeL, treeR) + 1;
    }

    protected Node<T> insertData(Node<T> root, T data) {
        Node<T> node;
        Node<T> father = null;

        if (root != null) {
            father = root.getFather();

            if (data.compareTo(root.getData()) > 0) {

                node = insertData(root.getRight(), data);
                root.setRight(node);

                return root;
            } else if (data.compareTo(root.getData()) < 0) {

                node = insertData(root.getLeft(), data);
                root.setLeft(node);

                return root;
            } else {
                root.setData(data);
                return root;
            }
        }

        node = new Node<>(data);
        node.setFather(father);
        size++;

        return node;
    }

    protected T findData(Node<T> root, T data) {
        if (root != null) {
            if (root.getData().compareTo(data) > 0) {
                return findData(root.getRight(), data);
            } else if (root.getData().compareTo(data) < 0) {
                return findData(root.getLeft(), data);
            } else {
                return root.getData();
            }
        }

        return null;
    }

    protected Node<T> max(Node<T> root) {
        if (root != null) {
            if (root.getRight() != null) {
                return max(root.getRight());
            }
        }

        return root;
    }

    protected Node<T> deleteData(Node<T> root, T data, Node<T> deletedNode) {
        if (root != null) {
            if (data.compareTo(root.getData()) > 0) {

                root.setRight(deleteData(root.getRight(), data, deletedNode));
                return root;
            } else if (data.compareTo(root.getData()) < 0) {

                root.setLeft(deleteData(root.getLeft(), data, deletedNode));
                return root;

            } else {

                Node<T> newRoot;

                if (root.getRight() == null && root.getLeft() == null) { // Se o nó não têm filhos a esquerda e a direita

                    newRoot = null;

                } else if (root.getLeft() == null) { // Se o nó não têm filhos a esquerda

                    root.getRight().setFather(root.getFather());

                    newRoot = root.getRight();

                } else if (root.getRight() == null) { // Se o nó não têm filhos a direita

                    root.getLeft().setFather(root.getFather());

                    newRoot = root.getLeft();

                } else { // Se o nó têm filhos a esquerda e a direita

                    Node<T> minMax = max(root.getLeft());
                    minMax.setRight(root.getRight());
                    root.getRight().setFather(minMax);
                    root.getLeft().setFather(root.getFather());

                    newRoot = root.getLeft();
                }

                deletedNode.setData(root.getData());

                size--;
                return newRoot;
            }
        }

        return null;
    }

    public void forEachInOrder(Node<T> root, Consumer<T> consumer) {
        if (root != null) {
            forEachInOrder(root.getLeft(), consumer);
            consumer.accept(root.getData());
            forEachInOrder(root.getRight(), consumer);
        }
    }

    public long height() {
        return height(root);
    }

    @Override
    public void insert(T data) {
        root = insertData(root, data);
    }

    @Override
    public T find(T data) {
        return findData(root, data);
    }

    @Override
    public T delete(T data) {
        Node<T> deletedNode = new Node<>();

        root = deleteData(root, data, deletedNode);

        return deletedNode.getData();
    }

    @Override
    public void forEach(Consumer<T> consumer) {
        forEachInOrder(root, consumer);
    }
}
