import highlevelmath.*;
import highlevelmath.constructs.structures.Complex;
import highlevelmath.constructs.structures.Matrix;
import highlevelmath.constructs.structures.Vector;
import highlevelmath.constructs.util.NoExistingSolutionException;
import highlevelmath.constructs.util.OperationUndefinedException;


public class Testing {
    public static void main(String[] args) throws OperationUndefinedException, NoExistingSolutionException{

        // VecFactory<Double, Vector> v = Vector::new;
        // Double[] first = {2.0, 4.0, 6.0 , 8.0};
        // System.out.println(v.create(first));
        // ArrFactory<Vector> arrV = Vector[]::new;
        // Vector[] t1 = arrV.create(3);
        // System.out.println(Arrays.toString(t1));

        // ArrayFactory<Double> ad = Double[]::new;
        // DoubleArrayFactory<Double> abc = (rows, cols) -> new Double[rows][cols];
        // TwoArrFactory<Double> abc = (r, c) -> new Double[r][c];

        LinAlg<Double, Double, Vector, Matrix> linalg = new LinAlg<>(Vector::new, Matrix::new, Double[]::new, Vector[]::new, Matrix[]::new, (r, c) -> new Double[r][c]);

        Matrix m1 = linalg.identity(2);

        // System.out.println(m1);

        // Double[][] mat = {  {0.0, 3.0, -6.0, 6.0, 4.0, -5.0},
        //                     {3.0, -7.0, 8.0, -5.0, 8.0, 9.0}, 
        //                     {3.0, - 9.0, 12.0, -9.0, 6.0, 15.0}};
        // double[][] mat2 = { {1, 6, 2, -5, -2},
        //                     {0, 0, 2, -8, -1}, 
        //                     {0, 0, 0, 0,  1}};
        // double[] d1 = {0, 3, -6, 6, 4, -5};
        // Vector v1 = new Vector(d1);
        // double[] d2 = {3, -7, 8, -5, 8, 9};
        // Vector v2 = new Vector(d2);
        // double[] d3 = {3, -9, 12, -9, 6, 15};
        // Vector v3 = new Vector(d3);
        // Vector[] mat = {v1, v2, v3};
        // Matrix tm1 = new Matrix(mat, true);
        // System.out.println(tm1);
        // Matrix test2 = new Matrix(mat2, true);
        // System.out.println(test2);
        // Matrix test1 = new Matrix(mat2);
        // System.out.println(test1);
        // // System.out.println("Start of Row Reduction: ");
        // LinAlg.rowReduction(test1);
        // // test1.set(2, 4, 9.9);
        // // System.out.println(test1);
        // // LinAlg.getPivots(test1);
        // double[] d1 = {-4, 3, 7};
        // Vector vtest = new Vector(d1);
        // Vector sol = LinAlg.gaussianElimination(test1, vtest);
        // System.out.println("Solution: " + sol);

        /*
         * Testing Matrix Class
         */
        // double[][] first = {
        //     {1, 2, 3, 4, 5, 6},
        //     {2, 4, 5, 6, 7},
        //     {0, 4, 4, 3, 9, 9, 8}
        // };

        // double[][] second = {
        //     {4, 5, 2, 9, 22, 8},
        //     {1, 3, 4, 5, 8},
        //     {5, 6, 2, 4, 9, 11}
        // };

        // Matrix m1 = new Matrix(first);
        // Matrix m2 = new Matrix(second);
        // Matrix m3 = new Matrix(first, true);
        // System.out.println("Matrix 1: " + m1);
        // System.out.println("Matrix 2: " + m2);
        // System.out.println("Matrix 3: " + m3);

        // System.out.println(m1.multiply(m3));
        // System.out.println("m1 : " + m1);

        // double[][] another = {{1 , 2, -1},
        //                     {0, -5, 3}};
        // double[] anotherVec<T, S, F>= {4, 3, 7};
        // double[][] another = {{2, -3},
        //                     {8, 0},
        //                     {-5, 2}};
        // double[] anotherVec<T, S, F>= {4, 7};
        // Matrix a = new Matrix(another);
        // Vector v = new Vector(anotherV);
        // System.out.println(a.multiply(v));

        // m1.add(m1);s
        // System.out.println("M1 = M1 + M1: " + m1);
        // m1.add(m2);
        // m1.subtract(m3);
        // System.out.println("M1 = M1 - M3: " + m1);
        // m1.subtract(m2);
        // m3.multiply(m3);
        // System.out.println("M3 = M3 * M3: " + m3);
        // m1.modulus(m3);
        // System.out.println("M1 = M1 % M3: " + m1);
        // System.out.println(m1.equals(m3));
        // m1.addRows(1, 2);
        // System.out.println(m1);
        // m1.addRows(3, 5);
        // m1.addColumns(1, 2);
        // m1.addColumns(8, 2);
        // System.out.println(m1);
        // m1.subtractRows(0, 2);
        // m1.subtractColumns(3, 5);
        // m1.interchangeRows(0, 2);
        // m1.interchangeCols(1, 6);
        // System.out.println(m1);
        // m2.scaleRow(1, 2);
        // System.out.println(m2);
        // m2.scaleRow(1, 0.5);
        // System.out.println(m2);
        // m1.scaleColumn(1, 3);
        // System.out.println(m1);
        // m1.scaleColumn(1, 0.5);
        // int[] randRow = {2, 4, 5, 6, 8};
        // double[] randRow = {2.5, 4.4, 5.2, 6.9, 8.3};
        // double[] randRow = {2.5, 4.4, 5.2, 6.9, 8.3, 4.5, 6.7, 7.8};
        // m1.setRow(2, randRow);
        // int[] randCol = {2, 4, 5, 6, 8};
        // int[] randCol = {2, 4, 5};
        // double[] randCol = {2.5, 4.4, 5.2};
        // m1.setColumn(0, randCol);
        // m1.setIndex(0, 0, 9875.3);
        // m1.setIndex(1, 1000, 2368);
        // m1.setMultiline(false);
        // System.out.println(m1);
        // System.out.println(Arrays.toString(m2.getRow(0)));
        // System.out.println(Arrays.toString(m2.getColumn(1)));
        // System.out.println(Arrays.toString(m2.getRow(100)));
        // System.out.println(Arrays.toString(m2.getColumn(100)));
        // System.out.println(m2.getIndex(0, 0));
        // System.out.println(m2.getIndex(100, 100));
        // System.out.println(m2.contains(4));
        // System.out.println(m2.contains(100));
        // System.out.println(m2.contains(9.0));

        /*
         * Testing Vector Class
         */
        // Double[] first = {2.0, 4.0, 6.0 , 8.0};
        // Double[] second = {1.0, 3.0, 5.0, 7.0};
        // // Double[] third = {1.0, 2.0, 3.0};
        // Vector v1 = new Vector(first);
        // Vector v2 = new Vector(second);
        // // Vector v3 = new Vector(third);
        // // Vector v4 = new Vector(first);
        // System.out.println("Vector 1: " + v1);
        // // System.out.println("Vector 2: " + v2);
        // // System.out.println("Vector 3: " + v3);
        // // v1.add(v2);
        // v1.subtract(v2);
        // System.out.println("Vector 1: " + v1);
        // v1.dot(v2);
        // v1.modulus(v2);
        // v1.modulus(v3);
        // System.out.println(v1.equals(v2));
        // System.out.println(v1.equals(v4));
        // v1.interchangeCols(0, 3);
        // System.out.println("Vector 1: " + v1);
        // v1.recorrect(3);
        // v2.recorrect(3);
        // System.out.println(v1.dot(v2));

        //Complex class
        // Complex c = new Complex(5, 43);
        // try {
            // Complex c = new Complex(" 4.5    + 3.558  i");
            // Complex c = new Complex("4.5");
            // Complex c = new Complex("4.5i");
        //     System.out.println(c);
        // } catch (ConstructFormatException e) {
        //     System.out.println("Error");
        // }
        // Complex c1 = new Complex(6, 4);
        // Complex c2 = new Complex(3, 5);
        // System.out.println(c1.getReal());
        // System.out.println(c1.getImag());
        // System.out.println(Complex.add(c1, c2));
        // System.out.println(Complex.subtract(c1, c2));
        // System.out.println(Complex.mul(c1, c2));
        // System.out.println(Complex.mul(c2, c1));
        // System.out.println(Complex.div(c1, c2));
        // System.out.println(Complex.mul(Complex.div(c1, c2), c2));
        // System.out.println(Complex.conjugate(c1));
            
    }

}

