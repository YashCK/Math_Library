package highlevelmath.constructs.abstract_algebra;

/**
 * A mathematical set structure which has a binary operation defined 
 * and also is associative over multiplication
 */
public interface SemiGroup<S> extends Magma<S>{

    S multiply(S a, S b);

    default S multiply(S a, S b, S c){
        return multiply(multiply(a, b), c);
    }

    default S multiply(S a, S b, S c, S d){
        return multiply(multiply(a, b), multiply(c, d));
    }

    @Override
    default S operation(S... args) {
        S value = null;
        for(int i = 0; i < args.length; i += 2){
            value = (value == null) ? multiply(args[i], args[i + 1]) : multiply(value, multiply(args[i], args[i + 1]));           
        }
        return value;
    }
    
}