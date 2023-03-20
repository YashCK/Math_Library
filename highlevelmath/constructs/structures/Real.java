package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;

public class Real implements Field<Real> {

    public final double value;

    public Real(int value){
        this.value = value;
    }

    public Real(double value){
        this.value = value;
    }

    @Override
    public Real add(Real first, Real second) {
        return null;
    }

    @Override
    public Real getAdditiveIdentity() {
        return null;
    }

    @Override
    public Real negate(Real element) {
        return null;
    }

    @Override
    public Real multiply(Real first, Real second) {
        return null;
    }

    @Override
    public Real getMultiplicativeIdentity() {
        return null;
    }

    @Override
    public Real invert(Real element) {
        return null;
    }
}
