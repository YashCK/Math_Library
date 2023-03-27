package highlevelmath.constructs.abstract_algebra.fields;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.NotInvertibleException;

public class Fraction<E> implements Field<Fraction<E>> {

    private E numerator, denominator;

    @Override
    public void add(Fraction<E> element) {

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

    private E[] extendedGcd(E first, E second){
        return null;
    }


}
