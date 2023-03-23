package highlevelmath.constructs.abstract_algebra.alg_structures;

import highlevelmath.constructs.util.NotInvertibleException;

public interface Field<E extends Field<E>> extends Ring<E> {

    /**
     * Inverts the element such that
     * <p> element * invert(element) = multiplicative identity <\p>
     */
    E invert() throws NotInvertibleException;

}
