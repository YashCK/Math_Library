import highlevelmath.constructs.structures.CVector;
import highlevelmath.constructs.abstract_algebra.fields.Complex;
import highlevelmath.constructs.structures.Vector;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.OperationUndefinedException;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class CVectorTest {

    @Test
    public void creationTest() throws ConstructFormatException {
        double[] first = {2.0, 4.0, 6.0 , 8.0};
        CVector v1 = new CVector(first);
        CVector v2 = new CVector(1, 2, 3, 5);
        CVector v3 = new CVector(1.0, 2.0, 3, 5.0);
        Complex[] third = {new Complex(1.0, 0), new Complex("1.0 + 4i"), new Complex("5i")};
        CVector v4 = new CVector(third);
        CVector v5 = new CVector("1.0", "1 - 5i", "-2 + 89i", "42i");
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
        System.out.println(v5);
    }
    @Test
    public void operationTest() throws OperationUndefinedException {
        double[] first = {1.0, 2.0, 3.0};
        CVector v1 = new CVector("2.0 + 1i", "4.0 + 0i", "6.0 + 2i" , "8.0 + 3i");
        CVector v2 = new CVector("1.0 + 3i", "3.0 + 4i", "5.0 + 2i", "7.0 + i");
        CVector v3 = new CVector(first);
        CVector v4 = new CVector("2.0 + 1i", "4.0 + 0i", "6.0 + 2i" , "8.0 + 3i");
        v1.add(v2);
        assertThat(v1.toString()).isEqualTo("[3.0 + 4.0i, 7.0 + 4.0i, 11.0 + 4.0i, 15.0 + 4.0i]");
        v1.subtract(v2);
        assertThat(v1.toString()).isEqualTo("[2.0 + 1.0i, 4.0 + 0.0i, 6.0 + 2.0i, 8.0 + 3.0i]");
        assertThat(v1).isEqualTo(v4);
        try {
            v1.add(v3);
        } catch(OperationUndefinedException e){
            assertWithMessage("Passed Exception Test");
        }
        try {
            v1.subtract(v3);
        } catch(OperationUndefinedException e){
            assertWithMessage("Passed Exception Test 2");
        }
        try {
            v1.dot(v3);
        } catch(OperationUndefinedException e){
            assertWithMessage("Passed Exception Test 3");
        }
    }

    @Test
    public void methodsTest() throws OperationUndefinedException {
        Vector v1 = new Vector(2.0, 4.0, 6.0 , 8.0);
        v1.interchangePos(0, 2);
        assertThat(v1).isEqualTo(new Vector(6.0, 4.0, 2.0 , 8.0));
        try {
            v1.interchangePos(1, 10);
        } catch(OperationUndefinedException e){
            assertWithMessage("Passed Exception test 1");
        }
        v1.pad(2);
        assertThat(v1).isEqualTo(new Vector(6.0, 4.0, 2.0 , 8.0, 0.0, 0.0));
        v1.pad(-1);
        assertThat(v1).isEqualTo(new Vector(6.0, 4.0, 2.0 , 8.0, 0.0, 0.0));
        assertThat(v1).isEqualTo(v1.copy());
        assertThat(v1 == v1.copy()).isFalse();
        v1.scale(0.5);
        assertThat(v1).isEqualTo(new Vector(3.0, 2.0, 1.0 , 4.0, 0.0, 0.0));
        v1.scale(2.0);
        assertThat(v1).isEqualTo(new Vector(6.0, 4.0, 2.0 , 8.0, 0.0, 0.0));
        v1.scale(-0.5);
        assertThat(v1).isEqualTo(new Vector(-3.0, -2.0, -1.0 , -4.0, 0.0, 0.0));
        assertThat(v1.get(2)).isEqualTo(-1.0);
        assertThat(v1.length()).isEqualTo(6);
        assertThat((new Vector()).length()).isEqualTo(0);
        v1.set(0, 100.0);
        assertThat(v1.get(0)).isEqualTo(100);
        int i = 0;
        for(Double d : v1){
            assertThat(v1.get(i)).isNotNaN();
            i++;
        }
        assertThat(v1.contains(100.0));
        System.out.println(v1);
        CVector cv = v1.toComplex();
        assertThat(cv).isEqualTo(new CVector("100", "-2", "-1" , "-4.0", "0.0", "0.0"));
    }

    @Test
    public void generalTest(){
        CVector v = new CVector("1.0 + 1i", "0 + 5i", "2 + 3i");
        assertThat(v.toString()).isEqualTo("[1.0 + 1.0i, 0.0 + 5.0i, 2.0 + 3.0i]");
        assertThat((new CVector()).toString()).isEqualTo("[]");
        v.pad(-1);
        assertThat(v.toString()).isEqualTo("[1.0 + 1.0i, 0.0 + 5.0i, 2.0 + 3.0i]");
        v.pad(0);
        assertThat(v.toString()).isEqualTo("[1.0 + 1.0i, 0.0 + 5.0i, 2.0 + 3.0i]");
        v.pad(2);
        assertThat(v.toString()).isEqualTo("[1.0 + 1.0i, 0.0 + 5.0i, 2.0 + 3.0i, 0.0 + 0.0i, 0.0 + 0.0i]");
    }

}
