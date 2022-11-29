package highlevelmath.constructs;

/**
 * This class creates a represntation of a matrix that can be used to store data
 * and perform operations upon. Matrices will be especially useful for operations in
 * linear algebra and similar areas. 
 * 
 * Each row and column of the matrix are treated as Vectors.
 */
public class Matrix {

    private static final String ROW_OUT_RANGE = "The rows are out of range.";
    private static final String ROW_NUM_OUT_RANGE = "The row number is out of range.";
    private static final String COL_OUT_RANGE = "The columns are out of range.";
    private static final String COL_NUM_OUT_RANGE = "The col number is out of range.";

    protected Vector[] data;
    protected boolean multiline;

    /**
     * A constructor for Matrix class
     * @param matrix An array of Vector objects that represent the rows of the matrix
     */
    public Matrix(Vector[] matrix){
        recorrectMatrix(matrix);
        this.data = matrix;
        this.multiline = true;
    }

    /**
     * A constructor for Matrix class
     * @param matrix A 2D array of doubles that represent the structure of the matrix
     */
    public Matrix(double[][] matrix){
        Vector[] array = new Vector[matrix.length];
        int i = 0;
        for(double[] row : matrix){
            Vector v = new Vector(row);
            array[i] = v;
            i++;
        }
        recorrectMatrix(array);
        this.data = array;
        this.multiline = true;
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
                if(v.getLength() > maxRowLength){
                    maxRowLength = v.getLength();
                }
            }
            try {
                Vector[] newMat = new Vector[maxRowLength];
                for(int r = 0; r < maxRowLength; r++){
                    double[] rowVec = new double[matrix.length];
                    for(int c = 0; c < matrix.length; c++){
                        rowVec[c] = matrix[c].get(r);
                    }
                    newMat[r] = new Vector(rowVec);
                }
                matrix = newMat;
            } catch(OperationUndefinedException e){
                e.printStackTrace();
            }
        }
        recorrectMatrix(matrix);
        this.data = matrix;
        this.multiline = true;
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
                if(column.length > maxRowLength){
                    maxRowLength = column.length;
                }
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
        int i = 0;
        for(double[] row : matrix){
            Vector v = new Vector(row);
            array[i] = v;
            i++;
        }
        recorrectMatrix(array);
        this.data = array;
        this.multiline = true;
    }

    //Operations between Matrices
    /**
     * Operation to add two matrices to one another
     *  - Each entry will be added to the corresponding entry in the input Matrix
     * @param matrix Matrix object that should be added
     * @throws OperationUndefinedExceptfion
     */
    public void add(Matrix matrix) throws OperationUndefinedException{
        // MatrixOperation function = (d1, d2) -> {return d1 + d2;};
        MatrixOperation function = (d1, d2) -> d1 + d2;
        applyOperation(matrix, function);
    }

    /**
     * Operation to subtract two matrices to one another
     *  - Each entry will subtract the corresponding entry in the input Matrix
     * @param matrix Matrix object that should be subtracted
     * @throws OperationUndefinedException
     */
    public void subtract(Matrix matrix) throws OperationUndefinedException{
        MatrixOperation function = (d1, d2) -> d1 - d2;
        applyOperation(matrix, function);
    }

    public Matrix multiply(Matrix matrix) throws OperationUndefinedException{
        if(this.getNumCols() != matrix.getNumRows()){
            throw new OperationUndefinedException("The columns of matrix 1 must equal the number of rows of matrix 2.");
        }
        double[][] newM = new double[this.getNumRows()][matrix.getNumCols()];
        for(int row = 0; row < data.length; row++){
            Vector v = this.getRow(row);
            for(int col = 0; col < matrix.getNumCols(); col++){
                newM[row][col] = v.dot(matrix.getColumn(col));
            }
        }
        return new Matrix(newM);
    }

    public Vector multiply(Vector v) throws OperationUndefinedException{
        if(this.getNumCols() != v.getLength()){
            throw new OperationUndefinedException("The columns of the matrix must equal the length of the vector.");
        }
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
    public void modulus(Matrix matrix) throws OperationUndefinedException{
        if(matrix.contains(0)){
            throw new OperationUndefinedException("This operation cannot be applied to input matrices with value 0.");
        }
        MatrixOperation function = (d1, d2) -> d1 % d2;
        applyOperation(matrix, function);
    }

    @Override
    public boolean equals(Object o){
        try {
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            if(data.length != ((Matrix)o).getNumRows() || data[0].getLength() != ((Matrix)o).getNumCols()){
                return false;
            }
            for(int row = 0; row < data.length; row++){
                for(int col = 0; col < data[0].getLength(); col++){
                    if(data[row].get(col) != ((Matrix)o).get(row, col)){
                        return false;
                    }
                }
            }
            return true;
        } catch(OperationUndefinedException e){
            return false;
        }
    }

    //Methods to Manipulate Matrix

    /**
     * Return a Matrix that represents a sub-matrix of the current one
     *  - (Row/Column positions start with 0)
     * @param startRow The row to start sub-matrix entries
     * @param endRow The row to end sub-matrix entries (inclusive)
     * @param startCol The column to start sub-matrix entries
     * @param endCol The column to end sub-matrix entries (inclusive)
     * @return Matrix that represents sub-matrix satisying defined parameters
     * @throws OperationUndefinedException
     */
    public Matrix subMatrix(int startRow, int endRow, int startCol, int endCol) throws OperationUndefinedException{
        if(startRow < 0 || endRow > data.length || startCol < 0 || endCol > data[0].getLength()){
            throw new OperationUndefinedException("A row or column parameter was out of the matrix's range.");
        }
        double[][] sub = new double[endRow - startRow + 1][endCol - startCol + 1];
        for(int i = startRow; i <= endRow; i++){
            for(int j = startCol; j <= endCol; j++){
                sub[i][j] = this.get(i, j);
            }
        }
        return new Matrix(sub);
    }

    /**
     * Adds row 2 to row 1 of the matrix
     *  - (Row positions start with 0)
     * @param row1 The row to be added to
     * @param row2 The row that will be added
     * @throws OperationUndefinedException
     */
    public void addRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        }
        //Row 1 = Row 1 + Row 2
        for(int col = 0; col < data[row1].getLength(); col++){
            data[row1].set(col, data[row1].get(col) + data[row2].get(col));
        }
    }

    /**
     * Adds column 2 to column 1 of the matrix
     *  - (Column positions start with 0)
     * @param col1 The column to be added to
     * @param col2 The column that will be added
     * @throws OperationUndefinedException
     */
    public void addColumns(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data.length; row++){
            data[row].set(col1, data[row].get(col1) + data[row].get(col2));
        }
    }

    /**
     * Subtracts row 2 from row 1 of the matrix
     *  - (Row positions start with 0)
     * @param row1 The row that will be subtracted from
     * @param row2 The row that will be subtracted
     * @throws OperationUndefinedException
     */
    public void subtractRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        }
        //Row 1 = Row 1 - Row 2
        for(int col = 0; col < data[row1].getLength(); col++){
            data[row1].set(col, data[row1].get(col) - data[row2].get(col));
        }
    }

    /**
     * Subtracts column 2 from column 1 of the matrix
     *  - (Column positions start with 0)
     * @param col1 The column that will be subtracted from
     * @param col2 The column that will be subtracted
     * @throws OperationUndefinedException
     */
    public void subtractColumns(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        //Col 1 = Col 1 - Col 2
        for(int row = 0; row < data.length; row++){
            data[row].set(col1, data[row].get(col1) - data[row].get(col2));
        }
    }

    /**
     * Interchange two rows of the matrix
     *  - (Row positions start with 0)
     * @param row1 A row number that will exchange positions with another row
     * @param row2 A row number that will exchange positions with another row
     * @throws OperationUndefinedException
     */
    public void interchangeRows(int row1, int row2) throws OperationUndefinedException{
        if(row1 >= data.length || row2 >= data.length){
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        }
        Vector firstRow = getRow(row1);
        setRow(row1, getRow(row2));
        setRow(row2, firstRow);
    }

    /**
     * Interchange two columns of the matrix
     *   - (Column positions start with 0)
     * @param col1 A column number that will exchange positions with another column
     * @param col2 A column number that will exchange positions with another column
     * @throws OperationUndefinedException
     */
    public void interchangeCols(int col1, int col2) throws OperationUndefinedException{
        if(col1 >= data[0].getLength() || col2 >= data[0].getLength()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        Vector firstCol = getColumn(col1);
        Vector secondCol = getColumn(col2);
        setColumn(col1, secondCol);
        setColumn(col2, firstCol);
    }

    /**
     * Scale a row of the matrix by a factor
     * @param rowNum The row number to be scaled (first row is 0)
     * @param factor The factor by which the row is scaled
     * @throws OperationUndefinedException
     */
    public void scaleRow(int rowNum, double factor) throws OperationUndefinedException{
        if(rowNum >= data.length){
            throw new OperationUndefinedException(ROW_OUT_RANGE);
        }
        data[rowNum].scale(factor);
    }

    /**
     * Scale a column of the matrix by a factor
     * @param columnNum The column number (first column is 0)
     * @param factor The factor by which the column is scaled
     * @throws OperationUndefinedException
     */
    public void scaleColumn(int columnNum, double factor) throws OperationUndefinedException{
        if(columnNum >= data[0].getLength()){
            throw new OperationUndefinedException(COL_OUT_RANGE);
        }
        for(int i = 0; i < data.length; i++){
            data[i].set(columnNum, data[i].get(columnNum)*factor);
        }
        correctRounding();
    }

    public Matrix copy(){
        return new Matrix(data);
    }

    // Setter Methods
    /**
     * Set a particular row number to a new Vector
     * @param rowNum The row number that should be set to a new Vector (first row is 0)
     * @param newRow A Vector that represents the new row
     * @throws OperationUndefinedException
     */
    public void setRow(int rowNum, Vector newRow) throws OperationUndefinedException{
        if(newRow.getLength() != data[0].getLength()){
            throw new OperationUndefinedException("The vector length is out of range.");
        }
        newRow.recorrect(data[0].getLength() - newRow.getLength());
        for(int col = 0; col < data[rowNum].getLength(); col++){
            data[rowNum].set(col, newRow.get(col));
        }
    }

    /**
     * Set a particular column number to a new Vector
     * @param colNum The column number that should be set to a new Vector (first column is 0)
     * @param newCol A Vector that represents the new column
     * @throws OperationUndefinedException
     */
    public void setColumn(int colNum, Vector newCol) throws OperationUndefinedException{
        if(newCol.getLength() >= data.length){
            throw new OperationUndefinedException("The vector length is out of range.");
        }
        newCol.recorrect(data.length - newCol.getLength());
        for(int row = 0; row < data.length; row++){
            data[row].set(colNum, newCol.get(row));
        }
    }

    /**
     * Sets a particular index of the matrix to a new value
     *  - (Row/Column positions start with 0)
     * @param row The row position of the index
     * @param column The column position of the index
     * @param value The value the position should be set to
     * @throws OperationUndefinedException
     */
    public void set(int row, int column, double value) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        } else if(column > data[0].getLength()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        data[row].set(column, value);
    }

    /**
     * Sets a particular index of the matrix to a new value
     *  - (Row/Column positions start with 0)
     * @param row The row position of the index
     * @param columnn The column position of the index
     * @param value The value the position should be set to
     * @throws OperationUndefinedException
     */
    public void set(int row, int column, int value) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        } else if(column > data[0].getLength()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        data[row].set(column, (double)value);
    }

    /**
     * The multline attribute configures whether the string representation of a matrix
     * should all be displayed on one line, or across many lines (multiline output)
     * @param multiline The state the multiline attribute should be set to
     */
    public void setMultiline(boolean multiline){
        this.multiline = multiline;
    }

    //Getter Methods
    /**
     * Get a particular row from the matrix
     * @param rowNum The row number to get (first row is 0)
     * @return A new Vector with the contents of the row chosen from the matrix
     * @throws OperationUndefinedException
     */
    public Vector getRow(int rowNum) throws OperationUndefinedException{
        if(rowNum >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        }
        double[] row = new double[data[0].getLength()];
        for(int columnNum = 0; columnNum < data[0].getLength(); columnNum++){
            row[columnNum] = data[rowNum].get(columnNum);
        }
        return new Vector(row);
    }

    /**
     * Get a particular column from the matrix
     * @param columnNum The column number to get (first column is 0)
     * @return A new Vector with the contents of the column chosen from the matrix
     * @throws OperationUndefinedException
     */
    public Vector getColumn(int columnNum) throws OperationUndefinedException{
        if(columnNum >= data[0].getLength()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        double[] column = new double[data.length];
        for(int rowNum = 0; rowNum < data.length; rowNum++){
            column[rowNum] = data[rowNum].get(columnNum);
        }
        return new Vector(column);
    }

    /**
     * Get the value at a particular index of the matrix
     *  - (Row/Column positions start with 0)
     * @param row The row position of the index
     * @param col The column positoin of the index
     * @return The value at the index
     * @throws OperationUndefinedException
     */
    public double get(int row, int col) throws OperationUndefinedException{
        if(row >= data.length){
            throw new OperationUndefinedException(ROW_NUM_OUT_RANGE);
        } else if(col > data[0].getLength()){
            throw new OperationUndefinedException(COL_NUM_OUT_RANGE);
        }
        return data[row].get(col);
    }

    /**
     * Get the number of rows
     * @return number of rows in the matrix
     */
    public int getNumRows(){
        return data.length;
    }

    /**
     * Get the number of columns
     * @return number of columns in the matrix
     */
    public int getNumCols(){
        return data[0].getLength();
    }

    /**
     * Get the multline attribute which configures how the string representation of a matrix is represented
     * @return
     */
    public boolean getMultiline(){
        return multiline;
    }

    /**
     * Returns whether a particular value is present within the matrix or not
     * @param value The value to search for
     * @return True if value is in the matrix, False otherwise
     */
    public boolean contains(double value){
        for(Vector v : data){
            if(v.contains(value)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns whether a particular value is present within the matrix or not
     * @param value The value to search for
     * @return True if value is in the matrix, False otherwise
     */
    public boolean contains(int value){
        for(Vector v : data){
            if(v.contains(value)){
                return true;
            }
        }
        return false;
    }

    //Other Methods
    private void recorrectMatrix(Vector[] matrix){
        //Find maximum row length
        int maxRowLength = 0;
        for(Vector v : matrix){
            if(v.getLength() > maxRowLength){
                maxRowLength = v.getLength();
            }
        }
        //Fill in any empty space to make the matrix take the form of a rectangle
        for(int row = 0; row < matrix.length; row++){
            matrix[row].recorrect(maxRowLength - matrix[row].getLength());
        }
    }

    /**
     * Will correct any roundings issues (too much precision) with values in the matrix
     */
    public void correctRounding(){
        for(Vector v : data){
            v.correctRounding();
        }
    }

    protected void applyOperation(Matrix matrix, MatrixOperation op) throws OperationUndefinedException{
        if(data.length != matrix.getNumRows()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of rows.");
        } else if(data[0].getLength() != matrix.getNumCols()){
            throw new OperationUndefinedException("This operation cannot be applied to matrices with different numbers of columns.");
        }
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].getLength(); col++){
                data[row].set(col, op.operation(data[row].get(col), matrix.get(row, col)));
            }
        }
    }

    @Override
    public String toString() {
        String str = "[";
        int rowNum = 0;
        for(Vector v : data){
            str += v.toString();
            if(multiline && rowNum < data.length - 1){
                str += "\n ";
            }
            rowNum++;
        }
        str += "]";
        return str;
    }

    protected double truncateDecimal(double value, int places){
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen)/powerOfTen;
    }
       
}
