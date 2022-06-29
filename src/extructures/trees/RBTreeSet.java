package extructures.trees;

import extructures.trees.node.Node;
import extructures.trees.node.NodeColor;
import extructures.trees.node.RBNode;

public class RBTreeSet<T extends Comparable<T>> extends TreeSet<T> {

    private final RBNode<T> nil;

    public RBTreeSet() {
        nil = new RBNode<>();
        nil.setColor(NodeColor.BLACK);
        root  = nil;
    }
    private int colorHeight(RBNode<T> root) {
        if (root != null) {
            if (root.getColor() == NodeColor.BLACK) {
                return Math.max(colorHeight((RBNode<T>) root.getLeft()), colorHeight((RBNode<T>) root.getRight())) + 1;
            } else {
                return Math.max(colorHeight((RBNode<T>) root.getLeft()), colorHeight((RBNode<T>) root.getRight()));
            }
        }

        return -1;
    }

    public boolean colorBlackValidation() {
        int heightLeft = 0;
        int heightRight = 0;

        if (root != null) {
            heightLeft = colorHeight((RBNode<T>) root.getLeft());
            heightRight = colorHeight((RBNode<T>) root.getRight());
        }

        return heightLeft == heightRight;
    }

    private boolean colorDoubleRed(RBNode<T> root) {
        if (root != nil) {
            RBNode<T> sunLeft = (RBNode<T>) root.getLeft();
            RBNode<T> sunRight = (RBNode<T>) root.getRight();
            boolean drLeft = false;
            boolean drRight = false;

            if (sunLeft != nil) {
                drLeft = colorDoubleRed((RBNode<T>) root.getLeft());
            }

            if (sunRight != nil) {
                drRight = colorDoubleRed((RBNode<T>) root.getRight());
            }

            return drLeft || drRight;
        }

        RBNode<T> father = (RBNode<T>) root.getFather();
        return root.getColor() == NodeColor.RED && father.getColor() == NodeColor.RED;
    }

    public boolean colorRedValidation() {
        return colorDoubleRed((RBNode<T>) root);
    }

    public boolean propertiesValidation() {
        return colorBlackValidation() && !colorRedValidation();
    }

    private void insertFixUp(RBNode<T> root) {
        while (((RBNode<T>) root.getFather()).getColor() == NodeColor.RED) {
            RBNode<T> grandFather = (RBNode<T>) root.getFather().getFather();

            // Se o pai de root é filho esquerdo
            if (root.getFather() == grandFather.getLeft()) {
                RBNode<T> uncle = (RBNode<T>) grandFather.getRight();

                // Se o tio é vermelho
                if (uncle.getColor() == NodeColor.RED) {
                    ((RBNode<T>) root.getFather()).setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    grandFather.setColor(NodeColor.RED);

                    root = grandFather;

                } else {

                    // Se root é filho direito de um nó esquerdo
                    if (root == root.getFather().getRight()) {
                        root = (RBNode<T>) root.getFather();
                        leftSimpleRotation(root);
                    }

                    // Se root é filho esquerdo de um nó esquerdo
                    ((RBNode<T>) root.getFather()).setColor(NodeColor.BLACK);
                    grandFather.setColor(NodeColor.RED);
                    rightSimpleRotation(grandFather);
                }
            }

            // Se o pai de root é filho direito
            else {
                RBNode<T> uncle = (RBNode<T>) grandFather.getLeft();

                // Se o tio é vermelho
                if (uncle.getColor() == NodeColor.RED) {
                    ((RBNode<T>) root.getFather()).setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    grandFather.setColor(NodeColor.RED);

                    root = grandFather;

                } else {

                    // Se root é filho esquerdo de um nó direito
                    if (root == root.getFather().getLeft()) {
                        root = (RBNode<T>) root.getFather();
                        rightSimpleRotation(root);
                    }

                    // Se root é filho direito de um nó direito
                    ((RBNode<T>) root.getFather()).setColor(NodeColor.BLACK);
                    grandFather.setColor(NodeColor.RED);
                    leftSimpleRotation(grandFather);
                }
            }
        }

        ((RBNode<T>) this.root).setColor(NodeColor.BLACK);
    }

