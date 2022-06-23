package extructures;

import java.util.function.Consumer;

public interface ExtructuriesStrategy <T> {
    void add(T data);
    T find(T data);
    T remove(T data);

    void forEach(Consumer<T> consumer);
}
