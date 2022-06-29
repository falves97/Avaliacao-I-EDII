package extructures.trees;

import extructures.ExtructuriesStrategy;
import extructures.trees.node.Node;

import java.util.function.Consumer;

public class TreeSet<T extends Comparable<T>> implements ExtructuriesStrategy<T> {
    protected Node<T> root;

    protected long size;
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

    protected void leftSimpleRotation(Node<T> node) {
         Node<T> aux = node.getRight();

         node.setRight(aux.getLeft());
         if (aux.getLeft() != null) {
             aux.getLeft().setFather(node);
         }

         aux.setFather(node.getFather());

         if (node.getFather() == null) {
             this.root = aux;
         } else if (node == node.getFather().getLeft()) {
             node.getFather().setLeft(aux);
         } else {
             node.getFather().setRight(aux);
         }

         aux.setLeft(node);
         node.setFather(aux);
    }

    protected void rightSimpleRotation(Node<T> node) {
        Node<T> aux = node.getLeft();

        node.setLeft(aux.getRight());
        if (aux.getRight() != null) {
            aux.getRight().setFather(node);
        }

        aux.setFather(node.getFather());

        if (node.getFather() == null) {
            this.root = aux;
        } else if (node == node.getFather().getLeft()) {
            node.getFather().setLeft(aux);
        } else {
            node.getFather().setRight(aux);
        }

        aux.setRight(node);
        node.setFather(aux);
    }

    protected void leftDoubleRotation(Node<T> root) {
        rightSimpleRotation(root.getRight());
        leftSimpleRotation(root);
    }

    protected void rightDoubleRotation(Node<T> root) {
        leftSimpleRotation(root.getLeft());
        rightSimpleRotation(root);
    }

    protected void insertData(Node<T> root, Node<T> father, Node<T> node) {

        if (root != null) {

            if (node.getData().compareTo(root.getData()) > 0) {
                insertData(root.getRight(), root, node);
                return;
            } else if (node.getData().compareTo(root.getData()) < 0) {
                insertData(root.getLeft(), root, node);
                return;
            } else {
                root.setData(node.getData());
                return;
            }
        }

        node.setFather(father);

        if (father == null) {
            this.root = node;
        } else if (node.getData().compareTo(father.getData()) > 0) {
            father.setRight(node);
        } else {
            father.setLeft(node);
        }
        size++;
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

    protected void trasnplant(Node<T> nodeA, Node<T> nodeB) {
        if (nodeA.getFather() == null) {
            this.root = nodeB;
        } else if (nodeA == nodeA.getFather().getLeft()) {
            nodeA.getFather().setLeft(nodeB);
        } else {
            nodeA.getFather().setRight(nodeB);
        }

        if (nodeB != null) {
            nodeB.setFather(nodeA.getFather());
        }
    }

    protected T deleteData(Node<T> root, T data) {
        if (root != null) {
            if (data.compareTo(root.getData()) > 0) {
                return deleteData(root.getRight(), data);
            } else if (data.compareTo(root.getData()) < 0) {
                return deleteData(root.getLeft(), data);
            } else {
                if (root.getLeft() == null) {
                    trasnplant(root, root.getRight());
                } else if (root.getRight() == null) {
                    trasnplant(root, root.getLeft());
                } else {

                    // obtem o antecessor
                    Node<T> ant = max(root.getLeft());

                    if (ant.getFather() != root) {
                        trasnplant(ant, ant.getLeft());
                        ant.setLeft(root.getLeft());
                        ant.getLeft().setFather(ant);
                    }

                    trasnplant(root, ant);
                    ant.setRight(root.getRight());
                    ant.getRight().setFather(ant);

                }

                size--;
                return root.getData();
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
        Node<T> node = new Node<>();
        node.setData(data);

        insertData(root, null, node);
    }

    @Override
    public T find(T data) {
        return findData(root, data);
    }

    @Override
    public T remove(T data) {
        return deleteData(root, data);
    }

    @Override
    public void forEach(Consumer<T> consumer) {
        forEachInOrder(root, consumer);
    }
}
