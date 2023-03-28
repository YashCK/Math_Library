package highlevelmath.constructs.abstract_algebra.rings;

import highlevelmath.constructs.abstract_algebra.alg_structures.EuclideanDomain;

public class Int implements EuclideanDomain<Int> {

    private int value;

    Int(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }

    @Override
    public void add(Int element) {
        value += element.value;
    }

    @Override
    public Int getZero() {
        return new Int(0);
    }

    @Override
    public Int negate() {
        return new Int(-value);
    }

    @Override
    public void subtract(Int element) {
        value -= element.value;
    }

    @Override
    public Int[] divisionWithRemainder(Int divisor) {
        Int[] divs = new Int[2];
        divs[1] = new Int(Math.floorMod(value, divisor.value));
        divs[0] = new Int((value - divs[1].value)/ divisor.value);
        return divs;
    }

    @Override
    public Int norm() {
        return new Int(Math.abs(value));
    }

    @Override
    public void multiply(Int other) {
        value *= other.value;
    }

    @Override
    public Int getOne() {
        return new Int(1);
    }

    @Override
    public Int copy() {
        return new Int(value);
    }
}
