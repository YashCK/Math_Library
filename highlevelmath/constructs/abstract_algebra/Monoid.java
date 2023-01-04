package highlevelmath.constructs.abstract_algebra;

public interface Monoid<S> extends SemiGroup<S>{
    
    S getIdentity();
    
    default boolean isIdentity(S a){
        return a.equals(this.getIdentity());
    }
    
}
