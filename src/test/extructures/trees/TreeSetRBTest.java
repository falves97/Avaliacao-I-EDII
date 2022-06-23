package test.extructures.trees;

import extructures.trees.NodeColor;
import extructures.trees.NodeRB;
import extructures.trees.TreeSetRB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeSetRBTest {

    private TreeSetRB<Integer> integerTreeSetRB;

    @BeforeEach
    public void init() {
        integerTreeSetRB = new TreeSetRB<>();
    }

    @Test
    public void testInsercao() {
        integerTreeSetRB.add(2);
        Assertions.assertEquals(integerTreeSetRB.size(), 1);
        NodeRB<Integer> node = (NodeRB<Integer>) integerTreeSetRB.getRoot();
        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);
    }

    @Test
    public void testInsercaoDoisNosVermelhosEsquerdoComTioPreto(){
        integerTreeSetRB.add(3);
        integerTreeSetRB.add(2);
        integerTreeSetRB.add(1);

        NodeRB<Integer> node = (NodeRB<Integer>) integerTreeSetRB.getRoot();

        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getLeft();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);

        node = (NodeRB<Integer>) node.getFather().getRight();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);

    }

    @Test
    public void testInsercaoDoisNosVermelhosComTioVermelho(){
        integerTreeSetRB.add(3);
        integerTreeSetRB.add(2);
        integerTreeSetRB.add(4);
        integerTreeSetRB.add(1);

        NodeRB<Integer> node = (NodeRB<Integer>) integerTreeSetRB.getRoot();

        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getLeft();
        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getLeft();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);

        node = (NodeRB<Integer>) node.getFather().getFather().getRight();
        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);
    }

    @Test
    public void testInsercaoNetoDireitoVermelhoFilhoEsquerdoVermelhoComTioPreto(){
        integerTreeSetRB.add(4);
        integerTreeSetRB.add(2);
        integerTreeSetRB.add(3);

        NodeRB<Integer> node = (NodeRB<Integer>) integerTreeSetRB.getRoot();

        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getLeft();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);

        node = (NodeRB<Integer>) node.getFather().getRight();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);
    }

    @Test
    public void testInsercaoDoisNosVermelhosDireitaComTioPreto(){
        integerTreeSetRB.add(3);
        integerTreeSetRB.add(4);
        integerTreeSetRB.add(5);

        NodeRB<Integer> node = (NodeRB<Integer>) integerTreeSetRB.getRoot();

        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getLeft();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);

        node = (NodeRB<Integer>) node.getFather().getRight();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);

    }

    @Test
    public void testInsercaoDoisNosVermelhosDirataComTioVermelho(){
        integerTreeSetRB.add(3);
        integerTreeSetRB.add(2);
        integerTreeSetRB.add(4);
        integerTreeSetRB.add(5);

        NodeRB<Integer> node = (NodeRB<Integer>) integerTreeSetRB.getRoot();

        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getLeft();
        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getFather().getRight();
        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getRight();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);
    }

    @Test
    public void testInsercaoNetoEsquerdoVermelhoFilhoDireitoVermelhoComTioPreto(){
        integerTreeSetRB.add(4);
        integerTreeSetRB.add(6);
        integerTreeSetRB.add(5);

        NodeRB<Integer> node = (NodeRB<Integer>) integerTreeSetRB.getRoot();

        Assertions.assertEquals(node.getColor(), NodeColor.BLACK);

        node = (NodeRB<Integer>) node.getLeft();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);

        node = (NodeRB<Integer>) node.getFather().getRight();
        Assertions.assertEquals(node.getColor(), NodeColor.RED);
    }


}
