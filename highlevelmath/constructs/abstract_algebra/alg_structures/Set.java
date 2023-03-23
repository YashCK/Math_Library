package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface Set<E extends Set<E>> {

    /**
     * Returns a copy of the element
     */
    E copy();

}
