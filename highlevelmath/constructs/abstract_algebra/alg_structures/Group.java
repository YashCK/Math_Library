package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface Group<E> {

    E multiply(E first, E second);

    /**
     * Returns the multiplicative identity of the field
     * <p> element * multiplicative identity = element <\p>
     */
    E getMultiplicativeIdentity();

    /**
     * Inverts the element such that
     * <p> element * invert(element) = multiplicative identity <\p>
     */
    E invert(E element);

    /**
     * Returns the result of dividing the elements
     */
    default E divide(E a, E b) {
        return multiply(a, invert(b));
    }

}
