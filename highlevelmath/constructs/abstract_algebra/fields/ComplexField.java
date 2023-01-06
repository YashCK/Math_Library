package highlevelmath.constructs.abstract_algebra.fields;

import java.util.ArrayList;
import java.util.List;

import highlevelmath.constructs.Complex;
import highlevelmath.constructs.abstract_algebra.structures.Field;
import highlevelmath.constructs.util.NotInvertibleException;

public class ComplexField implements Field<Complex>{

    @Override
    public List<Complex> divisionWithRemainder(Complex divident, Complex divisor) {
        List<Complex> l = new ArrayList<>();
        l.add(divide(divident, divisor));
        l.add(new Complex(0, 0));
        return l;
    }

    @Override
    public boolean zeroIsInvertible() {
        return false;
    }

    @Override
    public Complex add(Complex a, Complex b) {
        return Complex.add(a, b);
    }

    @Override
    public Complex getZero() {
        return new Complex(0, 0);
    }

    @Override
    public Complex subtract(Complex a, Complex b) {
        return Complex.subtract(a, b);
    }

    @Override
    public boolean isInstanceOfElement(Object o) {
        return o instanceof Complex;
    }

    @Override
    public Complex getIdentity() {
        return new Complex(1, 0);
    }

    @Override
    public Complex multiply(Complex a, Complex b) {
        return Complex.mul(a, b);
    }

    @Override
    public Complex invert(Complex a) {
        if(a.equals(new Complex(0, 0))){
            throw new NotInvertibleException("0 is not invertible in the Complex Number Field.")
        }
        return Complex.reciprocal(a);
    }
    
}
