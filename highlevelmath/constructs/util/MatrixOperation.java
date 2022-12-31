package highlevelmath.constructs.util;
/**
 * An Interface that allows you to define an operation on two values within a Matrix
 */
@FunctionalInterface
public interface MatrixOperation<T> {
    T operation(T first, T second);
}
