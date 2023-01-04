package highlevelmath.constructs.abstract_algebra;

public interface Ring<S> extends AdditiveGroup<S>, Monoid<S>{

    boolean hasZeroDivisors();
    boolean hasMultiplicativeIdenity(S a);
    boolean nonZeroElementsAreInvertible();
    boolean zeroIsInvertible();
    boolean identityElementsAreDifferent();
    
}
