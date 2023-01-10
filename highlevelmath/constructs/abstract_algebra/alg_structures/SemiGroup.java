package highlevelmath.constructs.abstract_algebra.alg_structures;

/**
 * A mathematical set structure which has a binary operation defined 
 * and also is associative over multiplication
 */
public interface SemiGroup<S> extends Set<S>{

    S multiply(S a, S b);

    default S multiply(S a, S b, S c){
        return multiply(multiply(a, b), c);
    }

    default S multiply(S a, S b, S c, S d){
        return multiply(multiply(a, b), multiply(c, d));
    }

    default S multiply(S... args) {
        S value = null;
        for(int i = 0; i < args.length; i += 2){
            value = (value == null) ? multiply(args[i], args[i + 1]) : multiply(value, multiply(args[i], args[i + 1]));           
        }
        return value;
    }

    default S power(S base, int exp){
        S value = null;
        if(exp >= 1){
            value = base;
        }
        for(int i = 2; i < exp; i++){
            value = multiply(value, base);
        }
        return value;
    }
    
}
