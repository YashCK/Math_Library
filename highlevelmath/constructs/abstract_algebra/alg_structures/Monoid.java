package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface Monoid<S> extends SemiGroup<S>{
    
    S getIdentity();
    
    default boolean isIdentity(S a){
        return a.equals(this.getIdentity());
    }

    default boolean hasMultiplicativeIdentity(S a){
        return true;
    }
    
}
