package highlevelmath.constructs.util;

@FunctionalInterface
public interface MatxFactory<V, M> {
    public M create(V[] input, boolean isColumn);
}