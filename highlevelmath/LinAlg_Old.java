package highlevelmath;

import highlevelmath.constructs.*;

public class LinAlg_Old<T, S> {

    static int calcDeterminant(int[][] matrix){
        return 0;
    }
    
    public static Vec<?> gaussianElimination(Mat<?, ?, ?, ?> matrix, Vec<?, ?, ?> b) throws NoExistingSolutionException, OperationUndefinedException{
        // double[] solutions = new double[matrix.getNumRows()];
        double[] solutions = new double[matrix.getNumCols()];
        boolean[] freeVars = new boolean[matrix.getNumCols()];
        //If no solution exists, then throw NoExistingSolutionException
        if(hasNoSolution(matrix)){
            throw new NoExistingSolutionException("The matrix does not have a solution. Gaussian Elimination cannot be applied.");
        }
        //Create augmented matrix
        Vec_Old<?, ?, ?>[] augCols = augment(matrix, b);
        Vec_Old<?, ?, ?>[] augMat = new Vec<?, ?, ?>[augCols.length - 1];
        int n = 0;
        for(Vec<?, ?, ?> a : augCols){
            if(n >= augMat.length)
                break;
            augMat[n] = a;
            n++;
        }
        b = augCols[augCols.length - 1];
        matrix = new Matrix_Old(augMat, true);
        //Loop from last row to the first row | Apply backsubstitution
        int lastPivotIndex = matrix.getNumCols();
        for(int row = matrix.getNumRows() - 1; row > -1; row--){
            int ind = getPivotIndex(matrix.getRow(row));
            solutions[ind] = b.get(row);
            for(int i = ind + 1; i < matrix.getNumCols(); i++){
                solutions[ind] -= matrix.get(row, i);
                solutions[ind] /= matrix.get(row, ind);
            }
            if(lastPivotIndex - ind > 1){
                freeVars[ind + (lastPivotIndex - ind)/2] = true;
                solutions[ind + (lastPivotIndex - ind)/2] = 1;
            }
            lastPivotIndex = ind;
        }
        Vec<?, ?, ?> v = new Vec<?, ?, ?>(solutions);
        v.correctRounding();
        return v;
    }

    private static Vec_Old<?, ?, ?>[] augment(Matrix_Old m, Vec<?, ?, ?> b) throws OperationUndefinedException{
        //Create augmented matrix
        Vec_Old<?, ?, ?>[] aug = new Vec<?, ?, ?>[m.getNumCols() + 1];
        for(int a = 0; a < m.getNumCols(); a++){
            aug[a] = m.getColumn(a);
        }
        aug[aug.length - 1] = b;
        Matrix_Old augmented = new Matrix_Old(aug, true);
        //Row reduce augmented matrix
        rowReduction(augmented);
        //Return Vector[] where Vectors from indices 0 to length - 2 make up the RREF matrix
        //The last Vector in Vector[] is the b column with row operations applied upon it
        for(int col = 0; col < augmented.getNumCols(); col++){
            aug[col] = augmented.getColumn(col);
        }
        return aug;
    }

    public static boolean isLinIndependent(Matrix_Old m) throws OperationUndefinedException{
        return hasUniqueSolution(m);
    }

    // public static boolean isAlwaysConsistent(Matrix m){

    // }

    public static boolean hasSolution(Matrix_Old m) throws OperationUndefinedException{
        return hasUniqueSolution(m) || hasInfiniteSolutions(m);
    }

    public static boolean hasUniqueSolution(Matrix_Old m) throws OperationUndefinedException{
        //At least one solution | A pivot in every row
        for(int row = 0; row < m.getNumRows(); row++){
            if(getPivotIndex(m.getRow(row)) == -1){
                return false;
            }
        }
        //At most one solutoin | A pivot in every column
        for(int col = 0; col < m.getNumCols(); col++){
            if(getPivotIndex(m.getColumn(col)) == -1){
                return false;
            }
        }
        return true;
    }

    public static boolean hasInfiniteSolutions(Matrix_Old m) throws OperationUndefinedException{
        if(hasUniqueSolution(m)){
            return false;
        }
         //At least one solution | A pivot in every row
         for(int row = 0; row < m.getNumRows(); row++){
            if(getPivotIndex(m.getRow(row)) == -1){
                return false;
            }
        }
        //Check if there is a free variable
        return m.getNumRows() < m.getNumCols();
    }

    public static boolean hasNoSolution(Matrix_Old m) throws OperationUndefinedException{
        return !(hasUniqueSolution(m) || hasInfiniteSolutions(m));
    }

    // public static int numFreeVars(Matrix m){
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
    public static void rowReduction(Matrix_Old matrix) throws OperationUndefinedException{
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

    public static double[] getPivots(Matrix_Old matrix) throws OperationUndefinedException{
        double[] pivots = new double[matrix.getNumRows()];
        int num = 0;
        for(int row = matrix.getNumRows() - 1; row > -1; row --){
            double val = matrix.get(row, getPivotIndex(matrix.getRow(row)));
            if(val != -1){
                pivots[num] = val;
                num++;
            } else {
                break;
            }
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

    public static int getPivotIndex(Vec<?, ?, ?> v) throws OperationUndefinedException{
        int ind = 0;
        double val = v.get(ind);
        while(val == 0){
            ind++;
            if(ind > v.length)
                return -1;
            val = v.get(ind);
        }
        return ind;
    }

    public static Matrix_Old identity(int num){
        double[][] im = new double[num][num];
        for(int i = 0; i < num; i++){
            im[i][i] = 1;
        }
        return new Matrix_Old(im);
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