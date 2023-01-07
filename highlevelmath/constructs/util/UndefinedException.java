package highlevelmath.constructs.util;

public class UndefinedException extends Exception{
     /**
     * The constructor for UndefinedException
     * @param errorMessage The input message that should be displayed for why an Undefined object exists
     */
    public UndefinedException(String errorMessage) {
        super(errorMessage);
    }
}
