package test.extructures.trees;

import extructures.trees.BTreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BTreeSetTest {
    private BTreeSet<Integer> tree;

    @BeforeEach
    public void init() {
        tree = new BTreeSet<>(2);
    }

    @Test
    public void testInsercao() {
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);


    }
}
