import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.structures.Vector;
import highlevelmath.constructs.structures.VectorBuilder;
import highlevelmath.constructs.util.ConstructFormatException;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class CVectorTest {

    @Test
    public void creationTest() throws ConstructFormatException {
        double[] first = {2.0, 4.0, 6.0, 8.0};
        VectorBuilder<Complex> bld = new VectorBuilder<>(VectorBuilder.Type.Double, Complex.class, first);
        Vector<Complex> c1 = bld.create();
        bld = new VectorBuilder<>(VectorBuilder.Type.Integer, Complex.class, 1, 2, 3, 5);
        Vector<Complex> c2 = bld.create();
        bld = new VectorBuilder<>(VectorBuilder.Type.Double, Complex.class, 1.0, 2.0, 3.0, 5.0);
        Vector<Complex> c3 = bld.create();
        Complex[] third = {new Complex(1.0, 0), new Complex("1.0 + 4i"), new Complex("5i")};
        Vector<Complex> c4 = new Vector<>(third);
        bld = new VectorBuilder<>(VectorBuilder.Type.String, Complex.class, "1.0", "1 - 5i", "-2 + 89i", "42i");
        Vector<Complex> c5 = bld.create();
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
    }

    @Test
    public void operationTest() {
        double[] first = {1.0, 2.0, 3.0};
        VectorBuilder<Complex> bld = new VectorBuilder<>();
        bld.set(VectorBuilder.Type.String, Complex.class);
        Vector<Complex> v1 = bld.construct("2.0 + 1i", "4.0 + 0i", "6.0 + 2i", "8.0 + 3i");
        Vector<Complex> v2 = bld.construct("1.0 + 3i", "3.0 + 4i", "5.0 + 2i", "7.0 + i");
        bld.set(VectorBuilder.Type.Double);
        Vector<Complex> v3 = bld.construct(first);
        bld.set(VectorBuilder.Type.String);
        Vector<Complex> v4 = bld.construct("2.0 + 1i", "4.0 + 0i", "6.0 + 2i", "8.0 + 3i");
        v1.add(v2);
        assertThat(v1.toString()).isEqualTo("[3.0 + 4.0i, 7.0 + 4.0i, 11.0 + 4.0i, 15.0 + 4.0i]");
        v1.subtract(v2);
        assertThat(v1.toString()).isEqualTo("[2.0 + 1.0i, 4.0 + 0.0i, 6.0 + 2.0i, 8.0 + 3.0i]");
        assertThat(v1).isEqualTo(v4);
        try {
            v1.add(v3);
        } catch (RuntimeException e) {
            assertWithMessage("Passed Exception Test");
        }
        try {
            v1.subtract(v3);
        } catch (RuntimeException e) {
            assertWithMessage("Passed Exception Test 2");
        }
        try {
            v1.dot(v3);
        } catch (RuntimeException e) {
            assertWithMessage("Passed Exception Test 3");
        }
    }

}
