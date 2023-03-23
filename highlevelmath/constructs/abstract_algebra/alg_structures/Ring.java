package highlevelmath.constructs.abstract_algebra.alg_structures;

import highlevelmath.constructs.util.Associative;
import highlevelmath.constructs.util.Distributive;

public interface Ring<E extends Ring<E>> extends AdditiveGroup<E> {

    /**
     * Returns the result of multiplying the addends
     */
    @Associative
    @Distributive
    void multiply(E first);

    /**
     * Returns the multiplicative identity of the field
     * <p> element * multiplicative identity = element <\p>
     */
    E getOne();

}
