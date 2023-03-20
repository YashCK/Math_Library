package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface Ring<E> {

    /**
     * Returns the result of adding the addends
     */
    E add(E first, E second);

    /**
     * Returns the additive identity of the field
     * <p> element + additive identity = element </p>
     */
    E getAdditiveIdentity();

    /**
     * Negates the element such that
     * <p> element + negate(element) = additive identity <\p>
     */
    E negate(E element);

    /**
     * Returns the result of multiplying the elements
     */

    /**
     * Returns the result of subtracting the elements
     */
    default E subtract(E first, E second){
        return add(first, negate(second));
    }

    E multiply(E first, E second);

}
