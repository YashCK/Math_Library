package highlevelmath.constructs.abstract_algebra.structures;

public interface Field<S> extends EuclideanDomain<S>{

    @Override
    default boolean nonZeroElementsAreInvertible() {
        return true;
    }

    @Override
    default boolean identityElementsAreDifferent() {
        return true;
    }

}
