package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface MultiplicativeGroup<E extends MultiplicativeGroup<E>> {

    /**
     * Returns the result of multiplying the elements
     */
    E multiply(E element);

    /**
     * Returns the multiplicative identity of the field
     * <p> element * multiplicative identity = element <\p>
     */
    E one();

    /**
     * Inverts the element such that
     * <p> element * invert(element) = multiplicative identity <\p>
     */
    E invert();

    /**
     * Returns the result of dividing the elements
     */
    default E divide(E b) {
        return multiply(b.invert());
    }

}
