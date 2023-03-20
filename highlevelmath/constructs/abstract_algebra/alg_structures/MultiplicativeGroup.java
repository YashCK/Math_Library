package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface MultiplicativeGroup {

    /**
     * Returns the result of multiplying the elements
     */
    MultiplicativeGroup multiply(MultiplicativeGroup element);

    /**
     * Returns the multiplicative identity of the field
     * <p> element * multiplicative identity = element <\p>
     */
    MultiplicativeGroup one();

    /**
     * Inverts the element such that
     * <p> element * invert(element) = multiplicative identity <\p>
     */
    MultiplicativeGroup invert();

    /**
     * Returns the result of dividing the elements
     */
    default MultiplicativeGroup divide(MultiplicativeGroup b) {
        return multiply(b.invert());
    }

}
