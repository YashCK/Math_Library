package highlevelmath.constructs.abstract_algebra.alg_structures;

public interface AdditiveGroup {

    /**
     * Returns the result of adding the addends
     */
    AdditiveGroup add(AdditiveGroup element);

    /**
     * Returns the additive identity of the field
     * <p> element + additive identity = element </p>
     */
    AdditiveGroup zero();

    /**
     * Negates the element such that
     * <p> element + negate(element) = additive identity <\p>
     */
    AdditiveGroup negate();

    /**
     * Returns the result of subtracting the elements
     */
    default AdditiveGroup subtract(AdditiveGroup element){
        return add(element.negate());
    }
}
