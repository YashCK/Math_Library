package highlevelmath.constructs;

import highlevelmath.constructs.util.MatrixOperation;
import highlevelmath.constructs.util.OperationUndefinedException;

public class Matrix extends Mat<Double, Double, Vector>{

     /**
     * A constructor for Matrix class
     * @param matrix An array of Vector objects that represent the rows of the matrix
     */
    public Matrix(Vector[] matrix){
        recorrectMatrix(matrix);
        this.data = matrix;
    }

    /**
     * A constructor for Matrix class
     * @param matrix A 2D array of doubles that represent the structure of the matrix
     */
    public Matrix(double[][] matrix){
        Vector[] array = new Vector[matrix.length];
        for(int i = 0; i < array.length; i++){
            array[i] = new Vector(matrix[i]);
        }
        recorrectMatrix(array);
        this.data = array;
    }

    /**
     * A constructor for Matrix class
     * @param matrix An array of Vector objects that represent the rows of the matrix
     * @param asColumn Whether to interpret each Vector as a column vector or not
     */
    public Matrix(Vector[] matrix, boolean asColumn){
        if(asColumn){
            int maxRowLength = 0;
            for(Vector v : matrix){
                if(v.length() > maxRowLength)
                    maxRowLength = v.length();
            }
            Vector[] newMat = new Vector[maxRowLength];
            try {
                for(int r = 0; r < maxRowLength; r++){
                    double[] rowVec = new double[matrix.length];
                    for(int c = 0; c < matrix.length; c++){
                        rowVec[c] = matrix[c].get(r);
                    }
                    newMat[r] = new Vector(rowVec);
                }
            } catch (OperationUndefinedException e) {
                e.printStackTrace();
            }
            matrix = newMat;
        }
        recorrectMatrix(matrix);
        this.data = matrix;
    }

    /**
     * A constructor for Matrix class
     * @param matrix A 2D array of doubles that represent the structure of the matrix
     * @param asColumn Whether to interpret each array as a column vector or not
     */
    public Matrix(double[][] matrix, boolean asColumn){
        Vector[] array = new Vector[matrix.length];
        if(asColumn){
            int maxRowLength = 0;
            for(double[] column : matrix){
                if(column.length > maxRowLength)
                    maxRowLength = column.length;
            }
            array = new Vector[maxRowLength];
            double[][] newMat = new double[maxRowLength][matrix.length];
            for(int col = 0; col < matrix.length; col++){
                for(int row = 0; row < matrix[col].length; row++){
                    newMat[row][col] = matrix[col][row];
                }
            }
            matrix = newMat;
        }
        for(int i = 0; i < array.length; i++){
            array[i] = new Vector(matrix[i]);
        }
        recorrectMatrix(array);
        this.data = array;
    }

    //Operations

    @Override
    public void add(Mat<Double, Double, Vector> matrix) throws OperationUndefinedException {
        MatrixOperation<Double> function = (d1, d2) -> d1 + d2;
        applyOperation(matrix, function);
    }
    
    @Override
    public void subtract(Mat<Double, Double, Vector> matrix) throws OperationUndefinedException {
        MatrixOperation<Double> function = (d1, d2) -> d1 - d2;
        applyOperation(matrix, function);
    }

    @Override
    public Mat<Double, Double, Vector> multiply(Mat<Double, Double, Vector> matrix) throws OperationUndefinedException {
        if(this.ncols() != matrix.nrows())
            throw new OperationUndefinedException("The columns of matrix 1 must equal the number of rows of matrix 2.");
        double[][] newM = new double[this.nrows()][matrix.ncols()];
        for(int row = 0; row < data.length; row++){
            Vector v = this.getRow(row);
            for(int col = 0; col < matrix.ncols(); col++){
                newM[row][col] = v.dot(matrix.getCol(col));
            }
        }
        return new Matrix(newM);
    }

