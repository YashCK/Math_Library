package highlevelmath;
import highlevelmath.constructs.*;

public class LinAlg {

    static int calcDeterminant(int[][] matrix){
        return 0;
    }
    
    static int[] gaussianElimination(Matrix matrix, Vector b){
        // return matrix.get(0, 0);
        ;
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
    */
    public static void rowReduction(Matrix matrix) throws OperationUndefinedException{
        for(int column = 0; column < matrix.getNumCols(); column++){
            //Interchange Rows so that the row being worked on does not have a leading 0
            if(matrix.get(0, column) != 0){
                
            }
        }
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