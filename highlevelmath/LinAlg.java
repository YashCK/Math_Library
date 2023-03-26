package highlevelmath;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.abstract_algebra.fields.Real;
import highlevelmath.constructs.structures.Matrix;
import highlevelmath.constructs.structures.Matx;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class LinAlg {

    private static final Map<Class<? extends Matx<?, ?>>, Factory> mapFactories;
    private static Class<? extends Matx<?, ?>> cls;
    private static Class<? extends Field<?>> elementClass;
    private static Class<? extends Field<?>> scalarClass;
    private static Object eZero;
    private static Object eOne;
    private static Object sZero;
    private static Object sOne;

    //Utility

    static {
        mapFactories = new HashMap<>();
        cls = (Class<? extends Matx<?, ?>>) Matrix.class;
        elementClass = Real.class;
        scalarClass = Real.class;
        eZero = new Real(0);
        sZero = new Real(0);
        eOne = new Real(1);
        sOne = new Real(1);
        mapFactories.put(cls, LinAlg::createFactoryMatrix);
    }

    public void register(Class<? extends Matx<?, ?>> cls, Factory fact){
        mapFactories.put(cls, fact);
    }

    public <T extends Field<T>, S extends Field<S>> void declare(Class<? extends Matx<T, S>> cls,
                                                                 T eZero, S sZero){
        this.cls = cls;
        elementClass = (Class<? extends Field<?>>) eZero.getClass();
        scalarClass = (Class<? extends Field<?>>) sZero.getClass();
        this.eZero = eZero;
        this.sZero = sZero;
        eOne = eZero.getOne();
        sOne = sZero.getOne();
    }
    private static <T extends Field<T>> Matx<?, ?> createFactoryMatrix(boolean asColumn, T[][] data){
        return (new Matrix<T>(asColumn, data));
    }

    private <T extends Field<T>> Matx<?, ?> createMatrix(boolean asColumn, T[][] data){
        return mapFactories.get(cls).create(asColumn, data);
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
    /**
     * This function reduces the matrix passed in to its REF state
     * @param matrix the matrix to be row reduced
     */
    public void ref(Matx<?, ?> matrix){
        int totalCols = matrix.ncols();
        int totalRows = matrix.nrows();
        int pivotRow = 0;
        //Reduces Matrix to REF State
        for(int column = 0; column < totalRows; column++){
            //Scale each row such that its first entry is 1 | provided it is not 0 initially
            int[] beginWZeros = new int[totalRows];
            int z = 0;
            for(int row = pivotRow; row < totalRows; row++){
                T value = matrix.get(row, column);
                if(!value.equals(e.getZero())){
//                        matrix.scaleRow(row, matrix.getRow(row).scalarInverse(value));
                } else {
                    //Add rows that begin with 0 to beginWZeros array
                    beginWZeros[z] = row + 1;
                    z++;
                }
            }
            //Put rows of leading 0s as the bottom rows of the matrix
            int buffer = 0;
            for(int r : beginWZeros){
                if(r == 0){
                    break;
                }
                matrix.interchangeRows(r - 1, totalRows - 1 - buffer);
                buffer++;
            }
            //Use row replacement operations to create zeros in all positions below the pivot
            //If Pivot Is 0 --> column += 1
            while(matrix.get(pivotRow, column).equals(e.getZero())){
                column++;
            }
            for(int i = pivotRow + 1; i < totalRows; i++){
                //Row X = Row X - Row Pivot
                if(!matrix.get(i, column).equals(e.getZero())){
                    matrix.subtractRows(i, pivotRow);
                }
            }
            pivotRow++;
        }
        System.out.println(matrix);
    }

    public void rref(Matx<?, ?> matrix){
        ref(matrix);
        //Reduces Matrix to RREF State
        // int column = totalCols - 1;
        for(int row = matrix.nrows() - 1; row > -1; row--){
            //Find pivots
            int column = 0;
            T val = matrix.get(row, column);
            while(val.equals(e.getZero())){
                column++;
                if(column >= matrix.ncols()){
                    row--;
                    break;
                }
                if(row < 0){
                    break;
                }
                val = matrix.get(row, column);
            }
            if(row < 0){
                break;
            }
            //Scale pivot to be 1 | Scale positions above it correspondingly
            for(int r = row; r > -1; r--){
                T pivot = matrix.get(r, column);
//                    matrix.scaleRow(r, matrix.getRow(row).scalarInverse(pivot));
            }
            //Create column of 0s above each pivot
            for(int r = row - 1; r > -1; r--){
                //Row R = Row R - Row Pivot
                matrix.subtractRows(r, row);
            }
        }
    }

    //Utility
    /**
     * Returns the NxN Identity Matrix
     * @param n The number of rows or columns the matrix should have
     * @return a Matrix with 1s along the diagonal
     */
    public <T> Matx<?, ?> identity(int n){
        T[][] im = (T[][]) Array.newInstance(elementType, n, n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                im[i][j] = (i == j) ? (T) eOne : (T) eZero;
            }
        }
        V[] identityM = vecArr.create(im.length);
        for(int i = 0; i < im.length; i++){
            identityM[i] = vec.create(im[i]);
        }
        return mat.create(identityM, false);
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
    private interface Factory<T> {
        <T extends Field<T>> Matx<?, ?> create(boolean asColumn, T[][] data);
    }

}
