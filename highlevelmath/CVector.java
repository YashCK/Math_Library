package highlevelmath;

import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.structures.Vec;
import highlevelmath.constructs.structures.Vector;
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
    }

    /**
     * A constructor for CVector class
     *
     * @param values the double numbers that represent the elements of the vector
     */
    public CVector(double... values) {
        super();
        data = new Complex[values.length];
        for (int i = 0; i < values.length; i++) {
            data[i] = new Complex(values[i], 0);
        }
    }

    /**
     * A constructor for CVector class
     *
     * @param values the String representations of the Complex numbers that represent the elements of the vector
     *               <p>Each string must take the format of either a + bi, a - bi, a, or bi</p>
     */
    public CVector(String... values) {
        super();
        try {
            data = new Complex[values.length];
            for (int i = 0; i < values.length; i++) {
                data[i] = new Complex(values[i]);
            }
        } catch (ConstructFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void scale(Complex factor) throws OperationUndefinedException {
        for (Complex c : data) {
            c.multiply(factor);
        }
    }

    public void scale(double factor) throws OperationUndefinedException {
        for (Complex c : data) {
            c.scale(factor);
        }
    }

    @Override
    public Complex dot(Vec<Complex, Complex> vector) throws OperationUndefinedException {
        return null;
    }

    @Override
    public Complex inner(Vec<Complex, Complex> vector) throws OperationUndefinedException {
        return null;
    }

    @Override
    public Vec<Complex, Complex> copy() {
        return new CVector(Arrays.copyOf(data, data.length));
    }

    public Vector getRealComplement() {
        double[] values = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            values[i] = data[i].real();
        }
        return new Vector(values);
    }

    public CVector getComplexComplement() {
        Complex[] values = new Complex[data.length];
        for (int i = 0; i < data.length; i++) {
            values[i] = new Complex(0, data[i].imag());
        }
        return new CVector(values);
    }

    public Vector toRealVector() throws OperationUndefinedException {
        if (isComplex()) {
            throw new OperationUndefinedException("The vector is Complex. It cannot be converted to a Real vector.");
        }
        return getRealComplement();
    }

    public boolean isComplex() {
        for (Complex c : data) {
            if (c.imag() != 0)
                return false;
        }
        return true;
    }

    public void set(int index, double value) throws OperationUndefinedException {
        if (index >= data.length) {
            throw new OperationUndefinedException(INDEX_OUT_RANGE);
        }
        data[index] = new Complex(value, 0);
    }

    @Override
    public Complex scalarInverse(Complex element) {
        try {
            return element.invert();
        } catch (NotInvertibleException e1) {
            return element.getZero();
        }
    }

    @Override
    public void pad(int num) {
        if(num > 0){
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