    private void deleteFixUp(RBNode<T> root) {
        while (root != this.root && root.getColor() == NodeColor.BLACK) {
            if (root == root.getFather().getLeft()) {
                Node<T> brother = root.getFather().getRight();

                // Se a cor do irmão for vermelho
                if (((RBNode<T>) brother).getColor() == NodeColor.RED) {
                    ((RBNode<T>) brother).setColor(NodeColor.BLACK);
                    ((RBNode<T>) root.getFather()).setColor(NodeColor.RED);
                    leftSimpleRotation(root.getFather());
                    brother = root.getFather().getRight();
                }

                // Se os filhos de brother são pretos
                if (
                        ((RBNode<T>) brother.getLeft()).getColor() == NodeColor.BLACK &&
                        ((RBNode<T>) brother.getRight()).getColor() == NodeColor.BLACK) {

                    ((RBNode<T>) brother).setColor(NodeColor.RED);
                    root = (RBNode<T>) root.getFather();

                // Se apenas o filho direito de brother é preto
                } else {
                    if (((RBNode<T>) brother.getRight()).getColor() == NodeColor.BLACK) {

                        ((RBNode<T>) brother.getLeft()).setColor(NodeColor.BLACK);
                        ((RBNode<T>) brother).setColor(NodeColor.RED);
                        rightSimpleRotation(brother);
                        brother = root.getFather();
                    }

                    ((RBNode<T>) brother).setColor(((RBNode<T>) root.getFather()).getColor());
                    ((RBNode<T>) root.getFather()).setColor(NodeColor.BLACK);
                    ((RBNode<T>) brother.getRight()).setColor(NodeColor.BLACK);
                    leftSimpleRotation(root.getFather());
                    root = (RBNode<T>) this.root;
                }
            } else {
                Node<T> brother = root.getFather().getLeft();

                // Se a cor do irmão for vermelho
                if (((RBNode<T>) brother).getColor() == NodeColor.RED) {

                    ((RBNode<T>) brother).setColor(NodeColor.BLACK);
                    ((RBNode<T>) root.getFather()).setColor(NodeColor.RED);
                    rightSimpleRotation(root.getFather());
                    brother = root.getFather().getLeft();
                }

                // Se os filhos de brother são pretos
                if (
                        ((RBNode<T>) brother.getLeft()).getColor() == NodeColor.BLACK &&
                        ((RBNode<T>) brother.getRight()).getColor() == NodeColor.BLACK) {

                    ((RBNode<T>) brother).setColor(NodeColor.RED);
                    root = (RBNode<T>) root.getFather();

                    // Se apenas o filho esquerdo de brother é preto
                } else {
                    if (((RBNode<T>) brother.getLeft()).getColor() == NodeColor.BLACK) {
                        ((RBNode<T>) brother.getRight()).setColor(NodeColor.BLACK);
                        ((RBNode<T>) brother).setColor(NodeColor.RED);
                        leftSimpleRotation(brother);
                        brother = root.getFather();
                    }

                    ((RBNode<T>) brother).setColor(((RBNode<T>) root.getFather()).getColor());
                    ((RBNode<T>) root.getFather()).setColor(NodeColor.BLACK);
                    ((RBNode<T>) brother.getLeft()).setColor(NodeColor.BLACK);
                    rightSimpleRotation(root.getFather());
                    root = (RBNode<T>) this.root;
                }
            }
        }

        root.setColor(NodeColor.BLACK);
    }

    @Override
    protected Node<T> max(Node<T> root) {
        if (root != nil) {
            if (root.getRight() != nil) {
                return max(root.getRight());
            }
        }

        return root;
    }

