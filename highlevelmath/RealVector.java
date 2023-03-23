package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.OperationUndefinedException;

public class RealVector extends Vec<Real, Real> {

    //Constructors

    /**
     * A constructor for Vector class
     *
     * @param values the doubles that represent the elements of the vector
     */
    public RealVector(double... values) {
        data = new Real[values.length];
        for (int i = 0; i < values.length; i++) {
            data[i] = new Real(values[i]);
        }
    }

    /**
     * A constructor for Vector class
     *
     * @param values the integers that represent the elements of the vector
     */
    public RealVector(int... values) {
        data = new Real[values.length];
        for (int i = 0; i < values.length; i++) {
            data[i] = new Real(values[i]);
        }
    }

    /**
     * A constructor for Vector class
     *
     * @param values the Real numbers that represent the elements of the vector
     */
    public RealVector(Real... values) {
        super();
    }

    @Override
    public void scale(Real factor) throws OperationUndefinedException {
        for (Real datum : data) {
            datum.multiply(factor);
        }
        correctRounding();
    }

    @Override
    public Real dot(Vec<Real, Real> vector) throws OperationUndefinedException {
        if (data.length != vector.length()) {
            throw new OperationUndefinedException(OPER_DIFFERING_LENGTHS);
        }
        double sum = 0.0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i].value() * vector.get(i).value();
        }
        return new Real(sum);
    }

    @Override
    public Real inner(Vec<Real, Real> vector) throws OperationUndefinedException {
        return null;
    }

    @Override
    public Vec<Real, Real> copy() {
        return new RealVector(data);
    }

    @Override
    public Real scalarInverse(Real element) {
        try {
            return element.invert();
        } catch (NotInvertibleException e1) {
            return element.getZero();
        }
    }

    @Override
    public void pad(int num) {
        if(num > 0){
            Real[] newArray = new Real[data.length + num];
            for (int i = 0; i < data.length + num; i++) {
                if (i < data.length) {
                    newArray[i] = data[i];
                } else {
                    newArray[i] = new Real(0.0);
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
        for (Real element : data) {
            if (index != 0)
                bld.append(", ");
            bld.append(truncateDecimal(element, 2));
            index++;
        }
        bld.append("]");
        return bld.toString();
    }

    //Other Methods
    /**
     * Will correct any roundings issues (too much precision) with values in the matrix
     */
    public void correctRounding() {
        double threshold = 1E-2;
        for (int i = 0; i < data.length; i++) {
            if (Math.abs(Math.round(data[i].value()) - data[i].value()) < threshold) {
                data[i] = new Real(Math.round(data[i].value()));
            }
        }
    }

    protected double truncateDecimal(Real r, int places) {
        double powerOfTen = Math.pow(10, places);
        return Math.floor(r.value() * powerOfTen) / powerOfTen;
    }
}
