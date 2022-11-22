package highlevelmath;

public class LinAlg {

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
    public static double[][] rowReduction(double[][] matrix){

        int currentColumn = 0;
        for(int rowNum = 0; rowNum < matrix.length; rowNum++){
            double[] row = matrix[rowNum];
            //If pivot is 0 then interchange rows with one that has a pivot in that location
            double pivot = row[currentColumn];
            // int pivot = row[findPivotIndex(row)]
            // System.out.println("row: " + Arrays.toString(row));
            if(pivot == 0){
                for(int subRowNum = rowNum + 1; subRowNum < matrix.length; subRowNum++){
                    if(matrix[subRowNum][0] != 0){
                        interchangeRows(matrix, rowNum, subRowNum);
                        row = matrix[rowNum];
                        pivot = row[currentColumn];
                        break;
                    }
                }
            }
            //Scale pivot row so that the first entry is 1
            if(pivot != 0){
                // System.out.println("pivot: " + pivot);
                scaleSequence(row, 1/pivot);
                // System.out.println("scaled");
            }
            // System.out.println("row: " + Arrays.toString(row));
            //Create column of 0s below pivot
            for(int subRowNum = rowNum + 1; subRowNum < matrix.length; subRowNum++){
                double[] subRow = matrix[subRowNum];
                // System.out.println("SubRow (initial) --> " + Arrays.toString(subRow));
                double subPivot = matrix[subRowNum][currentColumn];
                if(subPivot != 0){
                    scaleSequence(subRow, 1/subPivot);
                    subRow = subtractSequences(matrix, subRowNum, rowNum);
                }
                // System.out.println("SubRow (afterwards) --> " + Arrays.toString(subRow));
            }
            System.out.println("ColumNum: " + currentColumn);
            print(matrix);
            //Create column of 0s above pivot
            currentColumn += 1;
        }

        return matrix;
    }

    public static double findPivotIndex(double[] row){
        int i = 0;
        while(i < row.length){
            if(row[i] != 0){
                return i;
            }
            i++;
        }
        return row.length - 1;
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