package highlevelmath.constructs.abstract_algebra.alg_structures;

/**
 * Represents as Mathematical Set -> A collection of Objects
 */
public interface Set<S> {
    
    String toString();
    boolean equals(Object o);
    boolean isInstanceOfElement(Object o);

}
