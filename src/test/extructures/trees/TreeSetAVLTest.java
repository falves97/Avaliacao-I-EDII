package test.extructures.trees;

import extructures.trees.TreeSetAVL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        treeSetAVL.insert(6);
        treeSetAVL.insert(8);
        treeSetAVL.insert(12);

        Assertions.assertEquals(treeSetAVL.height(), 1);
    }

    @Test
    public void testRotacaoSimpleParaDireita() {
        treeSetAVL.insert(10);
        treeSetAVL.insert(6);
        treeSetAVL.insert(12);
        treeSetAVL.insert(11);
        treeSetAVL.insert(14);

        treeSetAVL.delete(6);

        Assertions.assertEquals(treeSetAVL.height(), 2);
    }

    @Test
    public void testRotacaoDuplaParaDireita() {
        treeSetAVL.insert(6);
        treeSetAVL.insert(8);
        treeSetAVL.insert(7);

        Assertions.assertEquals(treeSetAVL.height(), 1);
    }

    @Test
    public void testRotacaoDuplaParaEsquerda() {
        treeSetAVL.insert(6);
        treeSetAVL.insert(3);
        treeSetAVL.insert(5);

        Assertions.assertEquals(treeSetAVL.height(), 1);
    }

}
