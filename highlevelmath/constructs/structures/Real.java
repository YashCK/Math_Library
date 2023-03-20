package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.AdditiveGroup;
import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.alg_structures.MultiplicativeGroup;
import highlevelmath.constructs.abstract_algebra.alg_structures.Ring;

public class Real implements Field {

    public final double value;

    public Real(int value){
        this.value = value;
    }

    public Real(double value){
        this.value = value;
    }

    @Override
    public AdditiveGroup add(AdditiveGroup element) {
        return null;
    }

    @Override
    public AdditiveGroup zero() {
        return null;
    }

    @Override
    public AdditiveGroup negate() {
        return null;
    }

    @Override
    public MultiplicativeGroup multiply(MultiplicativeGroup element) {
        return null;
    }

    @Override
    public Ring multiply(Ring first) {
        return null;
    }

    @Override
    public MultiplicativeGroup one() {
        return null;
    }

    @Override
    public MultiplicativeGroup invert() {
        return null;
    }
}
