package extructures;

import java.util.*;

/*
 * Deve conter uma lista(ArrayList<T>)
 *
 * Deve usar o métedo da multiplicação como função hash: h(K) = Math.floor(m * ((k * a) % 1))
 * onde a = Math.sqrt(3) - 1
 */
public class HashTable<K extends Comparable<K>, V> implements Map<K, V> {

    private int capacity;
    private Item<K, V>[] list;
    private int colisions;
    private int size;

    public HashTable() {
        size = 0;
        colisions = 3;
        capacity = 100;
        list = new Item[capacity];
    }

    public HashTable(int capacity) {
        size = 0;
        colisions = 3;
        this.capacity = capacity;
        list = new Item[capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public int getColisions() {
        return colisions;
    }

    private int hashFuncition(K key) {
        int numKey = Math.abs(key.hashCode());
        double a = Math.sqrt(3) - 1;

        return (int) Math.floor(capacity * ((numKey * a) % 1));
    }

    private void rePutAll(Item<K, V>[] oldList) {
        Arrays.asList(oldList).forEach(item -> put(item.getKey(), item.getValue()));
    }

    private void grow() {
        // Reseta o númeor de elementos, pois irá readicionalos
        size = 0;
        // Duplica a capacidade
        capacity *= 2;
        // Reseta para o valor inicial de colisões para manter um desempenho razoável
        colisions = 3;
        Item<K, V>[] oldList = list;
        list = new Item[capacity];

        rePutAll(oldList);
    }

    private int insert(K k, int colisions) {
        // Verifica se o número de colisões já superou o máximo pré-definido e
        // se há pelo menos 80% da capacidade preenxida ou
        // se a tabela está cheia
        if ((colisions > this.colisions && size >= 0.8 * capacity) || size == capacity) {
            //dobra o tamanho
            grow();
            //reinicia a inserção do elemento na nova tabela, por tanto reseta as colisões
            return insert(k, 0);
        } else if (colisions > this.colisions && size < 0.8 * capacity) {
            this.colisions++;
        }


        int pos = hashFuncition(k) + colisions;

        // se a posição recebida for maior que a capacidade devido ao incremento de colisoçẽos
        // retorna as primeiras posições
        if (pos > capacity - 1) {
            pos -= capacity;
        }

        if (list[pos] == null || list[pos].getKey().equals(k)) {
            return pos;
        }

        return insert(k, colisions + 1);

    }

    private int findPositionKey(K key, int colisions) throws IndexOutOfBoundsException {
        if (colisions > this.colisions) {
            throw new IndexOutOfBoundsException();
        }

        int pos = hashFuncition(key) + colisions;

        // se a posição recebida for maior que a capacidade devido ao incremento de colisoçẽos
        // retorna as primeiras posições
        if (pos > capacity - 1) {
            pos -= capacity;
        }

        if (list[pos] != null) {
            if (list[pos].getKey().equals(key)) {
                return pos;
            }
        }

        return findPositionKey(key, colisions + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public boolean containsKey(Object o) {
        try {
            int pos = findPositionKey((K) o, 0);
            return list[pos] != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public V get(Object o) throws IndexOutOfBoundsException {
        int pos = findPositionKey((K) o, 0);

        return list[pos].getValue();
    }

    @Override
    public V put(K k, V v) {
        int pos = insert(k, 0);

        // Só incrementa caso não esteja atualizando uma chave já existente
        if (list[pos] == null){
            size++;
        }

        list[pos] = new Item<>(k, v);
        return v;
    }

    @Override
    public V remove(Object o) throws IndexOutOfBoundsException {
        int pos = findPositionKey((K) o, 0);
        V value = list[pos].getValue();
        list[pos] = null;
        size--;

        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();

        Arrays.asList(list)
                .forEach(item -> {
                    if (item != null) {
                    entries.add(item);
                }
        });

        return entries;
    }
}
