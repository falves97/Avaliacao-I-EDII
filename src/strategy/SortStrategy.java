package strategy;

import java.util.List;

public interface SortStrategy<T extends Comparable<T>> {
    public void sortAscend(List<T> list);
    public void sortDescend(List<T> list);
}
