package highlevelmath;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.structures.Matrix;
import highlevelmath.constructs.structures.Matx;
import highlevelmath.constructs.structures.Vec;
import highlevelmath.constructs.structures.Vector;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinAlg {

    private static final Map<Class<? extends Matx>, VectorFactory> vectorFactories;
    private static final Map<Class<? extends Matx>, MatrixFactory> matrixFactories;
    private static Class<? extends Matx> cls;
    private static Object eObject;
    private static Object sObject;

    //Helper

    static {
        //Initialize Factory Maps
        vectorFactories = new HashMap<>();
        matrixFactories = new HashMap<>();
        //Initialize Maps to contain Factories for standard Matrix
        cls = Matrix.class;
        vectorFactories.put(cls, LinAlg::createFactoryVector);
        matrixFactories.put(cls, LinAlg::createFactoryMatrix);
        //Initialize other parameters
        eObject = new Real(0);
        sObject = new Real(1);
    }

    public void register(Class<? extends Matx<?, ?>> cls, VectorFactory vFact, MatrixFactory mFact){
        if(!vectorFactories.containsKey(cls)){
            vectorFactories.put(cls, vFact);
            matrixFactories.put(cls, mFact);
        }
    }

    public static <T extends Field<T>, S extends Field<S>> void declare(Class<? extends Matx> cls,
                                                                        T eZero, S sZero){
        LinAlg.cls = cls;
        eObject = eZero;
        sObject = sZero;
    }
    private static <T extends Field<T>> Matx<?, ?> createFactoryMatrix(boolean asColumn, T[][] data){
        return (new Matrix<T>(asColumn, data));
    }

    private static <T extends Field<T>> Vec<?, ?> createFactoryVector(T[] data){
        return (new Vector<T>(data));
    }

    private static <T extends Field<T>> Matx<?, ?> createMatrix(boolean asColumn, T[][] data){
        return matrixFactories.get(cls).create(asColumn, data);
    }

    private static <T extends Field<T>> Vec<?, ?> createVector(T[] data){
        return vectorFactories.get(cls).create(data);
    }

    private static <T extends Field<T>> T eZero(){
        return ((T) eObject).getZero();
    }

    private static <S extends Field<S>> S sZero(){
        return ((S) sObject).getZero();
    }

    private static <T extends Field<T>> T eOne(){
        return ((T) eObject).getOne();
    }

    private static <S extends Field<S>> S sOne(){
        return ((S) sObject).getOne();
    }


    //Algorithms

    /*
     * This function reduces the matrix passed in to its RREF state.
     *   - All nonzero rows are above any rows of all zeros.
     *   - Each leading entry of a row is in a column to the right of the leading entry of the row above it.
     *   - All entries in a column below a leading entry are zeros.
     *   - The leading entry in each nonzero row is 1.
     *   - Each leading 1 is the only nonzero entry in its column.
     */
//    /**
//     * This function reduces the matrix passed in to its REF state
//     * @param matrix the matrix to be row reduced
//     */
//    public void ref(Matx<?, ?> matrix){
//        int totalCols = matrix.ncols();
//        int totalRows = matrix.nrows();
//        int pivotRow = 0;
//        //Reduces Matrix to REF State
//        for(int column = 0; column < totalRows; column++){
//            //Scale each row such that its first entry is 1 | provided it is not 0 initially
//            int[] beginWZeros = new int[totalRows];
//            int z = 0;
//            for(int row = pivotRow; row < totalRows; row++){
//                T value = matrix.get(row, column);
//                if(!value.equals(e.getZero())){
////                        matrix.scaleRow(row, matrix.getRow(row).scalarInverse(value));
//                } else {
//                    //Add rows that begin with 0 to beginWZeros array
//                    beginWZeros[z] = row + 1;
//                    z++;
//                }
//            }
//            //Put rows of leading 0s as the bottom rows of the matrix
//            int buffer = 0;
//            for(int r : beginWZeros){
//                if(r == 0){
//                    break;
//                }
//                matrix.interchangeRows(r - 1, totalRows - 1 - buffer);
//                buffer++;
//            }
//            //Use row replacement operations to create zeros in all positions below the pivot
//            //If Pivot Is 0 --> column += 1
//            while(matrix.get(pivotRow, column).equals(e.getZero())){
//                column++;
//            }
//            for(int i = pivotRow + 1; i < totalRows; i++){
//                //Row X = Row X - Row Pivot
//                if(!matrix.get(i, column).equals(e.getZero())){
//                    matrix.subtractRows(i, pivotRow);
//                }
//            }
//            pivotRow++;
//        }
//        System.out.println(matrix);
//    }
//
//    public void rref(Matx<?, ?> matrix){
//        ref(matrix);
//        //Reduces Matrix to RREF State
//        // int column = totalCols - 1;
//        for(int row = matrix.nrows() - 1; row > -1; row--){
//            //Find pivots
//            int column = 0;
//            T val = matrix.get(row, column);
//            while(val.equals(e.getZero())){
//                column++;
//                if(column >= matrix.ncols()){
//                    row--;
//                    break;
//                }
//                if(row < 0){
//                    break;
//                }
//                val = matrix.get(row, column);
//            }
//            if(row < 0){
//                break;
//            }
//            //Scale pivot to be 1 | Scale positions above it correspondingly
//            for(int r = row; r > -1; r--){
//                T pivot = matrix.get(r, column);
////                    matrix.scaleRow(r, matrix.getRow(row).scalarInverse(pivot));
//            }
//            //Create column of 0s above each pivot
//            for(int r = row - 1; r > -1; r--){
//                //Row R = Row R - Row Pivot
//                matrix.subtractRows(r, row);
//            }
//        }
//    }

    //Utility
    /**
     * Returns the NxN Identity Matrix
     * @param n The number of rows or columns the matrix should have
     * @return a Matrix with 1s along the diagonal
     */
    public static <T extends Field<T>> Matx<?, ?> identity(int n){
        T[][] im = (T[][]) Array.newInstance(eObject.getClass(), n, n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                im[i][j] = (i == j) ? eOne() : eZero();
            }
        }
        return createMatrix(false, im);
    }

    /**
     * Returns the NxM Zero Matrix
     * @param n The number of rows the matrix should have
     * @param m The number of columns the matrix should have
     * @return a Matrix with 0s in every position
     */
    public static <T extends Field<T>> Matx<?, ?> zeros(int n, int m){
        T[][] data = (T[][]) Array.newInstance(eObject.getClass(), n, m);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                data[i][j] = eZero();
            }
        }
        return createMatrix(false, data);
    }

    /**
     * Returns the NxN Zero Matrix with values along its diagonal
     * @param n The number of rows and cols the matrix should have
     * @param value The value for each entry of the diagonal
     */
    public static <T extends Field<T>> Matx<?, ?> diagonal(int n, T value){
        T[][] data = (T[][]) Array.newInstance(eObject.getClass(), n, n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                data[i][j] = (i == j) ? value : eZero();
            }
        }
        return createMatrix(false, data);
    }

    public static <T extends Field<T>> Matx<?, ?> transpose(Matx<T, ?> matrix){
        Matx<T, ?> copy = matrix.copy();
        T[][] transpose = (T[][]) Array.newInstance(eObject.getClass(), matrix.ncols(), matrix.nrows());
        for (int row = 0; row < transpose.length; row++) {
            for (int col = 0; col < transpose[0].length; col++) {
                transpose[row][col] = copy.get(col, row);
            }
        }
        return createMatrix(false, transpose);
    }

    //Miscellaneous Methods
    private int findGCD(int big, int small) {
        if (big % small == 0) {
            return small;
        }
        return findGCD(small, big % small);
    }

    private int findLCM(int a, int b) {
        if (a > b) {
            return a * b / findGCD(a, b);
        } else {
            return a * b / findGCD(b, a);
        }

    }

    @FunctionalInterface
    private interface VectorFactory<T> {
        <T extends Field<T>> Vec<?, ?> create(T[] data);
    }

    @FunctionalInterface
    private interface MatrixFactory<T> {
        <T extends Field<T>> Matx<?, ?> create(boolean asColumn, T[][] data);
    }

}
