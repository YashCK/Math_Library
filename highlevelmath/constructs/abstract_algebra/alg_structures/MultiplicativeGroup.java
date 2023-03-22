package highlevelmath.constructs.abstract_algebra.alg_structures;

import highlevelmath.constructs.util.Associative;
import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.UndefinedException;

public interface MultiplicativeGroup<E extends MultiplicativeGroup<E>> extends Set<E> {

    /**
     * Returns the result of multiplying the elements
     */
    @Associative
    void multiply(E element);

    /**
     * Returns the multiplicative identity of the field
     * <p> element * multiplicative identity = element <\p>
     */
    E getOne();

    /**
     * Inverts the element such that
     * <p> element * invert(element) = multiplicative identity <\p>
     */
    E invert() throws NotInvertibleException;

    boolean isAbelian();

}
