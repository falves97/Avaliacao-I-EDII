package test.extructures.trees;

import extructures.trees.AVLTreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AVLTreeSetTest {
    private AVLTreeSet<Integer> AVLTreeSet;

    @BeforeEach
    public void init() {
        AVLTreeSet = new AVLTreeSet<>();
    }

    @Test
    public void testIntanciacao(){
        Assertions.assertEquals(AVLTreeSet.size(), 0);
    }

    @Test
    public void testRotacaoSimpleParaEsquerda() {
        AVLTreeSet.add(6);
        AVLTreeSet.add(8);
        AVLTreeSet.add(12);

        Assertions.assertEquals(AVLTreeSet.height(), 1);
    }

    @Test
    public void testRotacaoSimpleParaDireita() {
        AVLTreeSet.add(10);
        AVLTreeSet.add(6);
        AVLTreeSet.add(12);
        AVLTreeSet.add(11);
        AVLTreeSet.add(14);

        AVLTreeSet.remove(6);

        Assertions.assertEquals(AVLTreeSet.height(), 2);
    }

    @Test
    public void testRotacaoDuplaParaDireita() {
        AVLTreeSet.add(6);
        AVLTreeSet.add(8);
        AVLTreeSet.add(7);

        Assertions.assertEquals(AVLTreeSet.height(), 1);
    }

    @Test
    public void testRotacaoDuplaParaEsquerda() {
        AVLTreeSet.add(6);
        AVLTreeSet.add(3);
        AVLTreeSet.add(5);

        Assertions.assertEquals(AVLTreeSet.height(), 1);
    }

}
