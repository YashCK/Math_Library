package highlevelmath;
import highlevelmath.constructs.*;

public class LinAlg {

    private LinAlg() {
        throw new IllegalStateException("Utility class");
    }

    static int calcDeterminant(int[][] matrix){
        return 0;
    }
    
    // static int[] gaussianElimination(Matrix matrix, Vector b){
    //     // return matrix.get(0, 0);
    //     ;
    // }

    /*
    * This function reduces the matrix passed in to its RREF state.
    *   - All nonzero rows are above any rows of all zeros.
    *   - Each leading entry of a row is in a column to the right of the leading entry of the row above it.
    *   - All entries in a column below a leading entry are zeros.
    *   - The leading entry in each nonzero row is 1.
    *   - Each leading 1 is the only nonzero entry in its column.
    */
    public static void rowReduction(Matrix matrix) throws OperationUndefinedException{
        int totalCols = matrix.getNumCols();
        int totalRows = matrix.getNumRows();
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

    public static double[] getPivots(Matrix matrix) throws OperationUndefinedException{
        double[] pivots = new double[matrix.getNumRows()];
        int num = 0;
        int totalCols = matrix.getNumCols();
        for(int row = matrix.getNumRows() - 1; row > -1; row --){
            int column = 0;
            double val = matrix.get(row, column);
            while(val == 0){
                column++;
                if(column >= totalCols){
                    row--;
                    break;
                } else if(row < 0){
                    break;
                }
                val = matrix.get(row, column);
            }
            System.out.println(val);
            pivots[num] = val;
            num++;
        }
        if(num < pivots.length){
            double[] newPivots = new double[num];
            int i = 0;
            for(double elem : pivots){
                if(elem != 0){
                    newPivots[i] = elem;
                }
                i++;
            }
            return newPivots;
        }
        return pivots;
    }

    static int findGCD(int big, int small) {
        if (big % small == 0) {
            return small;
        }
        return findGCD(small, big % small);
    }

    static int findLCM(int a, int b) {
        if (a > b) {
            return a * b / findGCD(a, b);
        } else {
            return a * b / findGCD(b, a);
        }

    }

    


}