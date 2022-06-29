package extructures.trees;

import extructures.trees.node.Node;

public class AVLTreeSet<T extends Comparable<T>> extends TreeSet<T> {

    protected void balance(Node<T> root) {
        long leftH = height(root.getLeft());
        long rightH = height(root.getRight());

        if (rightH - leftH == 2) {
            Node<T> son = root.getRight();
            long sonLeftH = height(son.getLeft());
            long sonRightH = height(son.getRight());

            if (sonRightH - sonLeftH >= 0) {
                leftSimpleRotation(root);
            } else {
                leftDoubleRotation(root);
            }
        }
        else if (leftH - rightH == 2) {
            Node<T> son = root.getLeft();
            long sonLeftH = height(son.getLeft());
            long sonRightH = height(son.getRight());

            if (sonLeftH - sonRightH >= 0) {
                rightSimpleRotation(root);
            } else {
                rightDoubleRotation(root);
            }
        }
    }

    @Override
    public void add(T data) {
        super.add(data);
        balance(root);
    }

    @Override
    public T remove(T data) {
        T deletedData = super.remove(data);
        balance(root);
        return deletedData;
    }
}
