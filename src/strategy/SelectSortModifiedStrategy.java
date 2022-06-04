package strategy;

import java.util.Comparator;
import java.util.List;

public class SelectSortModifiedStrategy<T extends Comparable<T>> implements SortStrategy<T> {

    private void exchange(List<T> list, int indexMin, int indexMax, T min, T max, int init, int last) {
        list.set(indexMin, list.get(init));
        list.set(init, min);

        /*Caso onde o valor contido no índece do máximo pode ter sido trocado na primeira troca*/
        if (indexMax == init) {
            indexMax = indexMin;
        }

        /*Troca o máximo com o valor na posição final*/
        list.set(indexMax, list.get(last));
        list.set(last, max);
    }

    private void selectSortModified(List<T> list, Comparator<T> comparator) {
        for (int i = 0; i < list.size() / 2; i++) {

            int indexMin = i;
            int indexMax = list.size() - 1 - i;

            T min = list.get(indexMin);
            T max = list.get(indexMax);

            for (int j = i; j < list.size() - i; j += 2) {
                int indexA, indexB;

                if (comparator.compare(list.get(j), list.get(j + 1)) < 0) {
                    indexA = j;
                    indexB = j + 1;
                } else {
                    indexA = j + 1;
                    indexB = j;
                }

                if (comparator.compare(list.get(indexA), min) < 0) {
                    min = list.get(indexA);
                    indexMin = indexA;
                }

                if (comparator.compare(list.get(indexB), max) > 0) {
                    max = list.get(indexB);
                    indexMax = indexB;
                }
            }

            int last = list.size() - i - 1;

            /*Troca o mínimo com o valor na posição inicial*/
            exchange(list, indexMin, indexMax, min, max, i, last);
        }
    }

    @Override
    public void sort(List<T> list) {
        selectSortModified(list, Comparator.naturalOrder());
    }

    @Override
    public void sort(List<T> list, SortOrder sortOrder) {
        if (sortOrder == SortOrder.ACSCENDING) {
            sort(list);
        } else {
            selectSortModified(list, Comparator.reverseOrder());
        }
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        selectSortModified(list, comparator);
    }

}
