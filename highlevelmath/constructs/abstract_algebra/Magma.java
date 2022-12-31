package highlevelmath.constructs.abstract_algebra;

/**
 * A set structure that has a single binary operation that it is closed under
 */
public interface Magma<S> extends Set<S>{
    
    S operation(S ... args);

}
