package highlevelmath.constructs.abstract_algebra.structures;
import java.util.List;

import highlevelmath.constructs.util.UndefinedException;

public interface EuclideanDomain<S> extends IntegralDomain<S>{

    List<S> divisionWithRemainder(S divident, S divisor) throws UndefinedException;
    
}
