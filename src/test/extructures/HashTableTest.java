package test.extructures;

import extructures.HashTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashTableTest {

    private HashTable<String, Integer> map;

    @BeforeEach
    public void init() {
        map = new HashTable<>(3);
    }

    @Test
    public void testInstaciacaoEQuantidadeElementos() {
        Assertions.assertEquals(0, map.size());
    }

    @Test
    public void testInsercao() {
        map.put("name", 1);
        map.put("nome", 2);
        Assertions.assertEquals(2, map.size());
    }

    @Test
    public void testInserecaoDeChavesRepetidas() {
        map.put("name", 1);
        map.put("name", 2);

        Assertions.assertEquals(map.get("name"), 2);
        Assertions.assertEquals(map.size(), 1);
    }

    @Test
    public void testProcurarElemento() {
        map.put("name", 1);
        map.put("nome", 2);

        Assertions.assertEquals(map.get("name"), 1);
        Assertions.assertEquals(map.get("nome"), 2);
    }

    @Test
    public void testDobraOTamanhoDaTabela() {
        map.put("name", 1);
        map.put("nome", 2);
        map.put("nume", 3);
        map.put("neme", 4);

        Assertions.assertEquals(map.size(), 4);
        Assertions.assertEquals(map.getCapacity(), 6);
        Assertions.assertEquals(map.get("nume"), 3);
    }

    @Test
    public void testRemocao() {
        map.put("name", 1);
        map.put("nome", 2);
        map.put("nume", 3);
        map.put("neme", 4);

        Assertions.assertEquals(map.size(), 4);
        Assertions.assertEquals(map.getCapacity(), 6);
        Assertions.assertEquals(map.get("nume"), 3);

        int resp = map.remove("name");

        Assertions.assertEquals(resp, 1);
        Assertions.assertEquals(map.size(), 3);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> map.get("name"));
    }
}
