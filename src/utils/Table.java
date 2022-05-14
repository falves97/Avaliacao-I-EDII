package utils;

import java.util.*;

public class Table<K extends Comparable<K>, V>  implements Map<K, V> {
    private List<Item<K, V>> items;

    public Table() {
        items = new ArrayList<>();
    }

    public List<Item<K, V>> getItems() {
        return items;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        TreeSet<K> keys = (TreeSet<K>) this.keySet();

        return keys.contains(o);
    }

    @Override
    public boolean containsValue(Object o) {
        ArrayList<V> values = (ArrayList<V>) this.values();

        return values.contains(o);
    }

    @Override
    public V get(Object o) {
        for (Item<K, V> item : items) {
            if (item.getKey().compareTo((K) o) == 0) {
                return item.getValue();
            }
        }

        return null;
    }

    public V put(K key, V value) {
        this.remove(key);

        items.add(new Item<>(key, value));
        return value;
    }

    @Override
    public V remove(Object o) {
        V value = null;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getKey().compareTo((K) o) == 0) {
                value = items.get(i).getValue();
                items.remove(i);
            }
        }

        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        Set<? extends Entry<? extends K, ? extends V>> entrys = map.entrySet();

        for (Entry<? extends K, ? extends V> entry : entrys) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < items.size(); i++) {
            items.remove(i);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();

        for (Item<K, V> item : items) {
            keys.add(item.getKey());
        }

        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();

        for (Item<K, V> item : items) {
            values.add(item.getValue());
        }

        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> pairs = new LinkedHashSet<>();

        for (Item<K, V> item : items) {
            pairs.add(item);
        }

        return pairs;
    }

    @Override
    public String toString() {
        return "Table{" +
                "items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table<?, ?> table = (Table<?, ?>) o;
        return getItems().equals(table.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItems());
    }
}