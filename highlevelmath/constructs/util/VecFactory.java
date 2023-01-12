package highlevelmath.constructs.util;

@FunctionalInterface
public interface VecFactory<T, V> {
    public V create(T[] input);
}
