package extructures.trees;

public class TreeSetAVL<T extends Comparable<T>> extends TreeSet<T> {

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
    public void insert(T data) {
        super.insert(data);
        root = balance(root);
    }

    @Override
    public T delete(T data) {
        T deletedData = super.delete(data);
        root = balance(root);
        return deletedData;
    }
}
