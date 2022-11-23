import java.util.Arrays;

import highlevelmath.*;
import highlevelmath.constructs.*;

public class Testing {
    public static void main(String[] args) throws OperationUndefinedException{
        // double[][] matrix = {  {0, 3, -6, 6, 4, -5},
        //                     {3, -7, 8, -5, 8, 9}, 
        //                     {3, - 9, 12, -9, 6, 15}};
        // LinAlg.rowReduction(matrix);

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

        // Matrix m1 = new Matrix(first, true);
        // Matrix m2 = new Matrix(second);
        // Matrix m3 = new Matrix(first);
        // System.out.println("Matrix 1: " + m1);
        // System.out.println("Matrix 2: " + m2);
        // System.out.println("Matrix 3: " + m3);

        // m1.add(m1);
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
        double[] first = {2.0, 4.0, 6.0 , 8.0};
        double[] second = {1.0, 3.0, 5.0, 7.0};
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        Vector v3 = new Vector();

    }

}

