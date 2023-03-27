package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.AdditiveGroup;
import highlevelmath.constructs.util.Commutative;

public interface Vec<K> extends AdditiveGroup<Vec<K>> {

    //Operations

    @Commutative
    @Override
    void add(Vec<K> element);

    /**
     * Scales the vector by a scalar value.
     * <p>The scalar part of Vector MUST be of the SAME TYPE of the Vector's set Field. </p>
     *
     * @param factor A scalar value
     */
    void scale(K factor);

    /**
     * Calculates the standard dot product between this and the input Vector
     * <p>The result will be part of the scalar field the Vector is using.</p>
     * <p>This method is used to calculated matrix multiplication</p>
     *
     * @param vector input vector
     * @return a scalar value (dot product)
     */
    K dot(Vec<K> vector);

    /**
     * Calculates the inner product between this and the input Vector.
     * <p>The result will be a part of the scalar field the Vector is using. </p>
     *
     * @param vector input vector
     * @return a scalar value (inner product)
     */
    K inner(Vec<K> vector);

    //General Methods

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

    @Override
    public String toString();



}