    @Override
    public Vector multiply(Vector v) throws OperationUndefinedException {
        if(this.ncols() != v.length())
            throw new OperationUndefinedException("The columns of the matrix must equal the length of the vector.");
        double[] newV = new double[data.length];
        for(int row = 0; row < data.length; row++){
            newV[row] = data[row].dot(v);
        }
        return new Vector(newV);
    }

    /**
     * Operation to take the modulus of another matrix
     * @param matrix Matrix object whose entires will act as the modulus divisor
     * @throws OperationUndefinedException
     */
    public void modulus(Mat<Double, Double, Vector> matrix) throws OperationUndefinedException{
        if(matrix.contains(0.0))
            throw new OperationUndefinedException("This operation cannot be applied to input matrices with value 0.");
        MatrixOperation<Double> function = (d1, d2) -> d1 % d2;
        applyOperation(matrix, function);
    }

    //Methods to Manipulate Matrix

    @Override
    public Mat<Double, Double, Vector> subMatrix(int startRow, int endRow, int startCol, int endCol)
            throws OperationUndefinedException {
        if(startRow < 0 || endRow > data.length || startCol < 0 || endCol > data[0].length())
            throw new OperationUndefinedException("A row or column parameter was out of the matrix's range.");
        double[][] sub = new double[endRow - startRow + 1][endCol - startCol + 1];
        for(int i = startRow; i <= endRow; i++){
            for(int j = startCol; j <= endCol; j++){
                sub[i][j] = this.get(i, j);
            }
        }
        return new Matrix(sub);
    }

    @Override
    public void addRows(int row1, int row2) throws OperationUndefinedException {
        if(row1 >= data.length || row2 >= data.length)
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        //Row 1 = Row 1 + Row 2
        getRow(row1).add(getRow(row2));
    }

    @Override
    public void addColumns(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data[0].length() || col2 >= data[0].length())
            throw new OperationUndefinedException(COL_OUT_RANGE);
        //Col 1 = Col 1 + Col 2
        getCol(col1).add(getCol(col2));
    }

    @Override
    public void subtractRows(int row1, int row2) throws OperationUndefinedException {
        if(row1 >= data.length || row2 >= data.length)
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        //Row 1 = Row 1 - Row 2
        getRow(row1).subtract(getRow(row2));
    }

    @Override
    public void subtractColumns(int col1, int col2) throws OperationUndefinedException {
        if(col1 >= data[0].length() || col2 >= data[0].length())
            throw new OperationUndefinedException(COL_OUT_RANGE);
        //Col 1 = Col 1 - Col 2
        getCol(col1).subtract(getCol(col2));
    }

    @Override
    public void scaleColumn(int columnNum, double factor) throws OperationUndefinedException {
        if(columnNum >= data[0].length()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        for(int i = 0; i < data.length; i++){
            data[i].set(columnNum, data[i].get(columnNum)*factor);
        }
        correctRounding();
    }

    @Override
    public Mat<Double, Double, Vector> copy() {
        return new Matrix(data);
    }

    //Getter Methods
    @Override
    public Vector getRow(int num) throws OperationUndefinedException {
        if(num >= data.length)
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        double[] row = new double[data[0].length()];
        for(int cNum = 0; cNum < data[0].length(); cNum++){
            row[cNum] = data[num].get(cNum);
        }
        return new Vector(row);
    }

    @Override
    public Vector getCol(int num) throws OperationUndefinedException {
        if(num >= data[0].length())
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        double[] column = new double[data.length];
        for(int rowNum = 0; rowNum < data.length; rowNum++){
            column[rowNum] = data[rowNum].get(num);
        }
        return new Vector(column);
    }

    //Other Methods
    /**
     * Will correct any roundings issues (too much precision) with values in the matrix
     */
    public void correctRounding(){
        for(Vector v : data){
            v.correctRounding();
        }
    }

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }

    public CMatrix toComplex(){
        //TO BE IMPLEMENTED
    }

}
