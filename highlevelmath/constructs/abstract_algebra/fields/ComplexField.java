package highlevelmath.constructs.abstract_algebra.fields;

import java.util.ArrayList;
import java.util.List;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.structures.Complex;
import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.UndefinedException;

public class ComplexField implements Field<Complex>{

    @Override
    public List<Complex> divisionWithRemainder(Complex divident, Complex divisor) throws UndefinedException{
        List<Complex> l = new ArrayList<>();
        l.add(Complex.div(divident, divisor));
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
    public Complex invert(Complex a) throws NotInvertibleException {
        try {
            return Complex.reciprocal(a);
        } catch (UndefinedException e) {
            throw new NotInvertibleException("0 is not invertible in the Complex Number Field.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj == null || obj.getClass()!= this.getClass());
    }
    
}
