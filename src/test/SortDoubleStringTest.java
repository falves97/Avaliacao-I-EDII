package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.*;
import utils.RandomString;
import extructures.Table;

import java.security.SecureRandom;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortDoubleStringTest {
    private static Table<String, Double> firstTable;
    private static Table<String, Double> secondTable;
    private static SecureRandom random;

    private void loadElements(Table<String, Double> firstTable, Table<String, Double> secondTable) {
        for (int i = 0; i < 10; i++) {
            String key = RandomString.randomString(10);
            Double value = random.nextDouble() * 10;

            firstTable.put(key, value);
            secondTable.put(key, value);
        }
    }

    public void loadTest(SortOrder sortOrder) {
        if (sortOrder == SortOrder.ACSCENDING) {
            firstTable.getItems().sort(Comparator.naturalOrder());
        } else {
            firstTable.getItems().sort(Collections.reverseOrder());
        }
        secondTable.sort(sortOrder);

        assertEquals(firstTable.getItems(), secondTable.getItems());
    }

    @BeforeEach
    public void init() {
        firstTable = new Table<>();
        secondTable = new Table<>();
        random = new SecureRandom();

        loadElements(firstTable, secondTable);
    }

    @Test
    public void testOrdenarSelectAsend() {
        loadTest(SortOrder.ACSCENDING);
    }

    @Test
    public void testOrdenarSelectDescend() {
        loadTest(SortOrder.DESCENDING);
    }

    @Test
    public void testOrdenarSelectModifiedAscend() {
        secondTable.setSortStrategy(new SelectSortModifiedStrategy<>());
        loadTest(SortOrder.ACSCENDING);
    }

    @Test
    public void testOrdenarSelectModifiedDescend() {
        secondTable.setSortStrategy(new SelectSortModifiedStrategy<>());
        loadTest(SortOrder.DESCENDING);
    }

    @Test
    public void testOrdenarQuickSortAscend() {
        secondTable.setSortStrategy(new QuickSortStrategy<>());
        loadTest(SortOrder.ACSCENDING);
    }

    @Test
    public void testOrdenarQuickSortDescend() {
        secondTable.setSortStrategy(new QuickSortStrategy<>());
        loadTest(SortOrder.DESCENDING);
    }

    @Test
    public void testOrdenarMergeSortAscend() {
        secondTable.setSortStrategy(new MergeSortStrategy<>());
        loadTest(SortOrder.ACSCENDING);
    }

    @Test
    public void testOrdenarMergeSortDescend() {
        secondTable.setSortStrategy(new MergeSortStrategy<>());
        loadTest(SortOrder.DESCENDING);
    }

    @Test
    public void testOrdenarMergeModifiedSortAscend() {
        secondTable.setSortStrategy(new MergeSortModifiedStrategy<>());
        loadTest(SortOrder.ACSCENDING);
    }

    @Test
    public void testOrdenarMergeModifiedSortDescend() {
        secondTable.setSortStrategy(new MergeSortModifiedStrategy<>());
        loadTest(SortOrder.DESCENDING);
    }

    @Test
    public void testOrdenarHeapSortAscend() {
        secondTable.setSortStrategy(new HeapSortStrategy<>());
        loadTest(SortOrder.ACSCENDING);
    }

    @Test
    public void testOrdenarHeapSortDescend() {
        secondTable.setSortStrategy(new HeapSortStrategy<>());
        loadTest(SortOrder.DESCENDING);
    }

}
