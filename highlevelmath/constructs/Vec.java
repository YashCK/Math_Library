package highlevelmath.constructs;
import highlevelmath.constructs.abstract_algebra.Field;
import highlevelmath.constructs.util.*;

/**
 * Representation of a Vector in some Vector space V
 */
public abstract class Vec<T>{

    private Field<?> field;

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
    public abstract void add(Vec<T> vector) throws OperationUndefinedException;
   
    /**
     * Subtracts the input vector from the current Vector
     * @param vector
     * @throws OperationUndefinedException
     */
     public abstract void subtract(Vec<T> vector) throws OperationUndefinedException;
    
     /**
     * Scales the vector by a scalar value. The default is a Real Value (doubles).
     * The scalar part of Vector MUST be of the SAME TYPE of the Vector's set Field.
     * @param vector A scalar value
     */
    public abstract void scale(Object factor) throws OperationUndefinedException;
    
    /**
     * Calculates the inner product between this and the input Vector.
     * The result will be a part of the scalar field the Vector is using.
     * @param vector
     * @return Inner Product (scalar value)
     * @throws OperationUndefinedException
     */
    public abstract Object inner(Vec<T> vector) throws OperationUndefinedException;

    //Getters
    
    /**
     * Gets the value at a certain index
     * @param index The index that should be gotten
     * @return The value at the index passed in
     * @throws OperationUndefinedException
     */
    public abstract T get(int index) throws OperationUndefinedException;
   
    /**
     * Gets the length of the vector
     * @return The length of the vector
     */
    public abstract int length();
    
    /**
     * Gets the scalar Field the Vector is using
     * @return
     */
    public Field<?> getField(){
        return field;
    }

    //Setters
    /**
     * Sets an index of the vector to particular value
     * @param index The index that should be set
     * @param value The value to set to
     * @throws OperationUndefinedException
     */
    public abstract void set(int index, T value) throws OperationUndefinedException;
    /**
     * Sets the Scalar Field of the Vector to a new Scalar Field.
     * @param field
     */
    public void changeField(Field<?> field){
        this.field = field;
    }

    //Other Methods
    /**
     * Exchange positions of two values of the vector
     * @param col1 The first index or column number
     * @param col2 The other index or column number
     * @throws OperationUndefinedException
     */
    public abstract void interchangePos(int col1, int col2) throws OperationUndefinedException;
    /**
     * Returns whether a particular value is present within the vector or not
     * @param value The value to search for
     * @return True if value is in the vector, False otherwise
     */
    public abstract boolean contains(T value);
    /**
     * Calculates the Sum of each of the Vector's components
     * @return sum of vector's value (scalar)
     */
    public abstract Object sumVals();
    /**
     * Create a copy of the Vector
     * @return copy of the vector
     */
    public abstract Vec<T> copy();
    /**
     * Checks if the current vector equals the input Object
     * @param o An object to check equals to
     * @return boolean representing the vectors are equal
     */
    public abstract boolean equals(Object o);
    /**
     * Creates the string representation of the Vector
     * @return Vector's string representation
     */
    public abstract String toString();

}
