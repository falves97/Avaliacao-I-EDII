package strategy;

import java.security.SecureRandom;
import java.util.List;

public class QuickSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {
    @Override
    public void sortAscend(List<T> list) {
        quick(list, 0, list.size() - 1, true);
    }

    @Override
    public void sortDescend(List<T> list) {
        quick(list, 0, list.size() - 1, false);
    }

    private static <T extends Comparable<T>> void quick(List<T> list, int init, int last, boolean isAscend) {
        int pivot;

        if (init < last){
            pivot = randomPartition(list, init, last, isAscend);
            quick(list, init, pivot - 1, isAscend);
            quick(list, pivot + 1, last, isAscend);
        }
    }

    private static <T extends Comparable<T>> int randomPartition(List<T> list, int init, int last, boolean isAscend) {
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
            if (isAscend) {
                if (list.get(i).compareTo(pivot) <= 0) {
                    i++;
                }
                else if (list.get(j).compareTo(pivot) > 0) {
                    j--;
                }
                else {
                    exchange(list, i, j);
                    i++;
                    j--;
                }
            }
            else {
                if (list.get(i).compareTo(pivot) > 0) {
                    i++;
                }
                else if (list.get(j).compareTo(pivot) <= 0) {
                    j--;
                }
                else {
                    exchange(list, i, j);
                    i++;
                    j--;
                }
            }

        }
        list.set(init, list.get(j));
        list.set(j, pivot);

        return j;
    }

    private static <T> void exchange(List<T> list, int i, int j) {
        T aux = list.get(i);
        list.set(i, list.get(j));
        list.set(j, aux);
    }
}
