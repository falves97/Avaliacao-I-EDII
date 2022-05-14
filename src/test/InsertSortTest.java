package test;

import strategy.InsertSortStrategy;
import utils.Item;
import utils.Table;
import junit.framework.TestCase;

import java.util.Collections;

public class InsertSortTest extends TestCase {
    public void testOrdenarChavesStringValoresIntegers() {
        Table<String, Integer> names = new Table<>();
        Table<String, Integer> namesOrdered = new Table<>();
        InsertSortStrategy<Item<String, Integer>> sortable = new InsertSortStrategy<>();

        names.put("Marcos", 0);
        names.put("Alexandre", 1);
        names.put("Júlia", 2);
        names.put("Samira", 3);
        names.put("Fernando", 4);
        names.put("Lis", 5);
        names.put("Antônia", 6);
        names.put("Carlos", 7);
        names.put("Beatriz", 8);
        names.put("Cazemiro", 9);

        namesOrdered.put("Marcos", 0);
        namesOrdered.put("Alexandre", 1);
        namesOrdered.put("Júlia", 2);
        namesOrdered.put("Samira", 3);
        namesOrdered.put("Fernando", 4);
        namesOrdered.put("Lis", 5);
        namesOrdered.put("Antônia", 6);
        namesOrdered.put("Carlos", 7);
        namesOrdered.put("Beatriz", 8);
        namesOrdered.put("Cazemiro", 9);

        Collections.sort(namesOrdered.getItems());
        sortable.sortAscend(names.getItems());

        assertEquals(namesOrdered.getItems(), names.getItems());
    }
}