    @Override
    protected void leftSimpleRotation(Node<T> node) {
        Node<T> aux = node.getRight();

        node.setRight(aux.getLeft());
        if (aux.getLeft() != nil) {
            aux.getLeft().setFather(node);
        }

        aux.setFather(node.getFather());

        if (node.getFather() == nil) {
            this.root = aux;
        } else if (node == node.getFather().getLeft()) {
            node.getFather().setLeft(aux);
        } else {
            node.getFather().setRight(aux);
        }

        aux.setLeft(node);
        node.setFather(aux);
    }

    @Override
    protected void rightSimpleRotation(Node<T> node) {
        Node<T> aux = node.getLeft();

        node.setLeft(aux.getRight());
        if (aux.getRight() != nil) {
            aux.getRight().setFather(node);
        }

        aux.setFather(node.getFather());

        if (node.getFather() == nil) {
            this.root = aux;
        } else if (node == node.getFather().getLeft()) {
            node.getFather().setLeft(aux);
        } else {
            node.getFather().setRight(aux);
        }

        aux.setRight(node);
        node.setFather(aux);
    }

    @Override
    protected void trasnplant(Node<T> nodeA, Node<T> nodeB) {
        if (nodeA == nil) {
            root = nodeB;
        } else if (nodeA == nodeA.getFather().getLeft()) {
            nodeA.getFather().setLeft(nodeB);
        } else {
            nodeA.getFather().setRight(nodeB);
        }

        nodeB.setFather(nodeA.getFather());
    }

    @Override
    protected void insertData(Node<T> root, Node<T> father, Node<T> node) {
        RBNode<T> rBNode = (RBNode<T>) node;

        if (root != nil) {
            if (node.getData().compareTo(root.getData()) > 0) {
                insertData(root.getRight(), root, node);
                return;
            } else if (node.getData().compareTo(root.getData()) < 0){
                insertData(root.getLeft(), root, node);
                return;
            } else {
                root.setData(node.getData());
                return;
            }
        }

        node.setFather(father);

        if (father == nil) {
            this.root = rBNode;
        } else if (node.getData().compareTo(father.getData()) > 0) {
            father.setRight(node);
        } else {
            father.setLeft(node);
        }

        node.setLeft(nil);
        node.setRight(nil);
        insertFixUp(rBNode);
        size++;
    }

    @Override
    protected T deleteData(Node<T> root, T data) {
        if (root != nil) {
            if (data.compareTo(root.getData()) > 0) {
                return deleteData(root.getRight(), data);
            } else if (data.compareTo(root.getData()) < 0) {
                return deleteData(root.getLeft(), data);
            } else {
                RBNode<T> nodeA;
                RBNode<T> nodeB = (RBNode<T>) root;
                NodeColor originalColor = nodeB.getColor();

                if (root.getLeft() == nil) {
                    nodeA = (RBNode<T>) root.getRight();
                    trasnplant(root, root.getRight());
                } else if (root.getRight() == nil) {
                    nodeA = (RBNode<T>) root.getLeft();
                    trasnplant(root, root.getLeft());
                } else {
                    nodeB = (RBNode<T>) max(root.getLeft());
                    originalColor = nodeB.getColor();
                    nodeA = (RBNode<T>) nodeB.getLeft();

                    if (nodeB.getFather() == root) {
                        nodeA.setFather(nodeB);
                    } else {
                        trasnplant(nodeB, nodeB.getLeft());
                        nodeB.setLeft(root.getLeft());
                        nodeB.getLeft().setFather(nodeB);
                    }

                    trasnplant(root, nodeB);
                    nodeB.setRight(root.getRight());
                    nodeB.getRight().setFather(nodeB);
                    nodeB.setColor(((RBNode<T>) root).getColor());
                }

                if (originalColor == NodeColor.BLACK) {
                    deleteFixUp(nodeA);
                }
            }

            return root.getData();
        }

        return null;
    }

    @Override
    public void add(T data) {
        RBNode<T> rBNode = new RBNode<>();
        rBNode.setData(data);
        this.insertData(this.root, nil, rBNode);
    }

    @Override
    public T remove(T data) {
        return deleteData(root, data);
    }
}
