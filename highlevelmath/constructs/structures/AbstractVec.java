package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.AdditiveGroup;
import highlevelmath.constructs.abstract_algebra.alg_structures.Field;

import java.util.Arrays;


public abstract class AbstractVec<T, S extends Field<S>> implements Vec<S> {

    protected static final String INDEX_OUT_RANGE = "The index is out of the vector's range";
    protected static final String OPER_DIFFERING_LENGTHS = "This operation cannot be applied to vectors of different lengths.";

    protected T[] data;

    public Vec(T... values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("At least one value is required");
        }
        data = Arrays.copyOf(values, values.length);
    }

    //Operations

    /**
     * Adds the input vector to the current Vector
     */
    public void add(Vec<T, S> vector) {
        if (data.length != vector.length()) {
            throw new RuntimeException(OPER_DIFFERING_LENGTHS);
        }
        for (int i = 0; i < data.length; i++) {
            data[i].add(vector.get(i));
        }
    }

    /**
     * Subtracts the input vector from the current Vector
     */
    public void subtract(Vec<T, S> vector) {
        if (data.length != vector.length()) {
            throw new RuntimeException(OPER_DIFFERING_LENGTHS);
        }
        for (int i = 0; i < data.length; i++) {
            data[i].subtract(vector.get(i));
        }
    }

    /**
     * Scales the vector by a scalar value.
     * <p>The scalar part of Vector MUST be of the SAME TYPE of the Vector's set Field. </p>
     *
     * @param factor A scalar value
     */
    public abstract void scale(S factor);

    /**
     * Calculates the standard dot product between this and the input Vector
     * <p>The result will be part of the scalar field the Vector is using.</p>
     * <p>This method is used to calculated matrix multiplication</p>
     *
     * @param vector input vector
     * @return a scalar value (dot product)
     */
    public abstract S dot(Vec<T, S> vector);

    /**
     * Calculates the inner product between this and the input Vector.
     * <p>The result will be a part of the scalar field the Vector is using. </p>
     *
     * @param vector input vector
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
     */
    public void interchange(int pos1, int pos2) {
        if (pos1 >= data.length || pos2 >= data.length) {
            throw new RuntimeException("A column is out of the vector's range");
        }
        T first = data[pos1];
        data[pos1] = data[pos2];
        data[pos2] = first;
    }

    /**
     * Calculates the sum of each of the Vector's components
     */
    public T sumValues() {
        T sum = data[0];
        for (int i = 1; i < data.length; i++) {
            sum.add(data[i]);
        }
        return sum;
    }

    //Getter Methods

    /**
     * Gets the element of the vector at a particular index
     */
    public T get(int index) {
        if (index >= data.length || index < 0) {
            throw new RuntimeException(INDEX_OUT_RANGE);
        }
        return data[index];
    }

    /**
     * Gets the length of the vector (number of elements)
     */
    public int length() {
        return data.length;
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
     */
    public abstract Vec<T, S> copy();

    //Setters

    /**
     * Sets the value of the element at as specified index
     *
     * @param index element to set value of
     * @param value value to set the element to
     */
    public void set(int index, T value) {
        if (index >= data.length) {
            throw new RuntimeException(INDEX_OUT_RANGE);
        }
        data[index] = value;
    }

    public void setInnerProduct() {
        //TODO
    }

    //Other Methods
    public abstract S scalarInverse(T element);

    /**
     * WARNING - COULD LEAD TO UNINTENDED CONSEQUENCES, ESPECIALLY IN MATRICES
     * Add a certain amount of 0s at the end of a vector
     *
     * @param num The number of 0s to add to the end or the vector
     */
    public abstract void pad(int num);

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
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("[");
        int index = 0;
        for (T element : data) {
            if (index != 0) {
                bld.append(", ");
            }
            bld.append(element.toString());
            index++;
        }
        bld.append("]");
        return bld.toString();
    }

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
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
