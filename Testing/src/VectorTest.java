import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.structures.Vector;
import highlevelmath.constructs.structures.VectorBuilder;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.OperationUndefinedException;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class VectorTest {

    @Test
    public void builderTest() throws ConstructFormatException {
        //Real Vectors
        double[] first = {2.0, 4.0, 6.0, 8.0};
        Vector<Real> v1 = (Vector<Real>) (new VectorBuilder(first)).create();
        Vector<Real> v2 = (Vector<Real>) (new VectorBuilder(1.0, 2.0, 3, 5.0)).create();
        Vector<Real> v3 = (Vector<Real>) (new VectorBuilder(0, 2, 4, 6, 8)).create();
        System.out.println("v1: " + v1);
        System.out.println("v2: " + v2);
        System.out.println("v3: " + v3);
        //Complex Vectors
        int[] cFirst = {5, 6, 7, 8};
        Integer[] cFirst2 = {5, 6, 7, 8};
        //Construction using ints
        VectorBuilder bld = new VectorBuilder(VectorBuilder.Type.Integer, "Complex Vector", cFirst);
        Vector<Complex> c1 = (Vector<Complex>) bld.create();
        bld = new VectorBuilder(VectorBuilder.Type.Integer, "Complex Vector", cFirst2);
        Vector<Complex> c2 = (Vector<Complex>) bld.create();
        bld = new VectorBuilder(VectorBuilder.Type.Integer, "Complex Vector", 1, 2, 3, 4);
        Vector<Complex> c3 = (Vector<Complex>) bld.create();
        //Construction using doubles
        double[] cSecond = {2.3, 4.7, 6.4, 8.8};
        Double[] cSecond2 = {2.0, 4.0, 6.0, 8.0};
        Complex cTest = new Complex(2.3, 0);
        System.out.println("ctest: " + cTest);
        VectorBuilder bld2 = new VectorBuilder(VectorBuilder.Type.Double, "Complex Vector", cSecond);
        Vector<Complex> c4 = (Vector<Complex>) bld2.create();
        bld2 = new VectorBuilder(VectorBuilder.Type.Double, "Complex Vector", cSecond2);
        Vector<Complex> c5 = (Vector<Complex>) bld2.create();
        bld2 = new VectorBuilder(VectorBuilder.Type.Double, "Complex Vector", 1.0, 2.0, 3.0, 4.0);
        Vector<Complex> c6 = (Vector<Complex>) bld2.create();
        //Construction using Strings
        String[] cThird = {"1.0", "1 - 5i", "-2 + 89i", "42.6i"};
        VectorBuilder bld3 = new VectorBuilder(VectorBuilder.Type.String, "Complex Vector", cThird);
        Vector<Complex> c7 = (Vector<Complex>) bld3.create();
        bld3 = new VectorBuilder(VectorBuilder.Type.String, "Complex Vector", "1.0", "1 - 5i", "-2 + 89i", "42i");
        Vector<Complex> c8 = (Vector<Complex>) bld3.create();
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c3: " + c3);
        System.out.println("c4: " + c4);
        System.out.println("c5: " + c5);
        System.out.println("c6: " + c6);
        System.out.println("c7: " + c7);
        System.out.println("c8: " + c8);
    }

    @Test
    public void creationTest() {
        double[] first = {2.0, 4.0, 6.0, 8.0};
        Vector<Real> v1 = (Vector<Real>) (new VectorBuilder(first)).create();
        Vector<Real> v2 = new Vector<>(new Real(1), new Real(2.0), new Real(3), new Real(5));
        Vector<Real> v3 = new Vector<>(new Real(1.0), new Real(2.0), new Real(3.0), new Real(5));
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
    }

    @Test
    public void operationTest() throws OperationUndefinedException {
        double[] first = {2.0, 4.0, 6.0, 8.0};
        double[] second = {1.0, 3.0, 5.0, 7.0};
        double[] third = {1.0, 2.0, 3.0};
        VectorBuilder bld = new VectorBuilder(2.0, 4.0, 6.0, 8.0);
        Vector<Real> v1 = (Vector<Real>) bld.create();
        bld = new VectorBuilder(1.0, 3.0, 5.0, 7.0);
        Vector<Real> v2 = (Vector<Real>) bld.create();
        bld = new VectorBuilder(1.0, 2.0, 3.0);
        Vector<Real> v3 = (Vector<Real>) bld.create();
        bld = new VectorBuilder(2.0, 4.0, 6.0, 8.0);
        Vector<Real> v4 = (Vector<Real>) bld.create();
        v1.add(v2);
        bld = new VectorBuilder(3, 7, 11, 15);
        assertThat(v1).isEqualTo((Vector<Real>) bld.create());
        v1.subtract(v2);
        bld = new VectorBuilder(2.0, 4.0, 6.0, 8.0);
        assertThat(v1).isEqualTo((Vector<Real>) bld.create());
        assertThat(v1).isEqualTo(v4);
        try {
            v1.add(v3);
        } catch (OperationUndefinedException e) {
            assertWithMessage("Passed Exception Test");
        }
        assertThat(v1.dot(v2)).isEqualTo(new Real(100));
        try {
            v1.subtract(v3);
        } catch (OperationUndefinedException e) {
            assertWithMessage("Passed Exception Test 2");
        }
        try {
            v1.dot(v3);
        } catch (OperationUndefinedException e) {
            assertWithMessage("Passed Exception Test 3");
        }
    }

    @Test
    public void methodsTest() throws OperationUndefinedException {
        VectorBuilder bld = new VectorBuilder(2.0, 4.0, 6.0, 8.0);
        Vector<Real> v1 = (Vector<Real>) bld.create();
        v1.interchange(0, 2);
        bld = new VectorBuilder(6.0, 4.0, 2.0, 8.0);
        assertThat(v1).isEqualTo((Vector<Real>) bld.create());
        try {
            v1.interchange(1, 10);
        } catch (OperationUndefinedException e) {
            assertWithMessage("Passed Exception test 1");
        }
        v1.pad(2);
        bld = new VectorBuilder(6.0, 4.0, 2.0, 8.0, 0, 0);
        assertThat(v1).isEqualTo((Vector<Real>) bld.create());
        v1.pad(-1);
        assertThat(v1).isEqualTo((Vector<Real>) bld.create());
        assertThat(v1).isEqualTo(v1.copy());
        assertThat(v1 == v1.copy()).isFalse();
        v1.scale(new Real(0.5));
        bld = new VectorBuilder(3.0, 2.0, 1.0, 4.0, 0.0, 0.0);
        assertThat(v1).isEqualTo((Vector<Real>) bld.create());
        v1.scale(new Real(2.0));
        bld = new VectorBuilder(6.0, 4.0, 2.0, 8.0, 0.0, 0.0);
        assertThat(v1).isEqualTo((Vector<Real>) bld.create());
        v1.scale(new Real(-0.5));
        bld = new VectorBuilder(-3.0, -2.0, -1.0, -4.0, 0.0, 0.0);
        assertThat(v1).isEqualTo((Vector<Real>) bld.create());
        assertThat(v1.get(2)).isEqualTo(new Real(-1.0));
        assertThat(v1.length()).isEqualTo(6);
        assertThat((new Vector<>(new Real(0))).length()).isEqualTo(1);
        v1.set(0, new Real(100.0));
        assertThat(v1.get(0)).isEqualTo(new Real(100.0));
        int i = 0;
        for (Real d : v1) {
            assertThat(v1.get(i)).isNotNull();
            i++;
        }
        assertThat(v1.contains(new Real(100.0)));
        System.out.println(v1);
    }

    @Test
    public void generalTest() {
        double[] first = {2.0, 4.0, 6.0, 8.0};
        Vector<Real> v = (Vector<Real>) (new VectorBuilder(first)).create();
        assertThat(v.toString()).isEqualTo("[2.0, 4.0, 6.0, 8.0]");
        v.pad(-1);
        assertThat(v.toString()).isEqualTo("[2.0, 4.0, 6.0, 8.0]");
        v.pad(0);
        assertThat(v.toString()).isEqualTo("[2.0, 4.0, 6.0, 8.0]");
        v.pad(2);
        assertThat(v.toString()).isEqualTo("[2.0, 4.0, 6.0, 8.0, 0.0, 0.0]");
    }
}
