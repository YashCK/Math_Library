package highlevelmath;

import java.util.Arrays;

public class LinAlg {

    // public static void main(String[] args){
    //     int[][] matrix = {  {0, 3, -6, 6, 4, -5},
    //                         {3, -7, 8, -5, 8, 9}, 
    //                         {3, - 9, 12, -9, 6, 15}};
    //     // highlevelmath.linalg.rowReduction(matrix);
    //     rowReduction(matrix);
    // }

    static int calcDeterminant(int[][] matrix){
        return 0;
    }
    
    static int[] gaussianElimination(int[][] matrix){
        return matrix[0];
    }

    static int[] getPivots(int[][] matrix){
        return matrix[0];
    }

    /*
    * This function reduces the matrix passed in to its RREF state.
    *   - All nonzero rows are above any rows of all zeros.
    *   - Each leading entry of a row is in a column to the right of the leading entry of the row above it.
    *   - All entries in a column below a leading entry are zeros.
    *   - The leading entry in each nonzero row is 1.
    *   - Each leading 1 is the only nonzero entry in its column.
    * 
    *   @param matrix The matrix to row reduce
    * 
    */
    public static int[][] rowReduction(int[][] matrix){

        int currentColumn = 0;
        for(int rowNum = 0; rowNum < matrix.length; rowNum++){
            int[] row = matrix[rowNum];
            //If pivot is 0 then interchange rows with one that has a pivot in that location
            int pivot = row[currentColumn];
            // int pivot = row[findPivotIndex(row)]
            if(pivot == 0){
                for(int subRowNum = rowNum + 1; subRowNum < matrix.length; subRowNum++){
                    if(matrix[subRowNum][0] != 0){
                        interchangeRows(matrix, rowNum, subRowNum);
                        break;
                    }
                }
            }
            //Scale pivot row so that the first entry is 1
            if(pivot != 1 && pivot != 0){
                scaleSequence(row, 1/pivot);
            }
            //Create column of 0s below pivot
            for(int subRowNum = rowNum + 1; subRowNum < matrix.length; subRowNum++){
                int[] subRow = matrix[subRowNum];
                System.out.println("SubRow (initial) --> " + Arrays.toString(subRow));
                int subPivot = matrix[subRowNum][0];
                if(subPivot != 0){
                    scaleSequence(subRow, 1/subPivot);
                    subRow = subtractSequences(matrix, subRowNum, rowNum);
                }
                System.out.println("SubRow (afterwards) --> " + Arrays.toString(subRow));
            }
            System.out.println("ColumNum: " + currentColumn + " ,matrix: " + Arrays.deepToString(matrix) + "\n");

            //Create column of 0s above pivot
            currentColumn += 1;
        }

        return matrix;
    }

    static int findPivotIndex(int[] row){
        int i = 0;
        while(i < row.length){
            if(row[i] != 0){
                return i;
            }
            i++;
        }
        return row.length - 1;
    }


    static int[] subtractSequences(int[][] matrix, int row1, int row2){
        int[] diffs = new int[matrix[row1].length];
        for(int i = 0; i < matrix[row1].length; i++){
            diffs[i] = matrix[row1][i] - matrix[row2][i];
        }
        return diffs;
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

    public int findGCD(int big, int small) {
        if (big % small == 0) {
            return small;
        }
        return findGCD(small, big % small);
    }

    public int findLCM(int a, int b) {
        if (a > b) {
            return a * b / findGCD(a, b);
        } else {
            return a * b / findGCD(b, a);
        }

    }


}