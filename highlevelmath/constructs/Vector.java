package highlevelmath.constructs;

public class Vector extends Matrix {

    private double[] vector;

    public Vector(double[] vector) {
        super(new double[vector.length][0]);
        vector = this.matrix[0];
    }

    @Override
    public String toString() {
        String str = "[";
        for(double element : vector){
            str += element == vector[0] ? "" : " ";
            str += truncateDecimal(element, 2);
        }
        str += "]";
        return str;
    }

}
    
