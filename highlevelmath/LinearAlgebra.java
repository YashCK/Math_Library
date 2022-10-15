package highlevelmath;

import java.net.PasswordAuthentication;

public class LinearAlgebra {

    static int calcDeterminant(int[][] matrix){
        return 0;
    }

    public static void main(String[] args){

    }
    
    static int[] gaussianElimination(int[][] matrix){
        return matrix[0];
    }

    static int[] getPivots(int[][] matrix){
        return matrix[0];
    }

    static int[][] rowReduction(int[][] matrix){
        
        /*
         * 
         * for every row make sure the leading entry is a 1
         * 
         * 
         * RREF --> 
         * 1. All nonzero rows are above any rows of all zeros.
         * 2. Each leading entry of a row is in a column to the right of the leading entry of
         * the row above it.
         * 3. All entries in a column below a leading entry are zeros.
         * 4. The leading entry in each nonzero row is 1.
         * 5. Each leading 1 is the only nonzero entry in its column.
         * 
         * STEPS:
         * 1. Begin with the leftmost nonzero column. This is a pivot column. The pivot
         * position is at the top.
         * 2. Select a nonzero entry in the pivot column as a pivot. If necessary, interchange
         * rows to move this entry into the pivot position.
         * 3. Use row replacement operations to create zeros in all positions below the pivot.
         * 4. Cover (or ignore) the row containing the pivot position and cover all rows, if any,
         * above it. Apply steps 1–3 to the submatrix that remains. Repeat the process until
         * there are no more nonzero rows to modify.
         * 5. Beginning with the rightmost pivot and working upward and to the left, create 
         * zeros above each pivot. If a pivot is not 1, make it 1 by a scaling operation.
         * 
         * 
         */

        for(int rowNum = 0; rowNum < matrix.length; rowNum++){
            int[] row = matrix[rowNum];

            //If pivot is 0 then interchange rows with one that has a pivot in that location
            if(row[0] == 0){
                for(int i = rowNum + 1; i < matrix.length; i++){
                    if(matrix[i][0] != 0){
                        interchangeRows(matrix, rowNum, i);
                        break;
                    }
                }
            }

        }

        return matrix;
    }

    static void interchangeRows(int[][] matrix, int row1, int row2){
        int[] firstRow = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = firstRow;
        // return matrix;
    }

    static void scaleSequence(int[] rowOrColumn, int scale){
        for(int i = 0; i < rowOrColumn.length; i++){
            rowOrColumn[i] *= scale;
        }
        // return rowOrColumn;
    }

    static int[] findColumn(int[][] matrix, int columnNum){
        int[] column = new int[matrix[0].length];
        for(int rowNum = 0; rowNum < matrix.length; rowNum++){
            column[rowNum] = matrix[rowNum][columnNum];
        }
        return column;
    }


}