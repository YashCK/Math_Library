package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.OperationUndefinedException;

/**
 * Representation of a Vector in some Vector space V over scalar field K
 * <pre> 
    *E is the type of the object stored in the Vector
    *K is the Field of scalars (of type S) that is associated with the Vector Space.
 * </pre>
 */
public interface Vec<T, S, K extends Field<S>>{
    /*
     * TO IMPLEMENT: 
     * GET Inner Product
     * SET Inner Product
     */

    //Operations

    /**
     * Adds the input vector to the current Vector
     * @param vector
     * @throws OperationUndefinedException
     */
    void add(Vec<T, S, K> vector) throws OperationUndefinedException;
   
    /**
     * Subtracts the input vector from the current Vector
     * @param vector
     * @throws OperationUndefinedException
     */
     void subtract(Vec<T, S, K> vector) throws OperationUndefinedException;
    
     /**
     * Scales the vector by a scalar value. The default is a Real Value (doubles).
     * The scalar part of Vector MUST be of the SAME TYPE of the Vector's set Field.
     * @param vector A scalar value
     */
    void scale(S factor) throws OperationUndefinedException;

    /**
     * Calculated the standard dot product between this and the input Vector
     * The result will be part of the scalar field the Vector is using.
     * <p>
     * This method is used to calculated matrix multiplication
     * </p>
     * @param vector
     * @return a scalar value (dot product)
     * @throws OperationUndefinedException
     */
    S dot(Vec<T, S, K> vector) throws OperationUndefinedException;
    
    /**
     * Calculates the inner product between this and the input Vector.
     * The result will be a part of the scalar field the Vector is using.
     * @param vector
     * @return a scalar value (inner product)
     * @throws OperationUndefinedException
     */
    S inner(Vec<T, S, K> vector) throws OperationUndefinedException;

    //Getters
    
    /**
     * Gets the value at a certain index
     * @param index The index that should be gotten
     * @return The value at the index passed in
     * @throws OperationUndefinedException
     */
    T get(int index) throws OperationUndefinedException;
   
    /**
     * Gets the length of the vector
     * @return The length of the vector
     */
    int length();

    //Setters
    /**
     * Sets an index of the vector to particular value
     * @param index The index that should be set
     * @param value The value to set to
     * @throws OperationUndefinedException
     */
    void set(int index, T value) throws OperationUndefinedException;

    //Other Methods
    /**
     * Exchange positions of two values of the vector
     * @param col1 The first index or column number
     * @param col2 The other index or column number
     * @throws OperationUndefinedException
     */
    void interchangePos(int col1, int col2) throws OperationUndefinedException;
    /**
     * Returns whether a particular value is present within the vector or not
     * @param value The value to search for
     * @return True if value is in the vector, False otherwise
     */
    boolean contains(T value);
    /**
     * Calculates the sum of each of the Vector's components
     * @return sum of vector's value (scalar)
     */
    T sumVals();
    /**
     * Create a copy of the Vector
     * @return copy of the vector
     */
    Vec<T, S, K> copy();
    /**
     * Checks if the current vector equals the input Object
     * @param o An object to check equals to
     * @return boolean representing the vectors are equal
     */
    boolean equals(Object o);
    /**
     * Creates the string representation of the Vector
     * @return Vector's string representation
     */
    String toString();
    /**
     * WARNING - COULD LEAD TO UNINDENTED CONSQUENCES, ESPECIALLY IN MATRICES
     * Add a certain amount of 0s at the end of a vector
     * @param num The number of 0s to add to the end or the vector
     */
    void pad(int num);

}
