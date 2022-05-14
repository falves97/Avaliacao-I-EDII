package strategy;

import java.util.List;

public class SelectSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {
    @Override
    public void sortAscend(List<T> list) {
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
            todos à sua direita, até achar o menor elemento comparado com ele
             */
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(key)) < 0) {
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
    public void sortDescend(List<T> list) {
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
            todos à sua direita, até achar o maior elemento comparado com ele
             */
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(key)) > 0) {
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
}
