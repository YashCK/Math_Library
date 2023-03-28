package highlevelmath.constructs.abstract_algebra.fields;

import highlevelmath.constructs.abstract_algebra.alg_structures.EuclideanDomain;
import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.NotInvertibleException;

import java.util.Objects;

public class Fraction<E extends EuclideanDomain<E>> implements Field<Fraction<E>> {

    private E numerator, denominator;

    Fraction(E num, E denom){
        this.numerator = num;
        this.denominator = denom;
    }

    @Override
    public void add(Fraction<E> element) {


        E newNumerator = (numerator * element.denominator) + (denominator * element.numerator);
        E newDenominator = (denominator * element.denominator);
        reduce(newNumerator, newDenominator);
    }

    @Override
    public Fraction<E> getZero() {
        return null;
    }

    @Override
    public Fraction<E> negate() {
        return null;
    }

    @Override
    public void subtract(Fraction<E> element) {

    }

    @Override
    public Fraction<E> invert() throws NotInvertibleException {
        return null;
    }

    @Override
    public void multiply(Fraction<E> first) {

    }

    @Override
    public Fraction<E> getOne() {
        return null;
    }

    @Override
    public Fraction<E> copy() {
        return null;
    }

    private void reduce(E num, E denom){
        E gcd = extendedGcd(num, denom)[0];
        E newNumerator = num.divisionWithRemainder(gcd)[0];
        E newDenominator = denom.divisionWithRemainder(gcd)[0];
        this.numerator = newNumerator;
        this.denominator = newDenominator;
    }

    private <T extends EuclideanDomain<?>> E[] extendedGcd(E first, E second){

        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
