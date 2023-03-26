package highlevelmath.constructs.util;

/**
 * This class outlines an exception where a function was used but no Solution was available
 */
public class NoExistingSolutionException extends Exception {
    public NoExistingSolutionException(String errorMessage) {
        super(errorMessage);
    }
}
