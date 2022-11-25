package highlevelmath.constructs;

/**
 * This class outlines an exception where an operation upon some object was applied but was invalid
 */
public class OperationUndefinedException extends Exception { 
    /**
     * The constructor for OperationUndefinedException
     * @param errorMessage The input message that should be displayed when this Exception is thrown
     */
    public OperationUndefinedException(String errorMessage) {
        super(errorMessage);
    }
}