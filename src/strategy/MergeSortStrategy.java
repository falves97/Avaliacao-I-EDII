package strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {
    protected void toMerge(List<T> arrayA, List<T> arrayB, int init, int middle, int last, Comparator<T> comparator) {
        int initA;
        int initB;
        int posFree;
        int numElem;

        initA = init;
        initB = middle + 1;
        posFree = init;
        numElem = last - init + 1;

        while (initA <= middle && initB <= last) {
            if (comparator.compare(arrayA.get(initA), arrayA.get(initB)) <= 0) {
                arrayB.set(posFree, arrayA.get(initA));
                initA++;
            } else {
                arrayB.set(posFree, arrayA.get(initB));
                initB++;
            }


            posFree++;
        }

        while (initA <= middle) {
            arrayB.set(posFree, arrayA.get(initA));
            posFree++;
            initA++;
        }

        while (initB <= last) {
            arrayB.set(posFree, arrayA.get(initB));
            posFree++;
            initB++;
        }

        for (int i = 0; i < numElem; i++, last--) {
            arrayA.set(last, arrayB.get(last));
        }
    }

    protected List<T> merge(List<T> arrayA, List<T> arrayB, int init, int last, Comparator<T> comparator) {
        int midle;

        if (arrayB == null) {
            arrayB = new ArrayList<>(arrayA);
        }

        if (init < last) {
            midle = (init + last) / 2;
            merge(arrayA, arrayB, init, midle, comparator);
            merge(arrayA, arrayB, midle + 1, last, comparator);
            toMerge(arrayA, arrayB, init, midle, last, comparator);
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
