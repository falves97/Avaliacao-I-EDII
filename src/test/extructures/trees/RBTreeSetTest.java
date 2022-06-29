package test.extructures.trees;

import extructures.trees.RBTreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RBTreeSetTest {

    private RBTreeSet<Integer> integerRBTreeSet;

    @BeforeEach
    public void init() {
        integerRBTreeSet = new RBTreeSet<>();
    }

    @Test
    public void testInsercao() {
        integerRBTreeSet.add(2);
        Assertions.assertEquals(integerRBTreeSet.size(), 1);
        boolean validation = integerRBTreeSet.propertiesValidation();
        Assertions.assertTrue(validation);
    }

    @Test
    public void testInsercaoDoisNosVermelhosEsquerdoComTioPreto(){
        integerRBTreeSet.add(3);
        integerRBTreeSet.add(2);
        integerRBTreeSet.add(1);
        Assertions.assertTrue(integerRBTreeSet.propertiesValidation());
    }

    @Test
    public void testInsercaoDoisNosVermelhosComTioVermelho(){
        integerRBTreeSet.add(3);
        integerRBTreeSet.add(2);
        integerRBTreeSet.add(4);
        integerRBTreeSet.add(1);

        Assertions.assertTrue(integerRBTreeSet.propertiesValidation());
    }

    @Test
    public void testInsercaoNetoDireitoVermelhoFilhoEsquerdoVermelhoComTioPreto(){
        integerRBTreeSet.add(4);
        integerRBTreeSet.add(2);
        integerRBTreeSet.add(3);

        Assertions.assertTrue(integerRBTreeSet.propertiesValidation());
    }

    @Test
    public void testInsercaoDoisNosVermelhosDireitaComTioPreto(){
        integerRBTreeSet.add(3);
        integerRBTreeSet.add(4);
        integerRBTreeSet.add(5);

        Assertions.assertTrue(integerRBTreeSet.propertiesValidation());
    }

    @Test
    public void testInsercaoDoisNosVermelhosDirataComTioVermelho(){
        integerRBTreeSet.add(3);
        integerRBTreeSet.add(2);
        integerRBTreeSet.add(4);
        integerRBTreeSet.add(5);

        Assertions.assertTrue(integerRBTreeSet.propertiesValidation());
    }

    @Test
    public void testInsercaoNetoEsquerdoVermelhoFilhoDireitoVermelhoComTioPreto(){
        integerRBTreeSet.add(4);
        integerRBTreeSet.add(6);
        integerRBTreeSet.add(5);

        Assertions.assertTrue(integerRBTreeSet.propertiesValidation());
    }

    @Test
    public void testRemocao() {
        integerRBTreeSet.add(1);
        integerRBTreeSet.add(2);
        integerRBTreeSet.add(3);
        integerRBTreeSet.add(4);
        integerRBTreeSet.add(5);
        integerRBTreeSet.add(6);
        integerRBTreeSet.add(7);
        integerRBTreeSet.add(8);
        integerRBTreeSet.add(9);
        integerRBTreeSet.add(10);

        integerRBTreeSet.remove(6);
        integerRBTreeSet.remove(2);

        Assertions.assertTrue(integerRBTreeSet.propertiesValidation());

    }


}
