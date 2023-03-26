import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.structures.Matrix;
import highlevelmath.constructs.structures.MatrixBuilder;
import highlevelmath.constructs.structures.Vector;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class MatrixTest {

    @Test
    public void builderTesT() {
        //Test Double[][]
        MatrixBuilder<Real> bld = new MatrixBuilder<>();
        bld.set(MatrixBuilder.Type.Double, Real.class);
        Double[][] mat = {{0.0, 3.0, -6.0, 6.0, 4.0, -5.0},
                {3.0, -7.0, 8.0, -5.0, 8.0, 9.0},
                {3.0, -9.0, 12.0, -9.0, 6.0, 15.0}};
        Matrix<Real> m1 = bld.construct(mat, false);
        MatrixBuilder<Complex> bld2 = new MatrixBuilder<>();
        bld2.set(MatrixBuilder.Type.Double, Complex.class);
        Matrix<Complex> c1 = bld2.construct(mat, false);
        System.out.println("Integer test: asColumn: false");
        System.out.println(m1);
        System.out.println(c1);
        System.out.println("Integer test: asColumn: true");
        m1 = bld.construct(mat, true);
        c1 = bld2.construct(mat, true);
        System.out.println(m1);
        System.out.println(c1);
        //Test Integer[][]
        Integer[][] mat2 = {
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}
        };
        bld.set(MatrixBuilder.Type.Integer);
        Matrix<Real> m2 = bld.construct(mat2, false);
        bld2.set(MatrixBuilder.Type.Integer);
        Matrix<Complex> c2 = bld2.construct(mat2, false);
        System.out.println("Double test: asColumn: false");
        System.out.println(m2);
        System.out.println(c2);
        System.out.println("Integer test: asColumn: true");
        m2 = bld.construct(mat2, true);
        c2 = bld2.construct(mat2, true);
        System.out.println(m2);
        System.out.println(c2);
        //Test String[][]
        String[][] mat3 = {
                {"1", "2 + 4i", "6.89 - 3.4i"},
                {"2 + 4.5i", "7", "8"}
        };
        MatrixBuilder<Complex> bld3 = new MatrixBuilder<>(MatrixBuilder.Type.String, Complex.class, false, mat3);
        Matrix<Complex> c3 = bld3.create();
        System.out.println("String test: asColumn: false");
        System.out.println(c3);
        System.out.println("String test: asColumn: true");
        bld3 = new MatrixBuilder<>(MatrixBuilder.Type.String, Complex.class, true, mat3);
        c3 = bld3.create();
        System.out.println(c3);
    }

    @Test
    public void creationTest() {
        //First and Third constructor
        Vector<Real> v1 = new Vector<>(new Real(1), new Real(2), new Real(3));
        Vector<Real> v2 = new Vector<>(new Real(4), new Real(5), new Real(6));
        Matrix<Real> m1 = new Matrix<>(v1, v2);
        System.out.println("asColumn: false");
        System.out.println(m1);
        m1 = new Matrix<>(true, v1, v2);
        System.out.println("asColumn: true");
        System.out.println(m1);
        //Second and Fourth constructor
        Real[][] data = new Real[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                data[i][j] = new Real(i * j);
            }
        }
        m1 = new Matrix<>(data);
        System.out.println("asColumn: false");
        System.out.println(m1);
        System.out.println("asColumn: true");
        m1 = new Matrix<>(true, data);
        System.out.println(m1);
    }

    @Test
    public void basicOperations() {
        MatrixBuilder<Real> bld = new MatrixBuilder<>();
        bld.set(MatrixBuilder.Type.Double, Real.class);
        Double[][] mat = {{0.0, 3.0, -6.0},
                {2.0, -4.0, 8.0},
                {5.0, -10.0, 15.0}};
        Matrix<Real> m1 = bld.construct(mat, false);
        Double[][] mat2 = {{1.0, 1.0, 1.0},
                {-1.0, -1.0, -1.0},
                {2.0, 0.0, -2.0}};
        Matrix<Real> m2 = bld.construct(mat2, false);
        m1.add(m2);
        Double[][] mat3 = {{1.0, 4.0, -5.0},
                {1.0, -5.0, 7.0},
                {7.0, -10.0, 13.0}};
        assertThat(m1).isEqualTo(bld.construct(mat3, false));
        m1.subtract(m2);
        assertThat(m1).isEqualTo(bld.construct(mat, false));
        Double[][] mat4 = {{0.666, 0.5, 0.0},
                {0.333, 1.0, -0.4},
                {0.0, 0.5, -0.2}};
        Double[][] identity = {{1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0}};
        assertThat(m1.multiply(bld.construct(mat4, false))).isEqualTo(bld.construct(identity, false));
        Vector<Real> v = new Vector<>(new Real(1), new Real(2), new Real(3));
        assertThat(m1.multiply(v)).isEqualTo(new Vector<>(new Real(-12), new Real(18), new Real(30)));
    }

    @Test
    public void otherOperations() {
        MatrixBuilder<Real> bld = new MatrixBuilder<>();
        bld.set(MatrixBuilder.Type.Double, Real.class);
        Double[][] mat = {{0.0, 3.0, -6.0},
                {2.0, -4.0, 8.0},
                {5.0, -10.0, 15.0}};
        Matrix<Real> m1 = bld.construct(mat, false);
        System.out.println("Original");
        System.out.println(m1);
        System.out.println("Columns");
        m1.addCols(1, 2);
        System.out.println(m1);
        m1.subtractCols(1, 2);
        System.out.println("\n" + m1);
        m1.interchangeCols(0, 1);
        System.out.println("\n" + m1);
        System.out.println("Rows");
        m1.addRows(0, 2);
        System.out.println("\n" + m1);
        m1.subtractRows(0, 2);
        System.out.println("\n" + m1);
        m1.interchangeRows(0, 2);
        m1.interchangeRows(1, 1);
        System.out.println("\n" + m1);
        assertThat(m1.contains(new Real(70.0))).isFalse();
        assertThat(m1.contains(new Real(3.0))).isTrue();
        m1.transpose();
        System.out.println("\n" + m1);
        m1.scaleColumn(1, new Real(0));
        m1.scaleRow(2, new Real(3));
        System.out.println("\n" + m1);
    }

}
