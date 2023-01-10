package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface IntegralDomain<S> extends Ring<S>, Group<S>{

    @Override
    default boolean hasZeroDivisors() {
        return false;
    }

    @Override
    default boolean isCommutativeOverMultiplication() {
        return true;
    }
    
}
