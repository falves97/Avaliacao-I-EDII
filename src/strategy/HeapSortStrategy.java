package strategy;

import java.util.ArrayList;
import java.util.List;

public class HeapSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {
    @Override
    public void sortAscend(List<T> list) {
        heapSort(list, true);
    }

    @Override
    public void sortDescend(List<T> list) {
        heapSort(list, false);
    }

    private static int left(int i) {
        return 2 * i;
    }

    private static int right(int i) {
        return 2 * i + 1;
    }

    private void maxHeapfy(List<T> list, int len, int i) {
        int l = left(i);
        int r = right(i);
        int max;

        if (l <= len && list.get(l).compareTo(list.get(i)) > 0) {
            max = l;
        } else {
            max = i;
        }

        if (r <= len && list.get(r).compareTo(list.get(max)) > 0) {
            max = r;
        }

        if (max != i) {
            T aux = list.get(i);
            list.set(i, list.get(max));
            list.set(max, aux);

            maxHeapfy(list, len, max);
        }
    }

    private void minHeapfy(List<T> list, int len, int i) {
        int l = left(i);
        int r = right(i);
        int max;

        if (l <= len && list.get(l).compareTo(list.get(i)) < 0) {
            max = l;
        } else {
            max = i;
        }

        if (r <= len && list.get(r).compareTo(list.get(max)) < 0) {
            max = r;
        }

        if (max != i) {
            T aux = list.get(i);
            list.set(i, list.get(max));
            list.set(max, aux);

            minHeapfy(list, len, max);
        }
    }

    private void buildHeapfy(List<T> list, int len, boolean isAscned) {
        for (int i = len / 2; i >= 1; i--) {
            if (isAscned) {
                maxHeapfy(list, len, i);
            } else {
                minHeapfy(list, len, i);
            }
        }
    }

    private void heapSort(List<T> list, boolean isAscend) {
        List<T> heapList = new ArrayList<>();
        heapList.add(0, null);
        heapList.addAll(list);

        int len = list.size();
        buildHeapfy(heapList, len, isAscend);

        for (int i = len; i >= 2; i--) {
            T aux = heapList.get(1);
            heapList.set(1, heapList.get(i));
            heapList.set(i, aux);

            len = i - 1;

            if (isAscend){
                maxHeapfy(heapList, len, 1);
            } else  {
                minHeapfy(heapList, len, 1);
            }

        }

        heapList.remove(0);
        list.clear();
        list.addAll(heapList);
    }
}
