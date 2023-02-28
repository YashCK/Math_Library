import highlevelmath.constructs.structures.Complex;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.UndefinedException;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ComplexTest {

    @Test
    public void creationTest(){
        try {
            Complex c = new Complex(5, 43);
            Complex c2 = new Complex(" 4.5    + 3.558  i");
            Complex c3 = new Complex("4.5");
            Complex c4 = new Complex("4.5i");
            Complex c5 = new Complex("5.5 i");
            assertThat(c.toString()).isEqualTo("5.0 + 43.0i");
            assertThat(c2.toString()).isEqualTo("4.5 + 3.558i");
            assertThat(c3.toString()).isEqualTo("4.5 + 0.0i");
            assertThat(c4.toString()).isEqualTo("0.0 + 4.5i");
            assertThat(c5.toString()).isEqualTo("0.0 + 5.5i");
        } catch (ConstructFormatException e) {
            assertThat(1).isEqualTo(2);
            System.out.println("A ConstructFormatException was found");
        }
        try {
            Complex c = new Complex("5..6");
        } catch (ConstructFormatException e) {
            assertWithMessage("Passed Invalid Argument Test 1");
        }
        try {
            Complex c = new Complex("6.6ii");
        } catch (ConstructFormatException e) {
            assertWithMessage("Passed Invalid Argument Test 2");
        }
    }

    @Test
    public void operationTest(){
        try {
            Complex c1 = new Complex(6, 4);
            Complex c2 = new Complex(3, 5);
            assertThat(c1.getReal()).isEqualTo(6);
            assertThat(c1.getImag()).isEqualTo(4);
            assertThat(Complex.add(c1, c2).toString()).isEqualTo("9.0 + 9.0i");
            assertThat(Complex.subtract(c1, c2).toString()).isEqualTo("3.0 - 1.0i");
            assertThat(Complex.mul(c1, c2).toString()).isEqualTo("-2.0 + 42.0i");
            assertThat(Complex.mul(c1, c2)).isEqualTo(Complex.mul(c2, c1));
            assertThat(Complex.div(c1, c2).toString()).isEqualTo("1.11 - 0.52i");
            assertThat(Complex.mul(Complex.div(c1, c2), c2).toString()).isEqualTo("6.0 + 4.0i");
            assertThat(Complex.conjugate(c1).toString()).isEqualTo("6.0 - 4.0i");
        } catch(UndefinedException e) {
            assertThat(1).isEqualTo(2);
            System.out.println("A UndefinedException was found");
        }
    }

    @Test
    public void generalTest(){
        Complex c = new Complex(5, 6);
        assertThat(c).isEqualTo(new Complex(5, 6));
        c.setReal(4);
        assertThat(c.getReal()).isEqualTo(4);
        c.setIm(9);
        assertThat(c.getImag()).isEqualTo(9);
        assertThat(c).isEqualTo(new Complex(4, 9));
        assertThat(c).isNotEqualTo(new Complex(5, 6));
    }

}