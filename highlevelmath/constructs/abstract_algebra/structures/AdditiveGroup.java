package highlevelmath.constructs.abstract_algebra.structures;

public interface AdditiveGroup<S> extends Set<S>{

    S add(S a, S b);

    S getZero();

    S subtract(S a, S b);

    S invert(S a);

    boolean isCommutativeOverAddition();

    // default S add(S a, S b, S c){
    //     return add(add(a, b), c);
    // }

    // default S add(S a, S b, S c, S d){
    //     return add(add(a, b), add(c, d));
    // }

    default S add(S ... args){
        S value = null;
        for(int i = 0; i < args.length; i++){
            value = (value == null) ? add(args[i], args[i + 1]) : add(value, add(args[i], args[i + 1]));
        }
        return value;
    }

    // default S subtract(S a, S b, S c){
    //     return subtract(subtract(a, b), c);
    // }

    // default S subtract(S a, S b, S c, S d){
    //     return subtract(subtract(a, b), subtract(c, d));
    // }

    default S subtract(S ... args){
        S value = null;
        for(int i = 0; i < args.length; i++){
            value = (value == null) ? subtract(args[i], args[i + 1]) : subtract(value, subtract(args[i], args[i + 1]));
        }
        return value;
    }
    
}
