import highlevelmath.CVector;
import highlevelmath.constructs.structures.Vector;
import highlevelmath.constructs.util.OperationUndefinedException;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class VectorTest {

    @Test
    public void creationTest() {
        double[] first = {2.0, 4.0, 6.0 , 8.0};
        Vector v1 = new Vector(first);
        Vector v2 = new Vector(1, 2, 3, 5);
        Vector v3 = new Vector(1.0, 2.0, 3, 5.0);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
    }
    @Test
    public void operationTest() throws OperationUndefinedException {
        double[] first = {2.0, 4.0, 6.0 , 8.0};
        double[] second = {1.0, 3.0, 5.0, 7.0};
        double[] third = {1.0, 2.0, 3.0};
        Vector v1 = new Vector(first);
        Vector v2 = new Vector(second);
        Vector v3 = new Vector(third);
        Vector v4 = new Vector(first);
        v1.add(v2);
        assertThat(v1).isEqualTo(new Vector(3, 7, 11, 15));
        v1.subtract(v2);
        assertThat(v1).isEqualTo(new Vector(2.0, 4.0, 6.0 , 8.0));
        assertThat(v1).isEqualTo(v4);
        try {
            v1.add(v3);
        } catch(OperationUndefinedException e){
            assertWithMessage("Passed Exception Test");
        }
        assertThat(v1.dot(v2)).isEqualTo(100);
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
        double[] first = {2.0, 4.0, 6.0 , 8.0};
        Vector v = new Vector(first);
        assertThat(v.toString()).isEqualTo("[2.0, 4.0, 6.0, 8.0]");
        assertThat((new Vector()).toString()).isEqualTo("[]");
        v.pad(-1);
        assertThat(v.toString()).isEqualTo("[2.0, 4.0, 6.0, 8.0]");
        v.pad(0);
        assertThat(v.toString()).isEqualTo("[2.0, 4.0, 6.0, 8.0]");
        v.pad(2);
        assertThat(v.toString()).isEqualTo("[2.0, 4.0, 6.0, 8.0, 0.0, 0.0]");
    }
}
