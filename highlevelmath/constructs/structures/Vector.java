package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.RealField;
import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.OperationUndefinedException;
import java.util.Arrays;
public class Vector extends Vec<Double, Double> {

    //Constructors

    /**
     * A constructor for Vector class
     *
     * @param values the doubles that represent the elements of the vector
     */
    public Vector(double... values) {
        super();
        for (int i = 0; i < values.length; i++) {
            data[i] = values[i];
        }
    }

    /**
     * A constructor for Vector class
     *
     * @param values the array doubles which represents the elements of the vector
     */
    public Vector(Double[] values) {
        super();
        this.data = values;
    }

    /**
     * A constructor for Vector class
     *
     * @param values the integers that represent the elements of the vector
     */
    public Vector(int... values) {
        super();
        for (int i = 0; i < values.length; i++) {
            data[i] = (double) values[i];
        }
    }

    //Operations
    public void modulus(Vec<Double, Double> vector) throws OperationUndefinedException {
        if (vector.contains(0.0)) {
            throw new OperationUndefinedException("This operation cannot be applied to input vectors with value 0.");
        }
        applyOperation(vector, (d1, d2) -> d1 % d2);
    }

    @Override
    public void scale(Double factor) throws OperationUndefinedException {
        for (int i = 0; i < data.length; i++) {
            data[i] *= factor;
        }
        correctRounding();
    }

    @Override
    public Double dot(Vec<Double, Double> vector) throws OperationUndefinedException {
        if (data.length != vector.length()) {
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i] * vector.get(i);
        }
        return sum;
    }

    @Override
    public Double inner(Vec<Double, Double> vector) throws OperationUndefinedException {
        return this.dot(vector);
    }

    //General Methods

    @Override
    public Vec<Double, Double> copy() {
        return new Vector(data);
    }

    //Other Methods

    @Override
    protected Field<Double> setElementField() {
        return new RealField();
    }

    @Override
    protected Field<Double> setScalarField() {
        return new RealField();
    }

    @Override
    public Double scalarInverse(Double e) {
        try {
            return this.element.invert(e);
        } catch (NotInvertibleException e1) {
            return this.scalar.getZero();
        }
    }

    public CVector toComplex() {
        return new CVector(data);
    }

    public void pad(int num) {
        Double[] newArray = new Double[data.length + num];
        for (int i = 0; i < data.length + num; i++) {
            if (i < data.length) {
                newArray[i] = data[i];
            } else {
                newArray[i] = 0.0;
            }
        }
        this.data = newArray;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("[");
        int index = 0;
        for (Double element : data) {
            if (index != 0)
                bld.append(", ");
            bld.append(truncateDecimal(element, 2));
            index++;
        }
        bld.append("]");
        return bld.toString();
    }

    /**
     * Will correct any roundings issues (too much precision) with values in the matrix
     */
    public void correctRounding() {
        double threshold = 1E-2;
        for (int i = 0; i < data.length; i++) {
            if (Math.abs(Math.round(data[i]) - data[i]) < threshold) {
                data[i] = (double) Math.round(data[i]);
            }
        }
    }

    protected double truncateDecimal(double value, int places) {
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen) / powerOfTen;
    }
}
