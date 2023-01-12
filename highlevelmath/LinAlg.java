package highlevelmath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.structures.Matx;
import highlevelmath.constructs.structures.Vec;
import highlevelmath.constructs.util.ArrFactory;
import highlevelmath.constructs.util.MatxFactory;
import highlevelmath.constructs.util.TwoArrFactory;
import highlevelmath.constructs.util.VecFactory;


public class LinAlg<T, S, V extends Vec<T, S>, M extends Matx<T,S>> {

    private VecFactory<T, V> vec;
    private MatxFactory<V, M> mat;
    private ArrFactory<T> arr;
    private ArrFactory<V> vecArr;
    private ArrFactory<M> matArr;
    private TwoArrFactory<T> twoArr;
    
    private Field<T> e;
    private Field<S> s;
    
    //Constructor
    public LinAlg(VecFactory<T, V> vec, MatxFactory<V, M> mat, ArrFactory<T> arr, ArrFactory<V> vecArr, ArrFactory<M> matArr, TwoArrFactory<T> twoArr){
        this.vec = vec;
        this.mat = mat;
        this.arr = arr;
        this.vecArr = vecArr;
        this.matArr = matArr;
        this.twoArr = twoArr;
        V tempVec = vec.create(arr.create(0));
        e = tempVec.element;
        s = tempVec.scalar;
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

    public void ref(M matrix){
        int totalCols = matrix.ncols();
        int totalRows = matrix.nrows();
        int pivotRow = 0;
        //Reduces Matrix to REF State
        for(int column = 0; column < totalRows; column++){
            //Scale each row such that its first entry is 1 | provided it is not 0 initially
            int[] beginWZeros = new int[totalRows];
            int z = 0;
            for(int row = pivotRow; row < totalRows; row++){
                double value = matrix.get(row, column);
                if(value != 0){
                    matrix.scaleRow(row, 1/value);
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
            while(matrix.get(pivotRow, column) == 0){
                column++;
            }
            for(int i = pivotRow + 1; i < totalRows; i++){
                //Row X = Row X - Row Pivot
                if(matrix.get(i, column) != 0){
                    matrix.subtractRows(i, pivotRow);
                }
            }
            pivotRow++;
        }
        System.out.println(matrix);
    }

    public void rref(M matrix){
        ref(matrix);
        //Reduces Matrix to RREF State
        // int column = totalCols - 1;
        for(int row = totalRows - 1; row > -1; row--){
            //Find pivots
            int column = 0;
            double val = matrix.get(row, column);
            while(val == 0){
                column++;
                if(column >= totalCols){
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
                double pivot = matrix.get(r, column);
                matrix.scaleRow(r, 1/pivot);
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
    public M identity(int n){
        T[][] im = twoArr.create(n, n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                im[i][j] = (i == j) ? e.getIdentity() : e.getZero();
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
    
}