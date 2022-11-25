package highlevelmath.constructs;
/**
 * An Interface that allows you to define an operation on two values within a Matrix
 */
@FunctionalInterface
public interface MatrixOperation {
    double operation(double first, double second);
}
