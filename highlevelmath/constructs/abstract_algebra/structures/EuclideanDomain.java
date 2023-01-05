package highlevelmath.constructs.abstract_algebra.structures;
import java.util.List;

public interface EuclideanDomain<S> extends IntegralDomain<S>{

    List<S> divisionWithRemainder(S divident, S divisor);
    
}
