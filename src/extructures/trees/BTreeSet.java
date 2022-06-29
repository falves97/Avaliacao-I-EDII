package extructures.trees;

import extructures.ExtructuriesStrategy;
import extructures.trees.node.BTPage;
import extructures.trees.node.BTNodePsition;

import java.util.function.Consumer;

public class BTreeSet<T extends Comparable<T>> implements ExtructuriesStrategy<T> {
    private final int order;
    private BTPage<T> root;

    public BTreeSet() {
        this(2);
    }

    public BTreeSet(int order) {
        if (order < 2) {
            order = 2;
        }

        this.order = order;
        root = new BTPage<>();
    }

    private BTNodePsition<T> btSearch(BTPage<T> page, T data) {
        int i = 0;
        BTNodePsition<T> nodePsition;

        while (i < page.size() && data.compareTo(page.getData(i)) > 0) {
            i++;
        }

        if (i < page.size() && data.compareTo(page.getData(i)) == 0) {
            nodePsition = new BTNodePsition<>();
            nodePsition.setPage(page);
            nodePsition.setPosition(i);

            return nodePsition;
        } else if (page.isLeaf()) {
            return null;
        } else {
            return btSearch(page.getKey(i), data);
        }
    }

    private void split(BTPage<T> page, int pos) {
        BTPage<T> pageA = new BTPage<>();
        BTPage<T> pageB = page.getKey(pos);
        pageA.setLeaf(pageB.isLeaf());

        for (int i = 0; i < order - 1; i++) {
            pageA.addData(pageB.getData(i + order));
            pageB.removeData(i + order);
        }

        if (!pageB.isLeaf()) {
            for (int i = 0; i < order; i++) {
                pageA.addKey(pageB.getKey(i + order));
                pageB.removeKey(i + order);
            }
        }

        page.addKey(pos + 1, pageA);

        page.addData(pos, pageB.getData(pageB.size() - 1));
        pageB.removeData(pageB.size() - 1);
    }

    private void insertNoFull(BTPage<T> page, T data) {
        int i = page.size();


        while (i > 0 && data.compareTo(page.getData(i - 1)) < 0) {
            i--;
        }

        if (page.size() > 0){
            i--;
        }

        if (page.isLeaf()) {
            page.addData(i + 1, data);
        } else {
            i++;

            if (page.getKey(i).size() == 2 * order - 1) {
                split(page, i);

                if (data.compareTo(page.getData(i)) > 0) {
                    i++;
                }
            }

            insertNoFull(page.getKey(i), data);
        }
    }

    private void insert(T data) {
        if (root.size() == 2 * order - 1) {
            BTPage<T> page = root;
            root = new BTPage<>();
            root.setLeaf(false);
            root.addKey(page);
            split(root, 0);
            insertNoFull(root, data);
        } else {
            insertNoFull(root, data);
        }
    }

    @Override
    public void add(T data) {
        insert(data);
    }

    @Override
    public T find(T data) {
        return null;
    }

    @Override
    public T remove(T data) {
        return null;
    }

    @Override
    public void forEach(Consumer<T> consumer) {

    }
}
