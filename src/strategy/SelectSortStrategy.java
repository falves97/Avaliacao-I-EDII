package strategy;

import java.util.Comparator;
import java.util.List;

public class SelectSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {
    private void selectSort(List<T> list, Comparator<T> comparator) {
        T aux;
        int key;

        /*
        laço percorre do início ao penúltimo elemento da lista para comparara com
        os elementos à direita de i
         */

        for (int i = 0; i < list.size() - 1; i++) {
            key = i;

            /*
            após key receber i, o segundo laço compara o elemento de índice i com
            todos à sua direita, até achar o menor ou o maior elemento, dependendo da do sortOerder, comparado com ele
             */
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(j), list.get(key)) < 0) {
                    key = j;
                }
            }

            /*
            após achar o índice do menor elemento comparado com o elemento de índice i,
            ele é colocado na posição i, fazendo assim que todos os elementos à esquerda de
            i fiquem sempre ordenados
             */
            aux = list.get(key);
            list.set(key, list.get(i));
            list.set(i, aux);
        }
    }

    @Override
    public void sort(List<T> list) {
        selectSort(list, Comparator.naturalOrder());
    }

    @Override
    public void sort(List<T> list, SortOrder sortOrder) {
        if (sortOrder == SortOrder.ACSCENDING) {
            sort(list);
        } else {
            selectSort(list, Comparator.reverseOrder());
        }
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        selectSort(list, comparator);
    }
}
