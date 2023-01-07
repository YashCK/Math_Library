package highlevelmath.constructs.abstract_algebra.fields;

import java.util.ArrayList;
import java.util.List;

import highlevelmath.constructs.abstract_algebra.structures.*;
import highlevelmath.constructs.util.NotInvertibleException;

public class RealField implements Field<Double>{

    @Override
    public List<Double> divisionWithRemainder(Double divident, Double divisor) {
        List<Double> l = new ArrayList<>();
        l.add(Math.floor(divident/divisor));
        l.add((divident % divisor));
        return l;
    }

    @Override
    public boolean zeroIsInvertible() {
        return false;
    }

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double getZero() {
        return 0.0;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public boolean isInstanceOfElement(Object o) {
        return o instanceof Double;
    }

    @Override
    public Double getIdentity() {
        return 1.0;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a*b;
    }

    @Override
    public Double invert(Double a) throws NotInvertibleException {
        if(a == 0){
            throw new NotInvertibleException("0 + 0i is not invertible in the Real Number Field.");
        }
        return 1/a;
    }
    

}
