package highlevelmath.constructs.util;

public class NotInvertibleException extends Exception{

    /**
     * The constructor for NotInvertibleException
     * @param errorMessage The input message explain that should why the object is not invertible
     */
    public NotInvertibleException(String errorMessage) {
        super(errorMessage);
    }
    
}
