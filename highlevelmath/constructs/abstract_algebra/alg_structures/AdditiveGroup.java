package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface AdditiveGroup<E> {

    /**
     * Returns the result of adding the addends
     */
    E add(E element);

    /**
     * Returns the additive identity of the field
     * <p> element + additive identity = element </p>
     */
    E zero();

    /**
     * Negates the element such that
     * <p> element + negate(element) = additive identity <\p>
     */
    E negate();

    /**
     * Returns the result of subtracting the elements
     */
    default E subtract(E first, E second){
        second.negate();
        return add(negate(second));
    }
}
