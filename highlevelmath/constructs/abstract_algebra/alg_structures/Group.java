package highlevelmath.constructs.abstract_algebra.alg_structures;

import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.OperationUndefinedException;

public interface Group<S> extends Monoid<S> {

    S invert(S a) throws NotInvertibleException;

    default boolean areInverses(S a, S b){
        return multiply(a, b).equals(getIdentity());
    }

    default S divide(S a, S b) throws OperationUndefinedException{
        try {
            return multiply(a, invert(b));
        } catch (NotInvertibleException e) {
            throw new OperationUndefinedException("Element b is not part of this group as it is not invertible.");
        }
    }

    boolean isCommutativeOverMultiplication();

    // void defineHomomorphism(Group<F> g);
    
}
