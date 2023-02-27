package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

import java.util.Iterator;

/**
 * Representation of a Vector in some Vector space V over scalar field K
 * <pre>
 * T is the type of the object stored in the Vector.
 * S is the type of the scalar that is associated with the Vector Space.
 * </pre>
 */
public abstract class Vec<T, S> implements Iterable<T> {

    protected static final String INDEX_OUT_RANGE = "The index is out of the vector's range";
    protected static final String OPER_DIFFERING_LENGTHS = "This operation cannot be applied to vectors of different lengths.";

    protected T[] data;

    public final Field<T> element;
    public final Field<S> scalar;

    Vec() {
        element = setElementField();
        scalar = setScalarField();
    }

    //Operations

    /**
     * Adds the input vector to the current Vector
     *
     * @param vector
     * @throws OperationUndefinedException
     */
    public void add(Vec<T, S> vector) throws OperationUndefinedException {
        applyOperation(vector, element::add);
    }

    /**
     * Subtracts the input vector from the current Vector
     *
     * @param vector
     * @throws OperationUndefinedException
     */
    public void subtract(Vec<T, S> vector) throws OperationUndefinedException {
        applyOperation(vector, element::subtract);
    }

    /**
     * Scales the vector by a scalar value.
     * The scalar part of Vector MUST be of the SAME TYPE of the Vector's set Field.
     *
     * @param factor A scalar value
     */
    public abstract void scale(S factor) throws OperationUndefinedException;

    /**
     * Calculates the standard dot product between this and the input Vector
     * The result will be part of the scalar field the Vector is using.
     * <p>
     * This method is used to calculated matrix multiplication
     * </p>
     *
     * @param vector
     * @return a scalar value (dot product)
     * @throws OperationUndefinedException
     */
    public abstract S dot(Vec<T, S> vector) throws OperationUndefinedException;

    /**
     * Calculates the inner product between this and the input Vector.
     * The result will be a part of the scalar field the Vector is using.
     *
     * @param vector
     * @return a scalar value (inner product)
     * @throws OperationUndefinedException
     */
    public abstract S inner(Vec<T, S> vector) throws OperationUndefinedException;

    //General Methods

    /**
     * Exchange positions of two values of the vector
     *
     * @param pos1 The first index number
     * @param pos2 The other index number
     * @throws OperationUndefinedException
     */
    public void interchangePos(int pos1, int pos2) throws OperationUndefinedException {
        if (pos1 >= data.length || pos2 >= data.length) {
            throw new OperationUndefinedException("A column is out of the vector's range");
        }
        T first = data[pos1];
        data[pos1] = data[pos2];
        data[pos2] = first;
    }

    /**
     * Calculates the sum of each of the Vector's components
     *
     * @return sum of the vector's values
     */
    public T sumValues() {
        T sum = element.getZero();
        for (T d : data) {
            sum = element.add(sum, d);
        }
        return sum;
    }

    //Getter Methods

    /**
     * Gets the element of the vector at a particular index
     *
     * @param index
     * @return the element value at specified index
     * @throws OperationUndefinedException
     */
    public T get(int index) throws OperationUndefinedException {
        if (index >= data.length || index < 0) {
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        return data[index];
    }

    /**
     * Gets the length of the vector
     *
     * @return the number of elements in the vector
     */
    public int length() {
        return data.length;
    }

    public void defineInnerProduct() {
        return;
    }

    public void getInnerProduct() {
        return;
    }

    /**
     * Returns whether a particular value is present within the vector or not
     *
     * @param value The value to search for
     * @return True if value is in the vector, False otherwise
     */
    public boolean contains(T value) {
        for (T val : data) {
            if (val.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create a copy of the Vector
     *
     * @return copy of the vector
     */
    public abstract Vec<T, S> copy();

    //Setters

    /**
     * Sets the value of the element at as specified index
     *
     * @param index element to set value of
     * @param value value to set the element to
     * @throws OperationUndefinedException
     */
    public void set(int index, T value) throws OperationUndefinedException {
        if (index >= data.length) {
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        data[index] = value;
    }

    //Other Methods
    protected abstract Field<T> setElementField();

    protected abstract Field<S> setScalarField();

    public abstract S scalarInverse(T element);

    /**
     * WARNING - COULD LEAD TO UNINTENDED CONSEQUENCES, ESPECIALLY IN MATRICES
     * Add a certain amount of 0s at the end of a vector
     *
     * @param num The number of 0s to add to the end or the vector
     */
    public abstract void pad(int num);

    protected void applyOperation(Vec<T, S> vector, MatrixOperation<T> op) throws OperationUndefinedException {
        if (data.length != vector.length()) {
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = op.operation(data[i], vector.get(i));
        }
    }

    @Override
    public boolean equals(Object o) {
        try {
            if (this == o) {
                return true;
            }
            if (o instanceof Vec oVec) {
                if (data.length != oVec.length()) {
                    return false;
                }
                int counter = 0;
                for (T element : data) {
                    if (!element.equals(oVec.get(counter))) {
                        return false;
                    }
                    counter++;
                }
                return true;
            }
            return false;
        } catch (OperationUndefinedException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public abstract String toString();

    @Override
    public Iterator<T> iterator() {
        return new VectorIterator();
    }

    private class VectorIterator implements Iterator<T> {

        private int currentPos = 0;

        @Override
        public boolean hasNext() {
            return currentPos < data.length - 1;
        }

        @Override
        public T next() {
            currentPos++;
            try {
                return get(currentPos - 1);
            } catch (OperationUndefinedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
