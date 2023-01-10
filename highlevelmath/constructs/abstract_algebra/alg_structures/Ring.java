package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface Ring<S> extends AdditiveGroup<S>, Monoid<S>{

    boolean hasZeroDivisors();
    boolean nonZeroElementsAreInvertible();
    boolean zeroIsInvertible();
    boolean identityElementsAreDifferent();
    
}
