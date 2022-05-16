package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.*;
import utils.Item;
import utils.RandomString;
import utils.Table;

import java.security.SecureRandom;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortDoubleStringTest {
    private static Table<String, Double> elementsThousand;
    private static Table<String, Double> copyElementsThousand;
    private static Table<String, Double> elementsHundredThousand;
    private static Table<String, Double> copyElementsHundredThousand;
    private static Table<String, Double> elementsMillion;
    private static Table<String, Double> copyElementsMillion;
    private static SecureRandom random;

    private static Map<String, SortStrategy> sorts;
    @BeforeAll
    public static void init() {
        elementsThousand = new Table<>();
        copyElementsThousand = new Table<>();
        elementsHundredThousand = new Table<>();
        copyElementsHundredThousand = new Table<>();
        elementsMillion = new Table<>();
        copyElementsMillion =new Table<>();


        int hundredThousand = 100000;
        int million = 1000000;

        random = new SecureRandom();

        sorts = new LinkedHashMap<>();
        sorts.put("Insert", new InsertSortStrategy());
        sorts.put("Select", new InsertSortStrategy());
        sorts.put("Select Modified", new InsertSortStrategy());
        sorts.put("Quick", new InsertSortStrategy());
        sorts.put("Heap", new InsertSortStrategy());
        sorts.put("Merge", new InsertSortStrategy());
        sorts.put("Merge Modified", new InsertSortStrategy());

    }

    private static void loadHundredThousand(int hundredThousand) {
        for (int i = 0; i < hundredThousand; i++) {
            String key = RandomString.randomString(10);
            Double value = random.nextDouble() * hundredThousand;

            elementsHundredThousand.put(key, value);
            copyElementsHundredThousand.put(key,value);
        }
    }

    private static void loadElements(int num) {
        for (int i = 0; i < num; i++) {
            String key = RandomString.randomString(10);
            Double value = random.nextDouble() * num;

            elementsThousand.put(key, value);
            copyElementsThousand.put(key,value);
        }
    }

    public static boolean testOrder(List<Item<String, Double>> list, boolean isAscend) {
        if (list.size() <= 1) {
            return true;
        }

        for (int i = 1; i < list.size(); i++) {
            if (isAscend) {
                if (list.get(i).compareTo(list.get(i - 1)) < 0){
                    return false;
                }
            } else {
                if (list.get(i).compareTo(list.get(i - 1)) > 0){
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void testOrdenarAscendThousand() {
        loadElements(1000);

        System.out.printf("%-20s%6s%n","Algoritmo", "Tempo");
        sorts.forEach((name, sort) -> {
            long instantBegin = System.currentTimeMillis();

            sort.sortAscend(copyElementsThousand.getItems());

            long instantEnd = System.currentTimeMillis();
            System.out.printf("%-20s%6d%n",name, (instantEnd - instantBegin));

            assertTrue(testOrder(copyElementsThousand.getItems(), true), () -> "Teste na ordem ascendente falhou");
            copyElementsThousand.clear();
            copyElementsThousand.putAll(elementsThousand);
        });
    }
}
