package strategy;

import java.util.ArrayList;
import java.util.List;

public class MergeSortStrategy<T extends Comparable<T>> implements SortStrategy <T> {

    @Override
    public void sortAscend(List<T> list) {
        List<T> listAux= merge(list, null, 0, list.size() - 1, true);
        list.clear();
        list.addAll(listAux);
    }

    @Override
    public void sortDescend(List<T> list) {
        List<T> listAux= merge(list, null, 0, list.size() - 1, false);
        list.clear();
        list.addAll(listAux);
    }

    private  <T extends Comparable<T>> void toMerge(List<T> arrayA, List<T> arrayB, int init, int middle, int last, boolean isAscend) {
        int initA;
        int initB;
        int posFree;
        int numElem;

        initA = init;
        initB = middle + 1;
        posFree = init;
        numElem = last - init + 1;

        while (initA <= middle && initB <= last) {
            if (isAscend) {
                if (arrayA.get(initA).compareTo(arrayA.get(initB)) <= 0) {
                    arrayB.set(posFree, arrayA.get(initA));
                    initA++;
                }
                else {
                    arrayB.set(posFree, arrayA.get(initB));
                    initB++;
                }
            }
            else {
                if (arrayA.get(initA).compareTo(arrayA.get(initB)) > 0) {
                    arrayB.set(posFree, arrayA.get(initA));
                    initA++;
                }
                else {
                    arrayB.set(posFree, arrayA.get(initB));
                    initB++;
                }
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

    private <T extends Comparable<T>> List<T> merge(List<T> arrayA, List<T> arrayB, int init, int last, boolean isAscend) {
        int midle;

        if (arrayB == null) {
            arrayB = new ArrayList<>(arrayA);
        }

        if (init < last) {
            midle = (init + last) / 2;
            merge(arrayA, arrayB, init, midle, isAscend);
            merge(arrayA, arrayB, midle + 1, last, isAscend);
            toMerge(arrayA, arrayB, init, midle, last, isAscend);
        }

        return arrayB;
    }
}
