package extructures;

import java.util.function.Consumer;

public interface ExtructuriesStrategy <T> {
    void insert(T data);
    T find(T data);
    T delete(T data);

    void forEach(Consumer<T> consumer);
}
