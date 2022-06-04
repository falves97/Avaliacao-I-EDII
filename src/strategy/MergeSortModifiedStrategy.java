package strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortModifiedStrategy<T extends Comparable<T>> extends MergeSortStrategy<T> {
    @Override
    protected List<T> merge(List<T> arrayA, List<T> arrayB, int init, int last, Comparator<T> comparator) {
        int midle;

        if (arrayB == null) {
            arrayB = new ArrayList<>(arrayA);
        }

        if (arrayB.size() > 15) {
            midle = (init + last) / 2;
            merge(arrayA, arrayB, init, midle, comparator);
            merge(arrayA, arrayB, midle + 1, last, comparator);
            super.toMerge(arrayA, arrayB, init, midle, last, comparator);
        } else {
            InsertSortStrategy<T> insertSortStrategy = new InsertSortStrategy<>();
            insertSortStrategy.sort(arrayB, comparator);
        }

        return arrayB;
    }

    @Override
    public void sort(List<T> list) {
        List<T> listAux = merge(list, null, 0, list.size() - 1, Comparator.naturalOrder());
        list.clear();
        list.addAll(listAux);
    }

    @Override
    public void sort(List<T> list, SortOrder sortOrder) {
        if (sortOrder == SortOrder.ACSCENDING) {
            sort(list);
        } else {
            List<T> listAux = merge(list, null, 0, list.size() - 1, Comparator.reverseOrder());
            list.clear();
            list.addAll(listAux);
        }
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        List<T> listAux = merge(list, null, 0, list.size() - 1, comparator);
        list.clear();
        list.addAll(listAux);
    }
}
