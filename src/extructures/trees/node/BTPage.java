package extructures.trees.node;

import java.util.ArrayList;
import java.util.List;

public class BTPage<T extends Comparable<T>> {

    // Valores
    private List<T> datas;

    // Filhos
    private List<BTPage<T>> keys;
    private boolean leaf;

    public BTPage() {
        keys = new ArrayList<>();
        datas = new ArrayList<>();
        leaf = true;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int size() {
        return datas.size();
    }

    public T getData(int i) {
        return datas.get(i);
    }

    public void addData(T data) {
        datas.add(data);
    }

    public void addData(int i, T data) {
        if (i >= datas.size()) {
            datas.add(data);
        } else {
            datas.add(i, data);
        }
    }


    public BTPage<T> getKey(int i) {
        return keys.get(i);
    }

    public void addKey(BTPage<T> key) {
        keys.add(key);
    }

    public void addKey(int i, BTPage<T> key) {
        if (i >= key.size()) {
            keys.add(key);
        } else {
            keys.add(i, key);
        }
    }

    public void removeKey(int i) {
        keys.remove(i);
    }

    public void removeData(int i) {
        datas.remove(i);
    }
}
