package extructures.trees;

public class TreeSetAVL<T extends Comparable<T>> extends TreeSet<T> {

    protected Node<T> balance(Node<T> root) {
        long leftH = height(root.getLeft());
        long rightH = height(root.getRight());

        if (rightH - leftH == 2) {
            Node<T> son = root.getRight();
            long sonLeftH = height(son.getLeft());
            long sonRightH = height(son.getRight());

            if (sonRightH - sonLeftH >= 0) {
                return leftSimpleRotation(root);
            } else {
                return leftDoubleRotation(root);
            }
        }
        else if (leftH - rightH == 2) {
            Node<T> son = root.getLeft();
            long sonLeftH = height(son.getLeft());
            long sonRightH = height(son.getRight());

            if (sonLeftH - sonRightH >= 0) {
                return rightSimpleRotation(root);
            } else {
                return rightDoubleRatation(root);
            }
        }

        return root;
    }

    @Override
    public void add(T data) {
        super.add(data);
        root = balance(root);
    }

    @Override
    public T remove(T data) {
        T deletedData = super.remove(data);
        root = balance(root);
        return deletedData;
    }
}
