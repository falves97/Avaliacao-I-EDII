package extructures.trees;

import extructures.ExtructuriesStrategy;

import java.lang.reflect.InvocationTargetException;
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

    protected Node<T> leftSimpleRotation(Node<T> root) {
        Node<T> aux = null;

        if (root != null) {
            aux = root.getRight();
            root.setRight(aux.getLeft());
            aux.setLeft(root);
            aux.setFather(root.getFather());
            root.setFather(aux);

            if (root.getRight() != null) {
                root.getRight().setFather(root);
            }
        }

        return aux;
    }

    protected Node<T> rightSimpleRotation(Node<T> root) {
        Node<T> aux = null;

        if (root != null) {
            aux = root.getLeft();
            root.setLeft(aux.getRight());
            aux.setRight(root);
            aux.setFather(root.getFather());
            root.setFather(aux);

            if (root.getLeft() != null) {
                root.getLeft().setFather(root);
            }
        }

        return aux;
    }

    protected Node<T> leftDoubleRotation(Node<T> root) {
        root.setRight(rightSimpleRotation(root.getRight()));
        return leftSimpleRotation(root);
    }

    protected Node<T> rightDoubleRatation(Node<T> root) {
        root.setLeft(leftSimpleRotation(root.getLeft()));
        return rightSimpleRotation(root);
    }

    protected Node<T> insertData(Node<T> root, Node<T> father, Node<T> newNode, T data) {

        if (root != null) {

            if (data.compareTo(root.getData()) > 0) {

                newNode = insertData(root.getRight(), root, newNode, data);
                root.setRight(newNode);

                return root;
            } else if (data.compareTo(root.getData()) < 0) {

                newNode = insertData(root.getLeft(), root, newNode, data);
                root.setLeft(newNode);

                return root;
            } else {
                root.setData(data);
                return root;
            }
        }

        newNode.setData(data);
        newNode.setFather(father);
        size++;

        return newNode;
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
    public void add(T data) {
        root = insertData(root, null, new Node<>(), data);
    }

    @Override
    public T find(T data) {
        return findData(root, data);
    }

    @Override
    public T remove(T data) {
        Node<T> deletedNode = new Node<>();

        root = deleteData(root, data, deletedNode);

        return deletedNode.getData();
    }

    @Override
    public void forEach(Consumer<T> consumer) {
        forEachInOrder(root, consumer);
    }
}
