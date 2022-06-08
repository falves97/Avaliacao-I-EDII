package extructures;

import java.util.Map;
import java.util.Objects;

public class Item<K extends Comparable<K>, V> implements Comparable<Item<K, V>>, Map.Entry<K, V> {
    private K key;
    private V value;

    public Item(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }


    public Object setValue(Object value) {
        this.value = (V) value;
        return value;
    }

    /**
     * A forma de comparação padrão sempre será pela chave
     */
    @Override
    public int compareTo(Item<K, V> item) {
        return this.getKey().compareTo(item.getKey());
    }

    @Override
    public String toString() {
        return "Item{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item<?, ?> item = (Item<?, ?>) o;
        return getKey().equals(item.getKey()) && getValue().equals(item.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }
}