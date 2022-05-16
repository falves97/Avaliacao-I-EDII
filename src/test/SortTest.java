package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.*;
import utils.Item;
import utils.RandomString;
import utils.Table;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortTest {
    private static Table<String, Double> elements;
    private static Table<String, Double> copyElements;
    private static SecureRandom random;

    private static List<SortStrategy> sorts;
    @BeforeAll
    public static void init() {
        elements = new Table<>();
        copyElements = new Table<>();
        random = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            String key = RandomString.randomString(10);
            Double value = random.nextDouble() * 10;

            elements.put(key, value);
            copyElements.put(key,value);
        }

        sorts = Arrays.asList(
                new InsertSortStrategy(),
                new SelectSortStrategy(),
                new QuickSortStrategy(),
                new HeapSortStrategy()
        );
    }

    public static boolean testAscend(List<Item<String, Double>> list) {
        if (list.size() <= 1) {
            return true;
        }

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0){
                return false;
            }
        }

        return true;
    }

    @Test
    @DisplayName("Testar ordenação de chaves do tipo String e valores tipo do Double")
    public void testOrdenarChavesStringValoresDouble() {
        for (SortStrategy sort : sorts) {
            sort.sortAscend(copyElements.getItems());
            assertTrue(testAscend(copyElements.getItems()), () -> "Teste Falhou");
            copyElements.clear();
            copyElements.putAll(elements);
        }
    }
}
