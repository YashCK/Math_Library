package highlevelmath.constructs;

public class OperationUndefinedException extends Exception { 
    public OperationUndefinedException(String errorMessage) {
        super(errorMessage);
    }
}