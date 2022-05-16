package test;

import strategy.*;
import utils.Item;
import utils.RandomString;
import utils.Table;

import java.security.SecureRandom;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        Table<Integer, String> lista = new Table<>();
        SecureRandom random = new SecureRandom();
        HeapSortStrategy<Item<Integer, String>> sort = new HeapSortStrategy<>();

        lista.put(16, RandomString.randomString(2));
        lista.put(14, RandomString.randomString(2));
        lista.put(10, RandomString.randomString(2));
        lista.put(8, RandomString.randomString(2));
        lista.put(7, RandomString.randomString(2));
        lista.put(9, RandomString.randomString(2));
        lista.put(3, RandomString.randomString(2));
        lista.put(2, RandomString.randomString(2));
        lista.put(4, RandomString.randomString(2));
        lista.put(1, RandomString.randomString(2));

        System.out.println(lista);
        sort.sortDescend(lista.getItems());
        System.out.println(lista);
    }
}
