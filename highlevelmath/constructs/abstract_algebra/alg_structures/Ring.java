package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface Ring<E> extends AdditiveGroup {

    /**
     * Returns the result of multiplying the addends
     */
    Ring multiply(Ring first);

    /**
     * Returns the multiplicative identity of the field
     * <p> element * multiplicative identity = element <\p>
     */
    Ring one();

}
