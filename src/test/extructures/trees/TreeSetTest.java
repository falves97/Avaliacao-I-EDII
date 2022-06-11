package test.extructures.trees;

import extructures.trees.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TreeSetTest {
    private TreeSet<Integer> integerTreeSet;
    private List<Integer> elements;

    @BeforeEach
    private void init() {
        integerTreeSet = new TreeSet<>();
        elements = Arrays.asList(7, 6, 9, 3, 4, 5, 8);
        elements.forEach(e -> integerTreeSet.insert(e));
    }

    @Test
    public void testInstaciacao() {
        Assertions.assertEquals(integerTreeSet.size(), elements.size());
    }

    @Test
    public void testInsercaoSimples() {
        Assertions.assertEquals(integerTreeSet.size(), elements.size());
    }

    @Test
    public void testInsercaoRepetida() {
        integerTreeSet.insert(4);
        Assertions.assertEquals(integerTreeSet.size(), elements.size());
    }

    private void remove(int removed) {
        int dataRemoved = integerTreeSet.delete(removed);
        Assertions.assertEquals(dataRemoved, removed);
        System.out.println("============= Teste Remoção " + removed + " =============");
        integerTreeSet.forEach(System.out::println);
        Assertions.assertEquals(integerTreeSet.size(), elements.size() - 1);
        System.out.println("============================");
    }

    @Test
    public void testRemocaoFolha() {
        remove(4);
    }

    @Test
    public void testRemocaoNoSemFilhoEsquerdo() {
        remove(3);
    }

    @Test
    public void testRemocaoNoComDoisFilhos() {
        remove(6);
    }

    @Test
    public void testRemoveraiz() {
        remove(7);
    }
}
