package strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HeapSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {
    private static int left(int i) {
        return 2 * i;
    }

    private static int right(int i) {
        return 2 * i + 1;
    }

    private void maxHeapfy(List<T> list, int len, int i, Comparator<T> comparator) {
        int l = left(i);
        int r = right(i);
        int max;

        if (l <= len && comparator.compare(list.get(l), list.get(i)) > 0) {
            max = l;
        } else {
            max = i;
        }

        if (r <= len && comparator.compare(list.get(r), list.get(max)) > 0) {
            max = r;
        }

        if (max != i) {
            T aux = list.get(i);
            list.set(i, list.get(max));
            list.set(max, aux);

            maxHeapfy(list, len, max, comparator);
        }
    }

    private void buildHeapfy(List<T> list, int len, Comparator<T> comparator) {
        for (int i = len / 2; i >= 1; i--) {
            maxHeapfy(list, len, i, comparator);
        }
    }

    private void heapSort(List<T> list, Comparator<T> comparator) {
        List<T> heapList = new ArrayList<>();
        heapList.add(0, null);
        heapList.addAll(list);

        int len = list.size();
        buildHeapfy(heapList, len, comparator);

        for (int i = len; i >= 2; i--) {
            T aux = heapList.get(1);
            heapList.set(1, heapList.get(i));
            heapList.set(i, aux);

            len = i - 1;

                maxHeapfy(heapList, len, 1, comparator);


        }

        heapList.remove(0);
        list.clear();
        list.addAll(heapList);
    }

    @Override
    public void sort(List<T> list) {
        heapSort(list, Comparator.naturalOrder());
    }

    @Override
    public void sort(List<T> list, SortOrder sortOrder) {
        if (sortOrder == SortOrder.ACSCENDING) {
            sort(list);
        } else {
            heapSort(list, Comparator.reverseOrder());
        }
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        heapSort(list, comparator);
    }
}
