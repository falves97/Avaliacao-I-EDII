package strategy;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy<T extends Comparable<T>> {
    void sort(List<T> list);

    void sort(List<T> list, SortOrder sortOrder);

    void sort(List<T> list, Comparator<T> comparator);
}
