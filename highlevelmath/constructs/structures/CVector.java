package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.ComplexField;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.OperationUndefinedException;

import java.util.Arrays;

public class CVector extends Vec<Complex, Complex> {

    /**
     * A constructor for CVector class
     *
     * @param values the Complex numbers that represent the elements of the vector
     */
    public CVector(Complex... values) {
        super();
        data = Arrays.copyOf(values, values.length);
    }

    /**
     * A constructor for CVector class
     *
     * @param vector An array of double numbers that represent the real values of the vector
     *               NOTE: All the complex values will be set to 0
     */
    public CVector(Double[] vector) {
        super();
        for (int i = 0; i < vector.length; i++) {
            data[i] = new Complex(vector[i], 0);
        }
    }

    /**
     * A constructor for CVector class
     *
     * @param values the double numbers that represent the elements of the vector
     */
    public CVector(double... values) {
        super();
        for (int i = 0; i < values.length; i++) {
            data[i] = new Complex(values[i], 0);
        }
    }

    /**
     * A constructor for CVector class
     *
     * @param values the String representations of the Complex numbers that represent the elements of the vector
     *               - Each string must take the format of either a + bi, a - bi, a, or bi
     */
    public CVector(String... values) throws ConstructFormatException {
        super();
        for (int i = 0; i < values.length; i++) {
            data[i] = new Complex(values[i]);
        }
    }

    //Operations
    @Override
    public void scale(Complex factor) throws OperationUndefinedException {
        for (int i = 0; i < data.length; i++) {
            data[i] = Complex.mul(data[i], factor);
        }
    }

    public void scale(double factor) throws OperationUndefinedException {
        for (Complex datum : data) {
            datum.scale(factor);
        }
    }

    @Override
    public Complex dot(Vec<Complex, Complex> vector) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Complex inner(Vec<Complex, Complex> vector) throws OperationUndefinedException {
        // TODO Auto-generated method stub
        return null;
    }

    //General Methods
    @Override
    public Vec<Complex, Complex> copy() {
        return new CVector(Arrays.copyOf(data, data.length));
    }

    Vector getRealComplement() {
        double[] values = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            values[i] = data[i].getReal();
        }
        return new Vector(values);
    }

    CVector getComplexComplement() {
        Complex[] values = new Complex[data.length];
        for (int i = 0; i < data.length; i++) {
            values[i] = new Complex(0, data[i].getImag());
        }
        return new CVector(values);
    }

    Vector toRealVector() throws OperationUndefinedException {
        if (isComplex()) {
            throw new OperationUndefinedException("The vector is Complex. It cannot be converted to a Real vector.");
        }
        return getRealComplement();
    }

    public void set(int index, double value) throws OperationUndefinedException {
        if (index >= data.length) {
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        data[index] = new Complex(value, 0);
    }

    //Other Methods

    @Override
    public Complex scalarInverse(Complex e) {
        try {
            return this.element.invert(e);
        } catch (NotInvertibleException e1) {
            return this.scalar.getZero();
        }
    }

    @Override
    protected Field<Complex> setElementField() {
        return new ComplexField();
    }

    @Override
    protected Field<Complex> setScalarField() {
        return new ComplexField();
    }

    boolean isComplex() {
        for (Complex c : data) {
            if (c.getImag() != 0)
                return false;
        }
        return true;
    }

    @Override
    public void pad(int num) {
        Complex[] newArray = new Complex[data.length + num];
        for (int i = 0; i < data.length + num; i++) {
            if (i < data.length) {
                newArray[i] = data[i];
            } else {
                newArray[i] = new Complex(0, 0);
            }
        }
        this.data = newArray;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("[");
        int index = 0;
        for (Complex element : data) {
            if (index != 0) {
                bld.append(", ");
            }
            bld.append(element.toString());
            index++;
        }
        bld.append("]");
        return bld.toString();
    }
}
