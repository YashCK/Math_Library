package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;

public class Real implements Field {

    public final double value;

    public Real(int value){
        this.value = value;
    }

    public Real(double value){
        this.value = value;
    }

}
