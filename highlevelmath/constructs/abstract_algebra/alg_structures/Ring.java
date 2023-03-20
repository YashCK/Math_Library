package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface Ring<E extends Ring<E>> extends AdditiveGroup<E> {

    /**
     * Returns the result of multiplying the addends
     */
    E multiply(E first);

    /**
     * Returns the multiplicative identity of the field
     * <p> element * multiplicative identity = element <\p>
     */
    E one();

}
