package highlevelmath.constructs.util;

@FunctionalInterface
public interface ArrFactory<T> {
    public T[] create(int length);
}
