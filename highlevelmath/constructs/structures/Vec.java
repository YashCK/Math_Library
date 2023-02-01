package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

/**
 * Representation of a Vector in some Vector space V over scalar field K
 * <pre> 
    *T is the type of the object stored in the Vector.
    *S is the type of the scalar that is associated with the Vector Space.
 * </pre>
 */
public abstract class Vec<T, S> {

    protected static final String INDEX_OUT_RANGE = "The index is out of the vector's range";
    protected static final String OPER_DIFFERING_LENGTHS = "This operation cannot be applied to vectors of different lengths.";

    protected T[] data;

    public final Field<T> element;
    public final Field<S> scalar;

    Vec(){
        element = setElementField();
        scalar = setScalarField();
    }

    //Operations

     /**
     * Adds the input vector to the current Vector
     * @param vector
     * @throws OperationUndefinedException
     */
    public void add(Vec<T, S> vector) throws OperationUndefinedException{
        MatrixOperation<T> function = element::add;
        applyOperation(vector, function);
    }
   
    /**
     * Subtracts the input vector from the current Vector
     * @param vector
     * @throws OperationUndefinedException
     */
    public void subtract(Vec<T, S> vector) throws OperationUndefinedException{
        MatrixOperation<T> function = element::subtract;
        applyOperation(vector, function);
    }
    
     /**
     * Scales the vector by a scalar value. The default is a Real Value (doubles).
     * The scalar part of Vector MUST be of the SAME TYPE of the Vector's set Field.
     * @param vector A scalar value
     */
    public abstract void scale(S factor) throws OperationUndefinedException;

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
    public abstract S dot(Vec<T, S> vector) throws OperationUndefinedException;
    
    /**
     * Calculates the inner product between this and the input Vector.
     * The result will be a part of the scalar field the Vector is using.
     * @param vector
     * @return a scalar value (inner product)
     * @throws OperationUndefinedException
     */
    public abstract S inner(Vec<T, S> vector) throws OperationUndefinedException;

    //General Methods
    /**
     * Exchange positions of two values of the vector
     * @param col1 The first index or column number
     * @param col2 The other index or column number
     * @throws OperationUndefinedException
     */
    public void interchangePos(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data.length || col2 >= data.length)
            throw new OperationUndefinedException("A column is out of the vector's range");
        T first = data[col1];
        data[col1] = data[col2];
        data[col2] = first;
    }

    /**
     * Calculates the sum of each of the Vector's components
     * @return sum of vector's value (scalar)
     */
    public T sumVals(){
        T sum = element.getZero();
        for(T d : data){
            sum = element.add(sum, d);
        }
        return sum;
    }

    //Getter Methods
    public T get(int index) throws OperationUndefinedException{
        if(index >= data.length)
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        return data[index];
    }

    public int length() {
        return data.length;
    }

    // getInnerProduct();

    /**
     * Returns whether a particular value is present within the vector or not
     * @param value The value to search for
     * @return True if value is in the vector, False otherwise
     */
    public boolean contains(T value){
        for(T val : data){
            if(val.equals(value)){
                return true;
            }
        }
        return false;
    }

    /**
     * Create a copy of the Vector
     * @return copy of the vector
     */
    public abstract Vec<T, S> copy();

    //Setters
    public void set(int index, T value) throws OperationUndefinedException{
        if(index >= data.length)
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        data[index] = value;
    }

    //defineInnerProduct();

    //Other Methods
    protected abstract Field<T> setElementField();

    protected abstract Field<S> setScalarField();

    public abstract S scalarInverse(T element);

    /**
     * WARNING - COULD LEAD TO UNINDENTED CONSQUENCES, ESPECIALLY IN MATRICES
     * Add a certain amount of 0s at the end of a vector
     * @param num The number of 0s to add to the end or the vector
     */
    public abstract void pad(int num);

    protected void applyOperation(Vec<T, S> vector, MatrixOperation<T> op) throws OperationUndefinedException{
        if(data.length != vector.length()){
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        for(int i = 0; i < data.length; i++){
            data[i] = op.operation(data[i], vector.get(i));
        }
    }

    @Override
    public boolean equals(Object o){
        try {
            if(this == o){
                return true;
            }
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((Vec<T, S>)o).length()){
                return false;
            }
            for(int i = 0; i < data.length; i++){
                if(data[i] != ((Vec<T, S>)o).get(i)){
                    return false;
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

    @Override
    public abstract String toString();
    
}
