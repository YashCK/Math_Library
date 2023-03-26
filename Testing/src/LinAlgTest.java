import highlevelmath.LinAlg;
import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.structures.Matrix;
import highlevelmath.constructs.structures.Vector;
import org.junit.jupiter.api.Test;

public class LinAlgTest {

    @Test
    public void utilityTest() {
        //Identity
        System.out.println("Default: ");
        System.out.println(LinAlg.identity(4));
        LinAlg.declare(Matrix.class, new Complex(0, 0), new Complex(1, 0));
        System.out.println("Changed to Complex: ");
        System.out.println(LinAlg.identity(4));
        //Zeros
        LinAlg.declare(Matrix.class, new Real(0), new Real(1));
        System.out.println("Default: ");
        System.out.println(LinAlg.zeros(4, 4));
        LinAlg.declare(Matrix.class, new Complex(0, 0), new Complex(1, 0));
        System.out.println("Changed to Complex: ");
        System.out.println(LinAlg.zeros(4, 6));
        //Diagonal
        LinAlg.declare(Matrix.class, new Real(0), new Real(1));
        System.out.println("Default: ");
        System.out.println(LinAlg.diagonal(4, new Real(0.7)));
        LinAlg.declare(Matrix.class, new Complex(0, 0), new Complex(1, 0));
        System.out.println("Changed to Complex: ");
        System.out.println(LinAlg.diagonal(4, new Complex(3, 7)));
        //Tranpose
        LinAlg.declare(Matrix.class, new Real(0), new Real(1));
        Vector<Real> v1 = new Vector<>(new Real(1), new Real(2), new Real(3));
        Vector<Real> v2 = new Vector<>(new Real(4), new Real(5), new Real(6));
        Matrix<Real> m1 = new Matrix<>(v1, v2);
        System.out.println("Tranpose: ");
        System.out.println(LinAlg.transpose(m1));
    }

}
