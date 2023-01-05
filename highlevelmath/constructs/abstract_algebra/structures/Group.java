package highlevelmath.constructs.abstract_algebra.structures;

public interface Group<S> extends Monoid<S> {

    S invert(S a);

    default boolean areInverses(S a, S b){
        return multiply(a, b).equals(getIdentity());
    }

    default S divide(S a, S b){
        return multiply(a, invert(b));
    }

    boolean isCommutativeOverMultiplication();

    // void defineHomomorphism(Group<F> g);
    
}
