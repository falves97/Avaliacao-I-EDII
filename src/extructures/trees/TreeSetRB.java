package extructures.trees;

public class TreeSetRB<T extends Comparable<T>> extends TreeSet<T> {

    private NodeColor color(NodeRB<T> node) {
        if (node != null) {
            return node.getColor();
        }

        return NodeColor.BLACK;
    }

    public NodeRB<T> insertFixUp(NodeRB<T> root) {
        if (root != null) {

            // A recursão vai até as folhas pra poder analizar se há algum nó que quebre as propriedades
            root.setLeft(insertFixUp((NodeRB<T>) root.getLeft()));
            root.setLeft(insertFixUp((NodeRB<T>) root.getLeft()));
        } else {

            // Quando chega a umnó folha volta
            return null;
        }

        // Após analizar que o nó não é nulo e analizar todos os nós filhos
        // Analiza se o no atual não quebra as propriedades
        if (color((NodeRB<T>) root.getLeft()) == NodeColor.RED) {
            NodeRB<T> sonLeft = (NodeRB<T>) root.getLeft();
            NodeRB<T> sonRight = (NodeRB<T>) root.getRight();

            // Se o filho neto esquer ou direito é vermelho, há dois vermenlhos consecutivos
            if (color((NodeRB<T>) sonLeft.getLeft()) == NodeColor.RED || color((NodeRB<T>) sonLeft.getRight()) == NodeColor.RED) {

                // Caso em que o tio vermelho
                if (color(sonRight) == NodeColor.RED) {
                    sonLeft.setColor(NodeColor.BLACK);
                    sonRight.setColor(NodeColor.BLACK);
                    root.setColor(NodeColor.RED);
                    return root;

                // Se o neto vermelho está a direita do filho esquerdo
                } else if (color((NodeRB<T>) sonLeft.getRight()) == NodeColor.RED) {
                    root.setLeft(leftSimpleRotation(sonLeft));
                    sonLeft = (NodeRB<T>) root.getLeft();
                }

                // Caso em que o neto a esquerda é vermelho do filho a esquerda
                sonLeft.setColor(NodeColor.BLACK);
                root.setColor(NodeColor.RED);
                return (NodeRB<T>) rightSimpleRotation(root);
            }
        }

        // Mesmo processo para o caso simétrico
        if (color((NodeRB<T>) root.getRight()) == NodeColor.RED) {
            NodeRB<T> sonLeft = (NodeRB<T>) root.getLeft();
            NodeRB<T> sonRight = (NodeRB<T>) root.getRight();

            // Se o filho neto esquer ou direito é vermelho, há dois vermenlhos consecutivos
            if (color((NodeRB<T>) sonRight.getLeft()) == NodeColor.RED || color((NodeRB<T>) sonRight.getRight()) == NodeColor.RED) {

                // Caso em que o tio vermelho
                if (color(sonLeft) == NodeColor.RED) {
                    sonLeft.setColor(NodeColor.BLACK);
                    sonRight.setColor(NodeColor.BLACK);
                    root.setColor(NodeColor.RED);
                    return root;

                    // Se o neto vermelho está a esquerda do filho direito
                } else if (color((NodeRB<T>) sonRight.getLeft()) == NodeColor.RED) {
                    root.setRight(rightSimpleRotation(sonRight));
                    sonRight = (NodeRB<T>) root.getRight();
                }

                // Caso em que o neto a direita é vermelho do filho a direita
                sonRight.setColor(NodeColor.BLACK);
                root.setColor(NodeColor.RED);
                return (NodeRB<T>) leftSimpleRotation(root);
            }
        }

        return root;
    }

    @Override
    public void add(T data) {
        this.root = insertData(this.root, null, new NodeRB<>(), data);
        this.root = insertFixUp((NodeRB<T>) this.root);
        ((NodeRB<T>) this.root).setColor(NodeColor.BLACK);
    }
}
