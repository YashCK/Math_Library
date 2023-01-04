package highlevelmath.constructs.abstract_algebra;

public interface EuclideanDomain<S> extends IntegralDomain<S>{

    S[] divisionWithRemainder(S divident, S divisor);
    
}
