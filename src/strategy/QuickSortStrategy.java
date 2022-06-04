package strategy;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;

public class QuickSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {
    private static <T> void exchange(List<T> list, int i, int j) {
        T aux = list.get(i);
        list.set(i, list.get(j));
        list.set(j, aux);
    }

    private static <T extends Comparable<T>> void quick(List<T> list, int init, int last, Comparator<T> comparator) {
        int pivot;

        if (init < last) {
            pivot = randomPartition(list, init, last, comparator);
            quick(list, init, pivot - 1, comparator);
            quick(list, pivot + 1, last, comparator);
        }
    }

    private static <T extends Comparable<T>> int randomPartition(List<T> list, int init, int last, Comparator<T> comparator) {
        SecureRandom secureRandom;
        int i;
        int j;
        int p;
        T pivot;

        secureRandom = new SecureRandom();
        p = secureRandom.nextInt((last + 1) - init) + init;
        exchange(list, init, p);
        pivot = list.get(init);
        i = init + 1;
        j = last;

        while (i <= j) {
            if (comparator.compare(list.get(i), pivot) <= 0) {
                i++;
            } else if (comparator.compare(list.get(j), pivot) > 0) {
                j--;
            } else {
                exchange(list, i, j);
                i++;
                j--;
            }

        }
        list.set(init, list.get(j));
        list.set(j, pivot);

        return j;
    }

    @Override
    public void sort(List<T> list) {
        quick(list, 0, list.size() - 1, Comparator.naturalOrder());
    }

    @Override
    public void sort(List<T> list, SortOrder sortOrder) {
        if (sortOrder == SortOrder.ACSCENDING) {
            sort(list);
        } else {
            quick(list, 0, list.size() - 1, Comparator.reverseOrder());
        }
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        quick(list, 0, list.size() - 1, comparator);
    }

}
