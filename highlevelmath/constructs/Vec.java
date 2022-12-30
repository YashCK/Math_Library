package highlevelmath.constructs;

import highlevelmath.constructs.util.*;

/**
 * Representation of a Vector in some Vector space V
 */
public interface Vec<T>{

    //Operations
    void add(Vec<T> vector) throws OperationUndefinedException;
    void subtract(Vec<T> vector) throws OperationUndefinedException;
    void scale(Field<?> vector);
    Field<?> inner(Vec<T> vector);

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
    T sumVals();
    Vec<T> copy();
    boolean equals(Object o);
    String toString();

}
