package highlevelmath.constructs.abstract_algebra;

public interface Monoid<S> extends SemiGroup<S>{
    
    S getIdentity();
    
}
