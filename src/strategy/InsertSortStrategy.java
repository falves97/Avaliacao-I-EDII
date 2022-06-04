package strategy;


import java.util.Comparator;
import java.util.List;

public class InsertSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {

    private void insertSort(List<T> list, Comparator<T> comparator) {
        T elected;

        /*
          Percorre a lista a partir do segundo item
         */
        for (int i = 1; i < list.size(); i++) {
            elected = list.get(i);
            int j = i - 1;
            /*
              Compara o valor na posição i, passada pra elected, com todos os elementos a esquerda dele na lista.
              Colocando assim o valor de i na posição correta, avançando os valores que são maiores que ele pra frente.
             */

            while (j >= 0 && comparator.compare(list.get(j), elected) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }


            /*
              coloca selected no índice correto
             */
            list.set(j + 1, elected);
        }
    }

    @Override
    public void sort(List<T> list) {
        insertSort(list, Comparator.naturalOrder());
    }

    @Override
    public void sort(List<T> list, SortOrder sortOrder) {
        if (sortOrder == SortOrder.ACSCENDING) {
            sort(list);
        } else {
            insertSort(list, Comparator.reverseOrder());
        }
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        insertSort(list, comparator);
    }
}
