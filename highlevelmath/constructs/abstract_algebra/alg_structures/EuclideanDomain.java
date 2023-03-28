package highlevelmath.constructs.abstract_algebra.alg_structures;

import highlevelmath.constructs.abstract_algebra.rings.Int;

public interface EuclideanDomain<E extends Ring<E>> extends Ring<E>{

    E[] divisionWithRemainder(E divisor);

    E norm();

}
