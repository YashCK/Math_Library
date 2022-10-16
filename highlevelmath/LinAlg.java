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


    public static double[] subtractSequences(double[][] matrix, int row1, int row2){
        double[] diffs = new double[matrix[row1].length];
        for(int i = 0; i < matrix[row1].length; i++){
            diffs[i] = matrix[row1][i] - matrix[row2][i];
        }
        return diffs;
    }

    public static void interchangeRows(double[][] matrix, int row1, int row2){
        double[] firstRow = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = firstRow;
    }

    public static void scaleSequence(double[] rowOrColumn, double scale){
        for(int i = 0; i < rowOrColumn.length; i++){
            rowOrColumn[i] *= scale;
        }
    }

    public static double[] findColumn(double[][] matrix, int columnNum){
        double[] column = new double[matrix[0].length];
        for(int rowNum = 0; rowNum < matrix.length; rowNum++){
            column[rowNum] = matrix[rowNum][columnNum];
        }
        return column;
    }

    public static void print(double[] array){
        String str = "Array: [";
        for(double element : array){
            str += element == array[0] ? "" : " ";
            str += truncateDecimal(element, 2);
        }
        System.out.println(str + "]");
    }

    public static void print(double[][] matrix){
        String str = "Matrix: [";
        for(double[] row : matrix){
            str += "[";
            for(double element: row){
                str += element == row[0] ? "" : " ";
                str += truncateDecimal(element, 2);
            }
            str += "]";  
        }
        System.out.println(str + "]");
    }

    static double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
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