package extructures.trees.node;

public class BTNodePsition<T extends Comparable<T>> {
    private int position;
    private BTPage<T> page;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public BTPage<T> getPage() {
        return page;
    }

    public void setPage(BTPage<T> page) {
        this.page = page;
    }
}
