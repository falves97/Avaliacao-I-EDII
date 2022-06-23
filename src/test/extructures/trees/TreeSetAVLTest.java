package test.extructures.trees;

import extructures.trees.TreeSetAVL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeSetAVLTest {
    private TreeSetAVL<Integer> treeSetAVL;

    @BeforeEach
    public void init() {
        treeSetAVL = new TreeSetAVL<>();
    }

    @Test
    public void testIntanciacao(){
        Assertions.assertEquals(treeSetAVL.size(), 0);
    }

    @Test
    public void testRotacaoSimpleParaEsquerda() {
        treeSetAVL.add(6);
        treeSetAVL.add(8);
        treeSetAVL.add(12);

        Assertions.assertEquals(treeSetAVL.height(), 1);
    }

    @Test
    public void testRotacaoSimpleParaDireita() {
        treeSetAVL.add(10);
        treeSetAVL.add(6);
        treeSetAVL.add(12);
        treeSetAVL.add(11);
        treeSetAVL.add(14);

        treeSetAVL.remove(6);

        Assertions.assertEquals(treeSetAVL.height(), 2);
    }

    @Test
    public void testRotacaoDuplaParaDireita() {
        treeSetAVL.add(6);
        treeSetAVL.add(8);
        treeSetAVL.add(7);

        Assertions.assertEquals(treeSetAVL.height(), 1);
    }

    @Test
    public void testRotacaoDuplaParaEsquerda() {
        treeSetAVL.add(6);
        treeSetAVL.add(3);
        treeSetAVL.add(5);

        Assertions.assertEquals(treeSetAVL.height(), 1);
    }

}
