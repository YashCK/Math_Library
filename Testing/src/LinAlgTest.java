import highlevelmath.LinAlg;
import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.structures.Matrix;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.UndefinedException;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
public class LinAlgTest {

    @Test
    public void generalTest() throws ConstructFormatException {
        System.out.println("Default: ");
        System.out.println(LinAlg.identity(4));
        LinAlg.declare(Matrix.class, new Complex(0, 0), new Complex(1, 0));
        System.out.println("Changed to Complex: ");
        System.out.println(LinAlg.identity(4));
    }

}
