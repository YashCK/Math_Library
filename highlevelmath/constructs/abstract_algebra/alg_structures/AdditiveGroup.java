package highlevelmath.constructs.abstract_algebra.alg_structures;

import highlevelmath.constructs.util.Associative;

public interface AdditiveGroup<E extends AdditiveGroup<E>> extends Set<E> {

    @Associative
    /**
     * Returns the result of adding the addends
     */
    void add(E element);

    /**
     * Returns the additive identity of the field
     * <p> element + additive identity = element </p>
     */
    E getZero();

    /**
     * Negates the element such that
     * <p> element + negate(element) = additive identity <\p>
     */
    E negate();

    /**
     * Returns the result of subtracting the elements
     */
    void subtract(E element);

    boolean isAbelian();
}

