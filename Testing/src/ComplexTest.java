import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.UndefinedException;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ComplexTest {

    @Test
    public void creationTest() {
        try {
            Complex c = new Complex(5, 43);
            Complex c2 = new Complex(" 4.5    + 3.558  i");
            Complex c3 = new Complex("4.5");
            Complex c4 = new Complex("4.5i");
            Complex c5 = new Complex("5.5 i");
            Complex c6 = new Complex(" -4.5    - 3.558  i");
            Complex c7 = new Complex("-4.5");
            Complex c8 = new Complex("-4.5i");
            Complex c9 = new Complex("i");
            Complex c10 = new Complex("-i");
            Complex c11 = new Complex("4.0 + i");
            Complex c12 = new Complex("5.0 - i");
            assertThat(c.toString()).isEqualTo("5.0 + 43.0i");
            assertThat(c2.toString()).isEqualTo("4.5 + 3.55i");
            assertThat(c3.toString()).isEqualTo("4.5 + 0.0i");
            assertThat(c4.toString()).isEqualTo("0.0 + 4.5i");
            assertThat(c5.toString()).isEqualTo("0.0 + 5.5i");
            assertThat(c6.toString()).isEqualTo("-4.5 - 3.55i");
            assertThat(c7.toString()).isEqualTo("-4.5 + 0.0i");
            assertThat(c8.toString()).isEqualTo("0.0 - 4.5i");
            assertThat(c9.toString()).isEqualTo("0.0 + 1.0i");
            assertThat(c10.toString()).isEqualTo("0.0 - 1.0i");
            assertThat(c11.toString()).isEqualTo("4.0 + 1.0i");
            assertThat(c12.toString()).isEqualTo("5.0 - 1.0i");
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
    public void operationTest() {
        try {
            Complex c1 = new Complex(6, 4);
            Complex c2 = new Complex(3, 5);
            assertThat(c1.real()).isEqualTo(6);
            assertThat(c1.imag()).isEqualTo(4);
            c1.add(c2);
            assertThat(c1.toString()).isEqualTo("9.0 + 9.0i");
            c1.subtract(c2);
            assertThat(c1.toString()).isEqualTo("6.0 + 4.0i");
            c1.multiply(c2);
            assertThat(c1.toString()).isEqualTo("-2.0 + 42.0i");
            c2.multiply(new Complex(6, 4));
            assertThat(c1.toString()).isEqualTo(c2.toString());
            assertThat(c1).isEqualTo(c2);
            c1.divide(c2);
            assertThat(c1.toString()).isEqualTo("1.0 + 0.0i");
            c1.divide(new Complex(6, 4));
            c1.multiply(new Complex(6, 4));
            assertThat(c1.toString()).isEqualTo("1.0 + 0.0i");
            assertThat(c1.conjugate().toString()).isEqualTo("1.0 + 0.0i");
            c1 = new Complex(6, 4);
            assertThat(c1.conjugate().toString()).isEqualTo("6.0 - 4.0i");
        } catch (UndefinedException e) {
            assertThat(1).isEqualTo(2);
            System.out.println("A UndefinedException was found");
        }
    }

    @Test
    public void generalTest() {
        Complex c = new Complex(5, 6);
        assertThat(c).isEqualTo(new Complex(5, 6));
        c.setReal(4);
        assertThat(c.real()).isEqualTo(4);
        c.setImag(9);
        assertThat(c.imag()).isEqualTo(9);
        assertThat(c).isEqualTo(new Complex(4, 9));
        assertThat(c).isNotEqualTo(new Complex(5, 6));
    }

}