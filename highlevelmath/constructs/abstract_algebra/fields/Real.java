package highlevelmath.constructs.abstract_algebra.fields;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.Associative;
import highlevelmath.constructs.util.Distributive;
import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.UndefinedException;

public class Real implements Field<Real> {

    private double value;

    public Real(int value) {
        this.value = value;
    }

    public Real(double value) {
        this.value = value;
    }

    @Associative
    @Override
    public void add(Real element) {
        value += element.value();
    }

    @Override
    public Real getZero() {
        return new Real(0.0);
    }

    @Override
    public Real negate() {
        return new Real(-value);
    }

    @Override
    public void subtract(Real element) {
        value -= element.value();
    }

    @Distributive
    @Associative
    @Override
    public void multiply(Real element) {
        value *= element.value();
        correctRounding();
    }

    @Override
    public Real getOne() {
        return new Real(1.0);
    }

    @Override
    public Real invert() throws NotInvertibleException {
        if (this.value == 0.0) {
            throw new NotInvertibleException("Zero is not invertible");
        }
        return new Real(1.0 / value);
    }

    public void divide(Real b) throws UndefinedException {
        if (b.value() == 0.0) {
            throw new UndefinedException("Cannot divide by zero.");
        }
        value /= b.value();
        correctRounding();
    }

    public double value() {
        return value;
    }

    @Override
    public String toString() {
        return "" + truncateDecimal(value, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Real r) {
            return value == r.value();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) value;
    }

    @Override
    public Real copy() {
        return new Real(value);
    }

    /**
     * Will correct any roundings issues (too much precision) with values
     */
    public void correctRounding() {
        double threshold = 1E-2;
        if (Math.abs(Math.round(value) - value) < threshold) {
            value = Math.round(value);
        }
    }

    protected double truncateDecimal(double d, int places) {
        double powerOfTen = Math.pow(10, places);
        return Math.floor(d * powerOfTen) / powerOfTen;
    }
}
